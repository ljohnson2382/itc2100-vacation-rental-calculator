#!/bin/bash
# Run script for Vacation Rental Calculator
# ITC2100 Signature Assignment

echo "Starting Vacation Rental Calculator..."
echo

# Check for compiled JAR first
if [ -f "target/vacation-rental-calculator-1.0.0.jar" ]; then
    echo "Running from JAR..."
    java -jar target/vacation-rental-calculator-1.0.0.jar
elif [ -f "target/classes/com/northeastern/itc2100/JVacationRental.class" ]; then
    echo "Running from compiled classes..."
    java -cp target/classes com.northeastern.itc2100.JVacationRental
else
    echo "Application not built yet. Please run ./build.sh first."
    echo
    read -p "Would you like to build now? (y/n): " choice
    if [[ $choice =~ ^[Yy]$ ]]; then
        ./build.sh
        if [ $? -eq 0 ]; then
            echo
            echo "Starting application..."
            java -cp target/classes com.northeastern.itc2100.JVacationRental
        fi
    fi
fi

echo
echo "Application closed."