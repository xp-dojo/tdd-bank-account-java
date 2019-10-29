#!/usr/bin/env bash
mkdir -p target
find src -type f -name "*.java" -print | xargs javac -cp "lib/*" -d target
