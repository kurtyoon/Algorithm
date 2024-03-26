#!/bin/bash

usage() {
echo "Usage: $0 <source_file>"
echo "Example: $0 source.cpp"
exit 1
    }

if [ $# -ne 1 ]; then
    usage
fi

source_file=$1

if ! g++ -std=c++17 -Wall "$source_file" -o result; then
    echo "Compilation failed."
    exit 1
fi

./result

rm -rf result