#!/bin/bash
# Build script for Vacation Rental Calculator
# ITC2100 Signature Assignment

echo "Building Vacation Rental Calculator..."

# Check if Maven is available
if command -v mvn &> /dev/null; then
    echo "Using Maven build..."
    mvn clean compile package
    echo
    echo "Build complete! Run with:"
    echo "java -jar target/vacation-rental-calculator-1.0.0.jar"
else
    echo "Maven not found, using javac..."
    
    # Create output directory
    mkdir -p "target/classes"
    
    # Compile Java files
    javac -d target/classes src/main/java/com/northeastern/itc2100/JVacationRental.java
    
    if [ $? -eq 0 ]; then
        echo "Compilation successful!"
        echo
        echo "Run with:"
        echo "java -cp target/classes com.northeastern.itc2100.JVacationRental"
    else
        echo "Compilation failed!"
        exit 1
    fi
fi

echo
echo "Build script completed."