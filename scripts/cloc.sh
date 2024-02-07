#!/bin/bash
cd src/sqlancer
str=$'DBMS,LOC\n'
for D in *; do
    # count 
    if [[ -d "${D}" && "${D}" != "any" && "${D}" != "arangodb" && "${D}" != "common" && "${D}" != "cosmos" && "${D}" != "mongodb" && "${D}" != "timescaledb" && "${D}" != "transformations" ]]; then
        loc=$(cloc "${D}" | grep "Java" | awk '{print $5}')
        echo "Counting $D"
        str+="$D,"
        str+=$loc
        str+=$'\n'
    else
        echo "Skipping $D"
    fi
done
cd ../..
echo "$str" > loc_results.csv

# remove trailing newline
printf "%s" "$(< loc_results.csv)" > tmp
mv tmp loc_results.csv

# Generate box plot 
Rscript scripts/plot_loc.R