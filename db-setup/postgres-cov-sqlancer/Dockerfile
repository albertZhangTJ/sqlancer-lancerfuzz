FROM ubuntu:22.04

# Prevent interactive prompts during build
ENV DEBIAN_FRONTEND=noninteractive

# Install build dependencies and coverage tools
RUN apt-get update && apt-get install -y \
    build-essential \
    git \
    wget \
    gcc \
    g++ \
    make \
    pkg-config \
    libreadline-dev \
    zlib1g-dev \
    flex \
    bison \
    libxml2-dev \
    libxslt-dev \
    libssl-dev \
    libxml2-utils \
    xsltproc \
    gettext \
    tcl-dev \
    libperl-dev \
    python3-dev \
    libipc-run-perl \
    libicu-dev \
    # Coverage tools (gcov comes with gcc)
    lcov \
    # Fuzzing tools (AFL++ as example)
    afl++ \
    clang \
    llvm \
    # Java for SQLancer
    openjdk-21-jdk \
    maven \
    # Utilities
    vim \
    curl \
    postgresql-client \
    && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /opt

# Download PostgreSQL source - Latest version
ARG PG_VERSION=18.1
RUN wget https://ftp.postgresql.org/pub/source/v${PG_VERSION}/postgresql-${PG_VERSION}.tar.gz && \
    tar -xzf postgresql-${PG_VERSION}.tar.gz && \
    rm postgresql-${PG_VERSION}.tar.gz

WORKDIR /opt/postgresql-${PG_VERSION}

# Configure PostgreSQL with coverage enabled
# Key flags:
# --enable-coverage: Enable coverage instrumentation with gcov
# --enable-debug: Add debug symbols
# --enable-cassert: Enable assertions for better fuzzing
# --prefix: Installation directory
RUN ./configure \
    --enable-coverage \
    --enable-debug \
    --enable-cassert \
    --prefix=/usr/local/pgsql \
    --with-openssl \
    --with-libxml \
    --with-libxslt \
    CFLAGS="-O0 -g -fprofile-arcs -ftest-coverage" \
    LDFLAGS="-fprofile-arcs -ftest-coverage"

# Build and install PostgreSQL
RUN make -j$(nproc) && make install

# Add PostgreSQL binaries to PATH
ENV PATH="/usr/local/pgsql/bin:${PATH}"
ENV LD_LIBRARY_PATH="/usr/local/pgsql/lib:${LD_LIBRARY_PATH}"

# Create postgres user and data directory
RUN useradd -m -s /bin/bash postgres && \
    mkdir -p /var/lib/postgresql/data && \
    mkdir -p /coverage && \
    chown -R postgres:postgres /var/lib/postgresql /coverage && \
    chown -R postgres:postgres /opt/postgresql-${PG_VERSION}

# Create directory for fuzzing inputs/outputs
RUN mkdir -p /fuzzing/{inputs,outputs,corpus} && \
    chown -R postgres:postgres /fuzzing

RUN mkdir -p /tmp/pg_fuzz_tablespace && chown postgres:postgres /tmp/pg_fuzz_tablespace

# ---------------------------------------------------------------------------
# Build SQLancer
# Clone and build SQLancer from source. The jar will be at:
#   /sqlancer/target/sqlancer-*.jar
# To swap in your own fuzzer, replace this entire block and update the
# fuzzer invocation in /usr/local/bin/run-fuzzer.sh (see further below).
# ---------------------------------------------------------------------------
WORKDIR /
COPY sqlancer /sqlancer
WORKDIR /sqlancer
RUN mvn package -DskipTests -q
# ---------------------------------------------------------------------------
# End of SQLancer build block
# ---------------------------------------------------------------------------

# Switch to postgres user
USER postgres

# Initialize the database cluster
RUN /usr/local/pgsql/bin/initdb -D /var/lib/postgresql/data

# Configure PostgreSQL for local-only access (no host port needed)
RUN echo "host    all             all             127.0.0.1/32            md5" >> /var/lib/postgresql/data/pg_hba.conf && \
    echo "host    all             all             ::1/128                 md5" >> /var/lib/postgresql/data/pg_hba.conf

# Configure PostgreSQL to listen on localhost only (internal to container)
RUN echo "listen_addresses = 'localhost'" >> /var/lib/postgresql/data/postgresql.conf && \
    echo "port = 5432" >> /var/lib/postgresql/data/postgresql.conf && \
    echo "max_connections = 100" >> /var/lib/postgresql/data/postgresql.conf

# Start PostgreSQL temporarily to create sqlancer user and databases
RUN /usr/local/pgsql/bin/pg_ctl -D /var/lib/postgresql/data start && \
    sleep 5 && \
    /usr/local/pgsql/bin/createuser sqlancer && \
    /usr/local/pgsql/bin/psql -c "ALTER USER sqlancer WITH PASSWORD 'sqlancer' SUPERUSER;" && \
    /usr/local/pgsql/bin/createdb -O sqlancer sqlancer && \
    /usr/local/pgsql/bin/createdb -O sqlancer test && \
    /usr/local/pgsql/bin/pg_ctl -D /var/lib/postgresql/data stop && \
    sleep 2 && \
    echo "✓ Database initialized successfully (databases: sqlancer, test)"

# Expose PostgreSQL port (internal use only - not published to host)
EXPOSE 5432

# Set environment variables
ENV PGDATA=/var/lib/postgresql/data
ENV POSTGRES_DB=postgres
ENV POSTGRES_USER=postgres

WORKDIR /fuzzing

# ---------------------------------------------------------------------------
# Fuzzer invocation script
# THIS IS THE ONLY PLACE YOU NEED TO EDIT to swap SQLancer for your own fuzzer.
#
# The script is run as the postgres user (who owns the DB) after PostgreSQL
# starts. It receives no arguments. stdout/stderr go to the container log.
#
# SQLancer command breakdown:
#   --num-threads 1          global flag  (must come BEFORE the subcommand)
#   postgres                 DBMS subcommand
#   --host / --port / ...    DBMS-level flags (must come AFTER the subcommand)
#   --oracle WHERE           oracle selection (also AFTER the subcommand)
# ---------------------------------------------------------------------------
USER root
RUN echo '#!/bin/bash\n\
# Wait for PostgreSQL to be ready\n\
until /usr/local/pgsql/bin/pg_isready -h localhost -p 5432 -U sqlancer; do\n\
    sleep 1\n\
done\n\
\n\
echo "[fuzzer] PostgreSQL is ready. Starting fuzzer..."\n\
\n\
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\
# REPLACE THE LINES BELOW to swap SQLancer for your own fuzzer.\n\
# Everything between the >>> and <<< markers is the fuzzer invocation.\n\
exec java -jar /sqlancer/target/sqlancer-*.jar \\\n\
    --num-threads 1 \\\n\
    postgres \\\n\
    --oracle WHERE\n\
# <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n\
' > /usr/local/bin/run-fuzzer.sh && \
    chmod +x /usr/local/bin/run-fuzzer.sh
# ---------------------------------------------------------------------------
# End of fuzzer invocation script
# ---------------------------------------------------------------------------

# Create startup script
RUN echo '#!/bin/bash\n\
set -e\n\
\n\
echo "================================================="\n\
echo "PostgreSQL 18.1 with Coverage - Starting..."\n\
echo "================================================="\n\
\n\
if [ ! -f "/var/lib/postgresql/data/PG_VERSION" ]; then\n\
    echo "ERROR: Database not initialized!"\n\
    exit 1\n\
fi\n\
\n\
echo "Configuration:"\n\
echo "  - Version: PostgreSQL 18.1"\n\
echo "  - Port: 5432 (container-internal only)"\n\
echo "  - User: sqlancer / Password: sqlancer"\n\
echo "  - Databases: test, sqlancer"\n\
echo "  - Fuzzer log: /fuzzing/outputs/fuzzer.log"\n\
echo ""\n\
\n\
# Start PostgreSQL as postgres user\n\
echo "Starting PostgreSQL..."\n\
runuser -u postgres -- /usr/local/pgsql/bin/pg_ctl \\\n\
    -D /var/lib/postgresql/data \\\n\
    -l /var/lib/postgresql/data/logfile start\n\
\n\
echo "✓ PostgreSQL started"\n\
echo ""\n\
echo "Starting fuzzer (see /fuzzing/outputs/fuzzer.log)..."\n\
\n\
# Run the fuzzer as the postgres user, log output to file AND stdout\n\
runuser -u postgres -- bash -c \\\n\
    "/usr/local/bin/run-fuzzer.sh 2>&1 | tee /fuzzing/outputs/fuzzer.log"\n\
' > /usr/local/bin/start-postgres.sh && \
    chmod +x /usr/local/bin/start-postgres.sh

USER root
CMD ["/usr/local/bin/start-postgres.sh"]
