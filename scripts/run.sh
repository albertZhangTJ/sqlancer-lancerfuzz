#!/bin/bash
cp src/sqlancer/any/Fuzzer.java.template.txt src/sqlancer/any/Fuzzer.java && \
#First build, to get the SGL jar
echo "Building Seagull" && \
mvn clean package -Dskip.main=true -DskipTests && \
#Generate fuzzer java code from grammar file and configuration file
echo "Starting Seagull" && \
java -jar target/SGL-jar-with-dependencies.jar -c $2 -g $1 && \
echo "Fuzzer generated" && \
mv Fuzzer.java src/sqlancer/any/Fuzzer.java && \
#Second build with the real Fuzzer code, to get the sqlancer jar
echo "Building SQLancer" && \
mvn clean package -Dskip.SGL=true -DskipTests && \
cd target && \
echo "Starting SQLancer" && \
java -jar sqlancer-jar-with-dependencies.jar -g && \
cd .. && \
echo "Finished, cleaning up" && \
cp src/sqlancer/any/Fuzzer.java.template.txt src/sqlancer/any/Fuzzer.java && \
rm -r grammars/.antlr