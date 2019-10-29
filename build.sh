#!/usr/bin/env bash
mkdir -p target
find src -type f -name "*.java" -print | xargs javac -cp "lib/*" -d target && java -cp "lib/*:target" org.junit.runner.JUnitCore $(find target -name "*Test.class" | tr "/" "." | sed -e "s/^target.//" -e "s/.class//")