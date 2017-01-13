#!/bin/bash
mkdir -p class
#javac -d class/ -cp lib/jdatepicker-1.3.4.jar:lib/swingx-all-1.6.4.jar src/BM/BudgetManagement.java -sourcepath src
javac -d class -cp "lib/*" -sourcepath src src/BM/BudgetManagement.java -Xlint
