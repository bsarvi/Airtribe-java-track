# Setup Instructions

## JDK Requirements
- **JDK Version**: Java 8 or higher recommended
- **Current Version Used**: Java 11 (or your installed version)

## Installation Steps

### 1. Install Java JDK
Download and install JDK from:
- [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
- [OpenJDK](https://openjdk.org/)

### 2. Verify Installation
Open a terminal/command prompt and run:
```bash
java -version
javac -version
```

Both commands should display version information.

## Compiling and Running

### Option 1: Using Command Line

#### Windows
```cmd
cd D:\Airtribe\LearnTrack\src
javac com\airtribe\learntrack\Main.java
java com.airtribe.learntrack.Main
```

#### Linux/Mac
```bash
cd /path/to/LearnTrack/src
javac com/airtribe/learntrack/Main.java
java com.airtribe.learntrack.Main
```

### Option 2: Using an IDE
1. Open your IDE (IntelliJ IDEA, Eclipse, VS Code)
2. Import the project
3. Right-click on `Main.java`
4. Select "Run Main.main()"

## Hello World Example

Here's a simple Hello World program to verify your Java installation:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

To run:
```bash
javac HelloWorld.java
java HelloWorld
```

Expected output:
```
Hello, World!
```

## Troubleshooting

### "javac is not recognized"
- Ensure JDK bin directory is in your PATH environment variable
- Windows: Add `C:\Program Files\Java\jdk-11\bin` to PATH
- Restart terminal/command prompt after updating PATH

### "Could not find or load main class"
- Ensure you're in the correct directory
- Check that package structure matches directory structure
- Verify class name and file name match exactly
