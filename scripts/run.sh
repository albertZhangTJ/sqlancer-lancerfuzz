#!/bin/bash
cp src/sqlancer/any/Fuzzer.java.template.txt src/sqlancer/any/Fuzzer.java && \
#First build, to get the SGL jar
echo "Building Seagull" && \
rm -f Fuzzer.java && \
mvn clean package -Dskip.main=true -DskipTests && \
#Generate fuzzer java code from grammar file and configuration file
echo "Starting Seagull" && \
java -jar target/SGL-jar-with-dependencies.jar -c $2 -g $1 && \
echo "Fuzzer generated" && \
#cat Fuzzer.java && \
mv Fuzzer.java src/sqlancer/any/Fuzzer.java && \
#Second build with the real Fuzzer code, to get the sqlancer jar
echo "Building SQLancer" && \
mvn clean package -Dskip.SGL=true -DskipTests && \
cd target && \
echo "Starting SQLancer" && \
java -jar sqlancer-jar-with-dependencies.jar -g && \
# java -XX:+FlightRecorder -XX:StartFlightRecording=duration=2h,filename=profile.jfr,settings=profile -jar sqlancer-jar-with-dependencies.jar -g && \
cd .. && \
echo "Finished, cleaning up" && \
cp src/sqlancer/any/Fuzzer.java.template.txt src/sqlancer/any/Fuzzer.java