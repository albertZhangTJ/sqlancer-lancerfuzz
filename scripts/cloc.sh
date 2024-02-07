#!/bin/bash
cd src/sqlancer
str=$'DBMS,LOC\n'
for D in *; do
    # count 
    if [ -d "${D}" ]; then
        loc=$(cloc "${D}" | grep "Java" | awk '{print $5}')
        # There are a couple of folders that are smaller than 1000 LOCs
        # They are either not DBMS-spceific code (e.g. any, transformations)
        # Or are simply wraps over another DBMS (cosmos, timescale, etc.)
        # Therefore those folders will be ignored
        if [ "$loc" -gt 1000 ]; then
            echo "Counting $D"
            str+="$D,"
            str+=$loc
            str+=$'\n'
        else
            echo "Skipping $D"
        fi
    fi
done
cd ../..
echo "$str" > loc_results.csv

# remove trailing newline
printf "%s" "$(< loc_results.csv)" > tmp
mv tmp loc_results.csv