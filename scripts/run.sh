#!/bin/bash
cp src/sqlancer/any/Fuzzer.java.template.txt src/sqlancer/any/Fuzzer.java && \
#First build, to get the lancerfuzz jar
echo "Building LancerBuild" && \
mvn -DskipTests -q clean package && \
#Generate fuzzer java code from grammar file and configuration file
echo "Starting LancerBuild" && \
java -jar target/lancerfuzz-jar-with-dependencies.jar -c $2 -g $1 && \
echo "Fuzzer generated" && \
mv Fuzzer.java src/sqlancer/any/Fuzzer.java && \
#Second build with the real Fuzzer code, to get the sqlancer jar
echo "Building SQLancer" && \
mvn -DskipTests -q clean package && \
cd target && \
echo "Starting SQLancer" && \
java -jar sqlancer-jar-with-dependencies.jar -g | tee log/stdout.log && \
cd .. && \
echo "Finished, cleaning up" && \
cp src/sqlancer/any/Fuzzer.java.template.txt src/sqlancer/any/Fuzzer.java && \
rm -r grammars/.antlr