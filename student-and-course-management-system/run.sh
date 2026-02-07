#!/bin/bash
# LearnTrack - Compile and Run Script for Linux/Mac

echo "========================================"
echo "   LearnTrack Compilation Script"
echo "========================================"
echo ""

cd src

echo "[1/2] Compiling Java files..."
javac com/airtribe/learntrack/Main.java

if [ $? -eq 0 ]; then
    echo "[✓] Compilation successful!"
    echo ""
    echo "[2/2] Running LearnTrack..."
    echo "========================================"
    echo ""
    java com.airtribe.learntrack.Main
else
    echo "[✗] Compilation failed!"
    echo "Please check the error messages above."
    exit 1
fi

echo ""
echo "========================================"
echo "   LearnTrack Execution Complete"
echo "========================================"
