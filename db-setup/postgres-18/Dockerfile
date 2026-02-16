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

# Switch to postgres user
USER postgres

# Initialize the database cluster
RUN /usr/local/pgsql/bin/initdb -D /var/lib/postgresql/data

# Configure PostgreSQL for JDBC access
# 1. Allow password authentication from all hosts
RUN echo "host    all             all             0.0.0.0/0               md5" >> /var/lib/postgresql/data/pg_hba.conf && \
    echo "host    all             all             ::0/0                   md5" >> /var/lib/postgresql/data/pg_hba.conf

# 2. Configure PostgreSQL to listen on all interfaces
RUN echo "listen_addresses = '*'" >> /var/lib/postgresql/data/postgresql.conf && \
    echo "port = 5432" >> /var/lib/postgresql/data/postgresql.conf && \
    echo "max_connections = 100" >> /var/lib/postgresql/data/postgresql.conf

# 3. Start PostgreSQL temporarily to create sqlancer user and databases
RUN /usr/local/pgsql/bin/pg_ctl -D /var/lib/postgresql/data start && \
    sleep 5 && \
    /usr/local/pgsql/bin/createuser sqlancer && \
    /usr/local/pgsql/bin/psql -c "ALTER USER sqlancer WITH PASSWORD 'sqlancer' SUPERUSER;" && \
    /usr/local/pgsql/bin/createdb -O sqlancer sqlancer && \
    /usr/local/pgsql/bin/createdb -O sqlancer test && \
    /usr/local/pgsql/bin/pg_ctl -D /var/lib/postgresql/data stop && \
    sleep 2 && \
    echo "✓ Database initialized successfully (databases: sqlancer, test)"

# Expose PostgreSQL port
EXPOSE 5432

# Set environment variables
ENV PGDATA=/var/lib/postgresql/data
ENV POSTGRES_DB=postgres
ENV POSTGRES_USER=postgres



WORKDIR /fuzzing

# Create startup script with better error handling
USER root
RUN echo '#!/bin/bash\n\
set -e\n\
\n\
echo "================================================="\n\
echo "PostgreSQL 18.1 with Coverage - Starting..."\n\
echo "================================================="\n\
echo ""\n\
\n\
# Check if data directory is initialized\n\
if [ ! -f "/var/lib/postgresql/data/PG_VERSION" ]; then\n\
    echo "ERROR: Database not initialized!"\n\
    echo "This should not happen - check Dockerfile."\n\
    exit 1\n\
fi\n\
\n\
echo "Configuration:"\n\
echo "  - Version: PostgreSQL 18.1"\n\
echo "  - Port: 5432"\n\
echo "  - User: sqlancer / Password: sqlancer"\n\
echo "  - Databases: test, sqlancer"\n\
echo ""\n\
echo "JDBC (default): jdbc:postgresql://localhost:5432/test?user=sqlancer&password=sqlancer"\n\
echo "JDBC (alt):     jdbc:postgresql://localhost:5432/sqlancer?user=sqlancer&password=sqlancer"\n\
echo ""\n\
echo "Coverage: /opt/postgresql-18.1/**/*.gcda"\n\
echo "Reports: /coverage/html/index.html"\n\
echo ""\n\
\n\
# Start PostgreSQL as postgres user (using runuser from root)\n\
echo "Starting PostgreSQL..."\n\
runuser -u postgres -- /usr/local/pgsql/bin/pg_ctl -D /var/lib/postgresql/data -l /var/lib/postgresql/data/logfile start\n\
\n\
if [ $? -eq 0 ]; then\n\
    echo ""\n\
    echo "✓ PostgreSQL started successfully!"\n\
    echo ""\n\
    echo "Ready for fuzzing. Check logs with:"\n\
    echo "  docker exec postgres-fuzzing cat /var/lib/postgresql/data/logfile"\n\
    echo ""\n\
    echo "Collect coverage with:"\n\
    echo "  docker exec postgres-fuzzing coverage-helper.sh collect"\n\
    echo "================================================="\n\
    \n\
    # Wait and follow logs if they exist\n\
    sleep 2\n\
    if [ -f /var/lib/postgresql/data/logfile ]; then\n\
        tail -f /var/lib/postgresql/data/logfile\n\
    else\n\
        # Keep container running\n\
        echo "Keeping container alive... Press Ctrl+C to stop."\n\
        tail -f /dev/null\n\
    fi\n\
else\n\
    echo ""\n\
    echo "✗ PostgreSQL failed to start!"\n\
    echo "Check logs: docker exec postgres-fuzzing cat /var/lib/postgresql/data/logfile"\n\
    exit 1\n\
fi\n\
' > /usr/local/bin/start-postgres.sh && \
    chmod +x /usr/local/bin/start-postgres.sh

# Default command - run as root so runuser works
USER root
CMD ["/usr/local/bin/start-postgres.sh"]
