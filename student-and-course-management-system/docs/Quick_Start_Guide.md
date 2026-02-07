# LearnTrack Quick Start Guide

## Running the Application

### Method 1: Using the Run Script (Recommended)
**Windows:**
```cmd
cd D:\Airtribe\LearnTrack
run.bat
```

**Linux/Mac:**
```bash
cd /path/to/LearnTrack
chmod +x run.sh
./run.sh
```

### Method 2: Manual Compilation and Execution
**Windows:**
```cmd
cd D:\Airtribe\LearnTrack\src
javac com\airtribe\learntrack\Main.java
java com.airtribe.learntrack.Main
```

**Linux/Mac:**
```bash
cd /path/to/LearnTrack/src
javac com/airtribe/learntrack/Main.java
java com.airtribe.learntrack.Main
```

## Sample Usage Flow

### 1. Add a Course
```
Main Menu → 2 (Course Management)
→ 1 (Add New Course)
→ Enter course details
→ Course added with ID: 1
```

### 2. Add a Student
```
Main Menu → 1 (Student Management)
→ 1 (Add New Student)
→ Enter student details
→ Student added with ID: 1
```

### 3. Enroll Student in Course
```
Main Menu → 3 (Enrollment Management)
→ 1 (Enroll Student in Course)
→ Enter Student ID: 1
→ Enter Course ID: 1
→ Student enrolled successfully
```

### 4. View Student Enrollments
```
Main Menu → 3 (Enrollment Management)
→ 2 (View Student Enrollments)
→ Enter Student ID: 1
→ All enrollments displayed
```

## Menu Structure

### Main Menu
1. Student Management
2. Course Management
3. Enrollment Management
4. Exit

### Student Management
1. Add New Student
2. View All Students
3. Search Student by ID
4. Deactivate Student
5. Back to Main Menu

### Course Management
1. Add New Course
2. View All Courses
3. Toggle Course Status
4. Back to Main Menu

### Enrollment Management
1. Enroll Student in Course
2. View Student Enrollments
3. Update Enrollment Status
4. Back to Main Menu

## Tips for Testing

### Test Student Management
1. Add multiple students with different batches
2. Try adding a student without email (optional field)
3. Search for specific students by ID
4. Deactivate a student and verify they can't be enrolled

### Test Course Management
1. Add courses with varying durations
2. Toggle course status and verify enrollment restrictions

### Test Enrollment Management
1. Enroll active students in active courses
2. Try enrolling inactive students (should fail)
3. Update enrollment status (Active → Completed)
4. View all enrollments for a student

### Test Error Handling
1. Enter invalid menu options
2. Try to search for non-existent IDs
3. Enter non-numeric input where numbers are expected
4. Try enrolling with invalid student/course IDs

## Common Issues and Solutions

### "javac is not recognized"
**Problem**: Java compiler not in PATH
**Solution**: Add JDK bin directory to your system PATH

### "Could not find or load main class"
**Problem**: Running from wrong directory
**Solution**: Ensure you're in the `src` directory when running

### Compilation Errors
**Problem**: Java version mismatch
**Solution**: Ensure JDK 8 or higher is installed

## Project Structure Reference

```
LearnTrack/
├── src/com/airtribe/learntrack/
│   ├── Main.java              # Start here!
│   ├── entity/                # Data models
│   ├── repository/            # Data storage
│   ├── service/               # Business logic
│   ├── exception/             # Custom exceptions
│   ├── util/                  # Utilities
│   ├── constants/             # Constants
│   └── enums/                 # Enumerations
├── docs/                      # Documentation
├── README.md                  # Main documentation
├── run.bat                    # Windows run script
└── run.sh                     # Unix/Linux run script
```

## Next Steps

1. **Run the application** using one of the methods above
2. **Test all features** using the sample flow
3. **Read the documentation**:
   - `README.md` for overview
   - `docs/Design_Notes.md` for architecture
   - `docs/JVM_Basics.md` for Java fundamentals
4. **Customize and extend** as needed

## Getting Help

If you encounter issues:
1. Check `docs/Setup_Instructions.md` for installation help
2. Verify Java version: `java -version`
3. Ensure compilation succeeded (no error messages)
4. Check that you're in the correct directory

---

**Happy Learning! 🚀**
