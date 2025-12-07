@echo off
REM Run script for Vacation Rental Calculator
REM ITC2100 Signature Assignment

echo Starting Vacation Rental Calculator...
echo.

REM Check for compiled JAR first
if exist "target\vacation-rental-calculator-1.0.0.jar" (
    echo Running from JAR...
    java -jar target/vacation-rental-calculator-1.0.0.jar
) else if exist "target\classes\com\northeastern\itc2100\JVacationRental.class" (
    echo Running from compiled classes...
    java -cp target/classes com.northeastern.itc2100.JVacationRental
) else (
    echo Application not built yet. Please run build.bat first.
    echo.
    echo Would you like to build now? (Y/N)
    set /p choice=
    if /i "%choice%"=="Y" (
        call build.bat
        if %ERRORLEVEL% EQU 0 (
            echo.
            echo Starting application...
            java -cp target/classes com.northeastern.itc2100.JVacationRental
        )
    )
)

echo.
echo Application closed.
pause