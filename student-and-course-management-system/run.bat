@echo off
REM LearnTrack - Compile and Run Script for Windows

echo ========================================
echo    LearnTrack Compilation Script
echo ========================================
echo.

cd src

echo [1/2] Compiling Java files...
javac com\airtribe\learntrack\Main.java

if %ERRORLEVEL% EQU 0 (
    echo [✓] Compilation successful!
    echo.
    echo [2/2] Running LearnTrack...
    echo ========================================
    echo.
    java com.airtribe.learntrack.Main
) else (
    echo [✗] Compilation failed!
    echo Please check the error messages above.
    pause
    exit /b 1
)

echo.
echo ========================================
echo    LearnTrack Execution Complete
echo ========================================
pause
