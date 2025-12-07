# Vacation Rental Calculator

**Course**: ITC2100 - Introduction to Programming Java  
**Assignment**: Signature Assignment  
**Author**: Loyd Johnson (johnson.loy@northeastern.edu)  
**Institution**: Northeastern University

## Project Overview
A professional Java Swing application for calculating vacation rental costs with additional services, taxes, and note-taking functionality. This project extends the JResortCalculator concept from Programming Exercise 4 (page 584) as part of the ITC2100 Signature Assignment.

## Features
- **Base Rental Calculation**: Calculate costs based on number of nights and base rate
- **Additional Services**: 
  - Windsurfing: $50.75
  - Snorkeling: $25.65
  - Horseback Riding: $75.89
  - Resort Shuttle: $15.00 per person
- **Tax Calculation**: Automatic 5.5% resort tax calculation
- **Note Taking**: Save resort stay notes with timestamps
- **Input Validation**: Comprehensive error handling and user-friendly messages
- **Professional GUI**: Clean Swing interface with proper layout management

## Project Structure
```
vacation-rental-calculator/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/northeastern/itc2100/
│   │   │       └── JVacationRental.java
│   │   └── resources/
│   └── test/
│       └── java/
│           └── com/northeastern/itc2100/
├── docs/
├── pom.xml
├── .gitignore
└── README.md
```

## System Requirements
- **Java**: JDK 8 or higher
- **Build Tool**: Maven 3.6+ (recommended for full project features)
- **Operating System**: Windows/Mac/Linux
- **Memory**: Minimum 512MB RAM
- **Storage**: 100MB free disk space

### Maven Installation (Optional but Recommended)
To use the full professional build system and run tests:

**Windows:**
```powershell
# Option 1: Using Chocolatey (requires admin privileges)
choco install maven

# Option 2: Manual installation
# Download from https://maven.apache.org/download.cgi
# Extract and add bin directory to PATH environment variable
```

**Mac:**
```bash
brew install maven
```

**Linux:**
```bash
sudo apt install maven    # Ubuntu/Debian
sudo yum install maven     # RedHat/CentOS
```

## Quick Start

### Option 1: Using Maven (Recommended)
```bash
# Navigate to project directory
cd vacation-rental-calculator

# Compile the project
mvn compile

# Run tests (requires Maven)
mvn test

# Run the application
mvn exec:java -Dexec.mainClass="com.northeastern.itc2100.JVacationRental"

# Or build and run JAR
mvn package
java -jar target/vacation-rental-calculator-1.0.0.jar
```

### Option 2: Using javac directly
```bash
# Compile
javac -d target/classes src/main/java/com/northeastern/itc2100/JVacationRental.java

# Run
java -cp target/classes com.northeastern.itc2100.JVacationRental
```

## Usage Guide

### Basic Operation
1. **Enter Number of Nights**: Input 1-365 nights
2. **Select Services**: Check desired additional services
3. **Resort Shuttle**: If selected, specify number of people (1-20)
4. **Calculate Total**: Click to see pricing breakdown
5. **Add Notes**: Enter resort stay information
6. **Save Notes**: Persist notes to file with timestamp
7. **Clear All**: Reset form for new calculation

### Sample Calculation
```
3 nights @ $200/night = $600.00
Windsurfing = $50.75
Snorkeling = $25.65
Subtotal = $676.40
Resort Tax (5.5%) = $37.20
Total = $713.60
```

### Input Validation
- Number validation with range checking
- Empty field detection
- User-friendly error messages
- Graceful error recovery

## Architecture

### Design Patterns
- **MVC Pattern**: Clear separation of UI, logic, and data
- **Event-Driven**: Swing ActionListener implementation
- **Error Handling**: Comprehensive try-catch with user feedback

### Key Classes
- `JVacationRental`: Main application class extending JFrame
  - GUI component initialization and layout
  - Event handling and user interactions
  - Business logic for calculations
  - File I/O for note persistence

## Development

### Building from Source
```bash
# Clone/download project
# Navigate to project directory
cd vacation-rental-calculator

# Clean and compile
mvn clean compile

# Run tests (if any)
mvn test

# Package application
mvn package
```

### IDE Setup
1. Import as Maven project
2. Set JDK 8+ as project SDK
3. Run `com.northeastern.itc2100.JVacationRental.main()`

## AI Tool Usage and Enhancement Documentation

### Core Assignment Implementation
The core JVacationRental.java application was developed following traditional programming practices learned in ITC2100, implementing all required features:
- Java Swing GUI components (Chapter 14)
- Double data type for currency calculations
- Service pricing and tax calculations
- File I/O for notes functionality
- Comprehensive error handling with user-friendly messages

### AI-Enhanced Professional Structure (Beyond Course Requirements)

**Learning Objective**: While the course covered basic Java project organization, I used AI tools (GitHub Copilot) to learn and implement industry-standard project practices not covered in our curriculum.

#### 1. Maven Project Structure
- **AI Contribution**: I learned Maven directory conventions (`src/main/java`, `src/test/java`) through consultation with Microsoft Copilot and Claude
- **Enhancement Value**: Professional project organization used in industry
- **Learning Outcome**: I gained understanding of how real-world Java projects are structured
- **Files Created**: `pom.xml` with proper dependencies, plugins, and build configuration

#### 2. Unit Testing Framework (JUnit)
- **AI Contribution**: I learned JUnit testing framework and test-driven development concepts through educational consultation with Microsoft Copilot and Claude
- **Enhancement Value**: Automated testing for code reliability and professional development practices
- **Learning Outcome**: I gained understanding of how to write and structure unit tests for business logic validation
- **Files Created**: `JVacationRentalTest.java` with comprehensive test coverage

#### 3. Maven Installation and Configuration (Learning Process)
- **Initial Challenge**: Maven was not installed on my system, preventing execution of professional build commands
- **Learning Process with AI Assistance** (following CPS AI Guidance for acceptable use):
  1. **Problem Identification**: I used AI as a learning resource to understand what `mvn` command errors indicated and Maven installation requirements
  2. **Installation Research**: I consulted Microsoft Copilot and Claude for educational explanations of Maven installation steps for Windows
  3. **Environment Configuration**: I used Microsoft Copilot and Claude to help me understand PowerShell commands for setting up Maven environment variables
  4. **Dependency Resolution**: When tests initially failed due to Java version compatibility, I consulted Microsoft Copilot and Claude to learn about Maven/JUnit dependency issues and understand possible solutions

**PowerShell Commands Used (Learned Through AI Consultation)**:
```powershell
# Create tools directory
New-Item -ItemType Directory -Path "C:\tools" -Force

# Extract Maven from Downloads
Expand-Archive -Path "$env:USERPROFILE\Downloads\apache-maven-3.9.11-bin.zip" -DestinationPath "C:\tools" -Force

# Set environment variables
[Environment]::SetEnvironmentVariable("MAVEN_HOME", "C:\tools\apache-maven-3.9.11", "User")
$currentPath = [Environment]::GetEnvironmentVariable("Path", "User")
[Environment]::SetEnvironmentVariable("Path", "$currentPath;C:\tools\apache-maven-3.9.11\bin", "User")

# Temporary PATH update for current session
$env:PATH += ";C:\tools\apache-maven-3.9.11\bin"

# Verify installation
mvn --version
```

#### 4. Professional Portfolio Integration (Version Control)
- **Portfolio Development**: Upon assignment completion, initialized Git repository and pushed to GitHub for professional portfolio
- **Repository Setup**: Created public repository to showcase academic work and professional development practices
- **Documentation Value**: Demonstrates transition from academic coursework to industry-standard development workflows

**Git Repository Setup Commands**:
```powershell
# Initialize local repository
cd "vacation-rental-calculator"
git init

# Add all project files
git add .

# Create initial commit with comprehensive description
git commit -m "Initial commit: ITC2100 Signature Assignment - JVacationRental

- Complete Swing GUI application for vacation rental calculations  
- All assignment requirements implemented per rubric
- Professional Maven project structure with testing
- Comprehensive documentation with AI usage transparency
- Academic integrity maintained (80% textbook, 20% AI-enhanced learning)
- Professional development practices beyond course scope"

# Create GitHub repository and push
gh repo create itc2100-vacation-rental-calculator --public --description "Academic Java Swing project demonstrating GUI development with professional practices"
git remote add origin https://github.com/[username]/itc2100-vacation-rental-calculator.git
git branch -M main
git push -u origin main
```

#### 5. Assignment Submission Preparation
- **Final Testing**: Executed comprehensive test suite to verify all functionality after enhancements
- **Code Quality Verification**: Confirmed all 9 unit tests pass with zero failures
- **Documentation Review**: Ensured all AI usage properly documented and compliant with academic standards
- **Submission Package**: Created professional zip archive for assignment submission

**Assignment Packaging Commands**:
```powershell
# Navigate to course directory
cd "c:\Users\loydj\OneDrive - Northeastern University\04_Courses\06_Fall_2025_ITC2100"

# Create professional submission archive
Compress-Archive -Path "Signature Assignment" -DestinationPath "Loyd_Johnson_ITC2100_Signature_Assignment.zip" -Force
```

**Submission Contents**:
- `vacation-rental-calculator/` - Complete project with source code, tests, and documentation
- `CPS-AI-Guidance.pdf` - University AI policy reference used for compliance
- `ITC 2100 Introduction to Programming Java-Signature-Assignment(PLO).pdf` - Assignment requirements used for verification

#### 4. Project Completion Verification (Quality Assurance with AI)
- **Requirement Review**: I used AI to systematically review assignment rubric and ensure all functional requirements were implemented
- **Academic Compliance**: I consulted CPS AI Guidance document to verify all AI usage remained within acceptable academic standards  
- **Code Quality**: AI assisted me in identifying areas for improvement (magic number elimination, error handling completeness)
- **Documentation Review**: I ensured all AI usage was properly documented and transparent

**Problem Resolution Learning with AI**:
- **Initial Error**: Java version compatibility issues (Java 24 runtime with Java 8 target)
- **AI Learning**: I used AI to understand Maven Surefire plugin dependency resolution conflicts
- **Solution Applied**: I learned to update pom.xml to use Java 11 target with `--release` flag instead of separate source/target
- **Result Achieved**: I successfully executed all 9 unit tests (Tests run: 9, Failures: 0, Errors: 0)

#### 4. Package Naming Conventions
- **AI Contribution**: I learned reverse domain naming (`com.northeastern.itc2100`)
- **Enhancement Value**: Follows Java industry standards for package organization
- **Learning Outcome**: I gained understanding of professional Java package hierarchies

#### 5. Cross-Platform Build Scripts
- **AI Contribution**: I created both Windows (.bat) and Unix (.sh) build scripts
- **Enhancement Value**: Project works across different operating systems
- **Learning Outcome**: I gained understanding of cross-platform development practices

#### 6. Professional Documentation
- **AI Contribution**: I learned comprehensive README structure and technical writing patterns
- **Enhancement Value**: Clear project documentation for team collaboration
- **Learning Outcome**: I gained knowledge of professional documentation standards in software development

#### 7. Version Control Integration
- **AI Contribution**: I learned Java-specific .gitignore patterns for build artifacts and IDE files
- **Enhancement Value**: Proper version control setup for collaborative development
- **Learning Outcome**: While I am experienced with Git and GitHub, I used AI to learn specific Java build files and artifacts that should be excluded (target/, *.class, Maven-specific files, IDE configurations)

### Build Process Documentation

#### Standard Course Approach (Manual Compilation)
```bash
# Basic compilation as taught in course
javac -d target/classes src/main/java/com/northeastern/itc2100/JVacationRental.java
java -cp target/classes com.northeastern.itc2100.JVacationRental
```

#### Enhanced Professional Approach (AI-Learned)
```bash
# Maven-based build (learned through AI assistance)
mvn clean compile package
java -jar target/vacation-rental-calculator-1.0.0.jar

# Cross-platform scripts (AI-enhanced)
./build.sh && ./run.sh    # Unix/Linux
build.bat && run.bat      # Windows
```

### AI Tool Integration Summary
- **Primary Tool**: Microsft Copilot for learning industry practices and problem-solving guidance
- **Usage Pattern**: Asked AI to explain professional Java project structure and troubleshoot technical issues
- **Learning Focus**: Industry standards not covered in introductory course, with AI providing educational explanations
- **Result**: Enhanced project that demonstrates both course learning and self-directed professional skill development through AI-assisted learning

### Academic Integrity Note
All core assignment requirements were implemented using course materials and traditional programming knowledge. AI tools were used exclusively for learning professional enhancement practices that extend beyond the course curriculum, providing valuable exposure to industry-standard development workflows.

## Course Information
- **Course**: ITC2100 - Introduction to Programming Java
- **Assignment**: Signature Assignment (Programming Learning Outcomes)
- **Semester**: Fall 2025
- **Institution**: Northeastern University

## Academic Integrity and AI Usage

This project demonstrates **acceptable use of AI** as a learning tool in accordance with academic integrity policies:

### Core Development Approach (80% / 20% Split)
- **80% Textbook-Based Knowledge**: All fundamental Java concepts, Swing GUI components, file I/O, exception handling, and core programming logic are directly from the Joyce Farrell Java Programming 10th Edition textbook
- **20% AI-Enhanced Learning**: Advanced concepts beyond textbook scope (Maven build system, JUnit testing framework, modern Java APIs, professional project structure) were learned through educational consultation with **Microsoft Copilot and Claude AI assistants**

### Specific AI Tools Used
- **Microsoft Copilot**: Used for understanding Maven configuration, build automation concepts, and modern Java syntax explanations
- **Claude AI Assistant**: Consulted for learning JUnit testing patterns, professional project organization, troubleshooting complex dependency issues, assignment requirement analysis, and rubric compliance verification

### AI Usage Process
1. **Requirement Analysis**: I used AI to review assignment details and rubric to ensure comprehensive coverage of all requirements
2. **Compliance Verification**: I consulted CPS AI Guidance document to maintain acceptable use standards throughout the project
3. **Educational Consultation**: AI served as my learning resource for concepts beyond textbook scope
4. **Quality Assurance**: AI assisted me in reviewing project completeness against assignment criteria

### Reference Documents
For transparency and verification purposes, the following source documents used for AI-assisted requirement analysis and compliance checking are included in the assignment folder:
- **`ITC 2100 Introduction to Programming Java-Signature-Assignment(PLO).pdf`**: Original assignment document with requirements and rubric used for completeness verification
- **`CPS-AI-Guidance.pdf`**: University AI usage policy consulted to ensure all AI assistance remained within acceptable academic standards

### Learning vs. Task Completion
- **AI Role**: Educational resource for understanding concepts beyond textbook scope and verifying assignment completeness
- **My Work**: All code written by me based on learned understanding
- **Implementation**: I designed the solution architecture and business logic
- **Problem Solving**: My analysis and decision-making throughout development process
- **Compliance**: I followed CPS AI Guidance to ensure appropriate academic use

### Textbook Alignment
This project primarily uses concepts from:
- **Chapter 14: Introduction to Swing Components** (90% of GUI implementation)
- **Chapter 13: File I/O** (file saving functionality)
- **Chapter 12: Exception Handling** (error management)
- **Chapters 2-8: Java Fundamentals** (core programming concepts)

## Academic Compliance
- **CLO1**: Algorithm design through clear calculation logic implementation
- **CLO2**: Programming constructs via GUI event handling and decision structures
- **CLO3**: Comprehensive error handling with graceful recovery and user feedback
- **CLO4**: Professional Swing GUI components with proper layout management
- **CLO5**: Strategic AI tool usage for learning industry practices beyond course scope

## File Output
- **resort_notes.txt**: Timestamped notes saved in project root
- **Log format**: Date, time, user notes with separators

## Troubleshooting

### Common Issues
1. **Compilation Errors**: Verify JDK installation and JAVA_HOME
2. **Runtime Issues**: Check Java version compatibility
3. **GUI Problems**: Verify display settings and permissions
4. **File Save Errors**: Check directory write permissions

## License
This is an academic project for educational purposes only.