#!/bin/bash
mvn -DskipTests package
for file in grammars/test/*.sgl; do
    if [ -e "$file" ]; then
        echo "Testing $file"
        java -jar target/lancerfuzz-jar-with-dependencies.jar -c grammars/test.json -g $file
        mv Fuzzer.java rig/
        cd rig
        sed -i '/package sqlancer.any/d' Fuzzer.java
        sed -i '/import sqlancer.SQLConnection/d' Fuzzer.java
        javac *.java
        java Driver
        rm *.class
        rm Fuzzer.java
        cd ..
    fi
done