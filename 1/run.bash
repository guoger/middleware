#!/bin/bash
TEMP_DIR="$(mktemp -dt results_test_run)"

echo "Starting 126 connection attempts"
for i in {1..126}; do
    ./client $i > $TEMP_DIR/$i &
done
sleep 1
SUCCESS_NUM="$(grep -hc success $TEMP_DIR/* | awk '{s+=$1} END {print s}')"
if [ $SUCCESS_NUM -eq 126 ]; then
    echo "All 126 connection attempts succeeded!"
    rm -Rf $TEMP_DIR
else
    echo "Only $SUCCESS_NUM of 126 attempts succeeded.."
    echo "Please check:"
    grep -L success $TEMP_DIR
fi
