@echo off
REM Build script for Vacation Rental Calculator
REM ITC2100 Signature Assignment

echo Building Vacation Rental Calculator...

REM Check if Maven is available
mvn --version >nul 2>&1
if %ERRORLEVEL% EQU 0 (
    echo Using Maven build...
    mvn clean compile package
    echo.
    echo Build complete! Run with:
    echo java -jar target/vacation-rental-calculator-1.0.0.jar
) else (
    echo Maven not found, using javac...
    
    REM Create output directory
    if not exist "target\classes" mkdir "target\classes"
    
    REM Compile Java files
    javac -d target/classes src/main/java/com/northeastern/itc2100/JVacationRental.java
    
    if %ERRORLEVEL% EQU 0 (
        echo Compilation successful!
        echo.
        echo Run with:
        echo java -cp target/classes com.northeastern.itc2100.JVacationRental
    ) else (
        echo Compilation failed!
        exit /b 1
    )
)

echo.
echo Build script completed.
pause