#!/bin/bash

cp src/sqlancer/any/Fuzzer.java.template.txt src/sqlancer/any/Fuzzer.java

#First build, to get the dsqlancer jar
mvn -DskipTests clean package

#Generate fuzzer java code from grammar file and configuration file
java -jar target/dsqlancer-jar-with-dependencies.jar -c $2 -g $1

mv Fuzzer.java src/sqlancer/any/Fuzzer.java

#Second build with the real Fuzzer code, to get the sqlancer jar
mvn -DskipTests clean package

cd target 


java -jar sqlancer-jar-with-dependencies.jar -g 

cd ..