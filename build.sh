#!/usr/bin/env bash
mkdir -p target
find src -type f -name "*.java" -print | xargs javac -cp "lib/*" -d target
java -cp "lib/*:target" org.junit.runner.JUnitCore org.xpdojo.bank.AccountTest