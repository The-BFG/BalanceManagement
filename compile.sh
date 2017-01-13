#!/bin/bash
mkdir -p class
javac -d class -cp "lib/*" -sourcepath src src/BM/BudgetManagement.java 
