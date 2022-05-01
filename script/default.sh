#!/bin/bash

PROJECT_NAME="bots"
IS_EXE=1
MAIN_CLASS_PATH="$PROJECT_NAME/Main"

BPROF="default"

rm -rf build/$BPROF
mkdir -p build/$BPROF

if [ $IS_EXE -eq 1 ];
then
echo "Main-Class: $MAIN_CLASS_PATH" > build/$BPROF/MANIFEST.txt
else
touch build/$BPROF/MANIFEST.txt
fi

echo "++++++++++++++++++++ COMPILING ++++++++++++++++++++"

mkdir temp_build_dir
cp -r src/* temp_build_dir
find -name "*.java" | grep "temp_build_dir" > build/$BPROF/sources.txt
javac -verbose @build/$BPROF/sources.txt -d build/$BPROF

cd build/$BPROF

find -name "*.class" > bins.txt
jar -cfm $PROJECT_NAME.jar MANIFEST.txt @bins.txt

cd ../..

rm -rf temp_build_dir

echo "+++++++++++++++++++++++++++++++++++++++++++++++++++"

echo ""
echo ""

echo "=============== COMPILATION RESULT ================"

jar tf build/$BPROF/$PROJECT_NAME.jar

echo "==================================================="

if [ $IS_EXE -eq 1 ];
then

echo ""
echo ""

echo "================ RUNNING PROGRAM =================="

cd build/$BPROF
mkdir -p run && cd run
java -jar ../$PROJECT_NAME.jar
cd ../..

echo "==================================================="
fi
