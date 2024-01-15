#!/bin/sh
for D in src/sqlancer/*; do
    if [ -d "${D}" ]; then
        echo "Counting ${D}"
        cloc "${D}" | grep "Java" 
        echo "\n"
    fi
done