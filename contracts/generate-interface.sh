#!/usr/bin/env bash
DIR=/Users/mroon/Documents/projects/contracts/deployment/build

for file in /Users/mroon/Documents/projects/Web3jSample/contracts/*.bin
do
  filename=$(basename -- "$file")
  name="${filename%%\.*}"
  echo $name
#  cmd [option] "$file" >> results.out
done