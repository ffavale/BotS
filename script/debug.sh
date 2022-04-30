#!/bin/bash

PROJECT_NAME="bots"

TEST_PROJECT_NAME="${PROJECT_NAME}_test"
MAIN_CLASS_PATH="$TEST_PROJECT_NAME/TestMain"

BPROF="debug"

rm -rf build/$BPROF
mkdir -p build/$BPROF

echo "Main-Class: $MAIN_CLASS_PATH" > build/$BPROF/MANIFEST.txt

echo "++++++++++++++++++++ COMPILING ++++++++++++++++++++"

mkdir temp_build_dir
cp -rL src/* temp_build_dir
cp -rL test/* temp_build_dir
find -name "*.java" | grep "temp_build_dir" > build/$BPROF/sources.txt
javac -verbose @build/$BPROF/sources.txt -d build/$BPROF

cd build/$BPROF

find -name "*.class" > bins.txt
jar -cfm $TEST_PROJECT_NAME.jar MANIFEST.txt @bins.txt

cd ../..

rm -rf temp_build_dir

echo "+++++++++++++++++++++++++++++++++++++++++++++++++++"

echo ""
echo ""

echo "=============== COMPILATION RESULT ================"

jar tf build/$BPROF/$TEST_PROJECT_NAME.jar

echo "==================================================="

echo ""
echo ""

echo "================ RUNNING PROGRAM =================="

java -jar build/$BPROF/$TEST_PROJECT_NAME.jar

echo "==================================================="
