package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.constants.MenuOptions.MenuContext;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);
    private static final Scanner scanner = new Scanner(System.in);
    
    private static final String HEADER_DIVIDER = "═══════════════════════════════════════════";
    private static final String SECTION_DIVIDER = "-------------------------------------------";

    public static void main(String[] args) {
        printWelcomeBanner();
        
        boolean keepRunning = true;
        while (keepRunning) {
            try {
                showMainMenu();
                int userChoice = getUserInput();
                
                MenuOptions selectedOption = MenuOptions.fromCode(userChoice, MenuContext.MAIN);
                
                if (selectedOption == null) {
                    displayError("Please select a valid option from the menu.");
                    continue;
                }
                
                switch (selectedOption) {
                    case MANAGE_STUDENTS:
                        processStudentOperations();
                        break;
                    case MANAGE_COURSES:
                        processCourseOperations();
                        break;
                    case MANAGE_ENROLLMENTS:
                        processEnrollmentOperations();
                        break;
                    case EXIT_APP:
                        keepRunning = false;
                        printGoodbyeMessage();
                        break;
                    default:
                        displayError("Unhandled menu option.");
                }
            } catch (Exception e) {
                displayError("An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }
    
    private static void printWelcomeBanner() {
        System.out.println("\n" + HEADER_DIVIDER);
        System.out.println("    🎓 LEARNTRACK - Education Management System");
        System.out.println(HEADER_DIVIDER + "\n");
    }
    
    private static void printGoodbyeMessage() {
        System.out.println("\n" + SECTION_DIVIDER);
        System.out.println("Thank you for using LearnTrack! See you again soon.");
        System.out.println(SECTION_DIVIDER + "\n");
    }
    
    private static void displayError(String message) {
        System.out.println("⚠️  " + message);
    }
    
    private static void displaySuccess(String message) {
        System.out.println("✓ " + message);
    }

    private static void showMainMenu() {
        System.out.println("\n┌─────── MAIN MENU ───────┐");
        System.out.println("│ 1. Student Management   │");
        System.out.println("│ 2. Course Management    │");
        System.out.println("│ 3. Enrollment Management│");
        System.out.println("│ 4. Exit Application     │");
        System.out.println("└─────────────────────────┘");
        System.out.print("→ Select an option: ");
    }

    private static void processStudentOperations() {
        boolean returnToMain = false;
        while (!returnToMain) {
            try {
                showStudentMenu();
                int userChoice = getUserInput();
                
                MenuOptions option = MenuOptions.fromCode(userChoice, MenuContext.STUDENT);
                
                if (option == null) {
                    displayError("Invalid choice. Please try again.");
                    continue;
                }
                
                switch (option) {
                    case CREATE_STUDENT:
                        registerNewStudent();
                        break;
                    case LIST_STUDENTS:
                        displayAllStudents();
                        break;
                    case FIND_STUDENT:
                        lookupStudentById();
                        break;
                    case DISABLE_STUDENT:
                        deactivateStudentAccount();
                        break;
                    case GO_BACK:
                        returnToMain = true;
                        break;
                    default:
                        displayError("Option not implemented.");
                }
            } catch (Exception e) {
                displayError(e.getMessage());
            }
        }
    }

    private static void showStudentMenu() {
        System.out.println("\n┌─── STUDENT OPERATIONS ───┐");
        System.out.println("│ 1. Register New Student  │");
        System.out.println("│ 2. Display All Students  │");
        System.out.println("│ 3. Search by ID          │");
        System.out.println("│ 4. Deactivate Account    │");
        System.out.println("│ 5. Return to Main Menu   │");
        System.out.println("└──────────────────────────┘");
        System.out.print("→ Choose operation: ");
    }

    private static void registerNewStudent() throws InvalidInputException {
        System.out.println("\n--- Register New Student ---");
        
        System.out.print("First Name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Email (optional - press Enter to skip): ");
        String email = scanner.nextLine().trim();
        email = email.isEmpty() ? null : email;

        System.out.print("Batch/Cohort: ");
        String batch = scanner.nextLine().trim();

        studentService.addStudent(firstName, lastName, email, batch);
    }

    private static void displayAllStudents() {
        ArrayList<Student> allStudents = studentService.listAllStudents();
        
        if (allStudents.isEmpty()) {
            System.out.println("\n📋 No students registered in the system.");
            return;
        }
        
        System.out.println("\n" + SECTION_DIVIDER);
        System.out.println("  REGISTERED STUDENTS (Total: " + allStudents.size() + ")");
        System.out.println(SECTION_DIVIDER);
        for (Student student : allStudents) {
            System.out.println("  " + student);
        }
        System.out.println(SECTION_DIVIDER);
    }

    private static void lookupStudentById() {
        try {
            System.out.print("\nEnter Student ID to search: ");
            int studentId = getUserInput();
            Student foundStudent = studentService.findStudentById(studentId);
            System.out.println("\n✓ Student Found:");
            System.out.println("  " + foundStudent);
        } catch (EntityNotFoundException e) {
            displayError(e.getMessage());
        }
    }

    private static void deactivateStudentAccount() {
        try {
            System.out.print("\nEnter Student ID to deactivate: ");
            int studentId = getUserInput();
            studentService.deactivateStudent(studentId);
        } catch (EntityNotFoundException e) {
            displayError(e.getMessage());
        }
    }

    private static void processCourseOperations() {
        boolean returnToMain = false;
        while (!returnToMain) {
            try {
                showCourseMenu();
                int userChoice = getUserInput();
                
                MenuOptions option = MenuOptions.fromCode(userChoice, MenuContext.COURSE);
                
                if (userChoice == 4) {
                    returnToMain = true;
                    continue;
                }
                
                if (option == null) {
                    displayError("Invalid choice. Please try again.");
                    continue;
                }
                
                switch (option) {
                    case CREATE_COURSE:
                        createNewCourse();
                        break;
                    case LIST_COURSES:
                        displayAllCourses();
                        break;
                    case MODIFY_COURSE_STATUS:
                        modifyCourseAvailability();
                        break;
                    default:
                        displayError("Option not implemented.");
                }
            } catch (Exception e) {
                displayError(e.getMessage());
            }
        }
    }

    private static void showCourseMenu() {
        System.out.println("\n┌─── COURSE OPERATIONS ────┐");
        System.out.println("│ 1. Create New Course     │");
        System.out.println("│ 2. Display All Courses   │");
        System.out.println("│ 3. Toggle Availability   │");
        System.out.println("│ 4. Return to Main Menu   │");
        System.out.println("└──────────────────────────┘");
        System.out.print("→ Choose operation: ");
    }

    private static void createNewCourse() throws InvalidInputException {
        System.out.println("\n--- Create New Course ---");
        
        System.out.print("Course Name: ");
        String courseName = scanner.nextLine().trim();

        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Duration (in weeks): ");
        int durationWeeks = getUserInput();

        courseService.addCourse(courseName, description, durationWeeks);
    }

    private static void displayAllCourses() {
        ArrayList<Course> allCourses = courseService.listAllCourses();
        
        if (allCourses.isEmpty()) {
            System.out.println("\n📚 No courses available in the catalog.");
            return;
        }
        
        System.out.println("\n" + SECTION_DIVIDER);
        System.out.println("  COURSE CATALOG (Total: " + allCourses.size() + ")");
        System.out.println(SECTION_DIVIDER);
        for (Course course : allCourses) {
            System.out.println("  " + course);
        }
        System.out.println(SECTION_DIVIDER);
    }

    private static void modifyCourseAvailability() {
        try {
            System.out.print("\nEnter Course ID: ");
            int courseId = getUserInput();
            courseService.toggleCourseStatus(courseId);
        } catch (EntityNotFoundException e) {
            displayError(e.getMessage());
        }
    }

    private static void processEnrollmentOperations() {
        boolean returnToMain = false;
        while (!returnToMain) {
            try {
                showEnrollmentMenu();
                int userChoice = getUserInput();
                
                MenuOptions option = MenuOptions.fromCode(userChoice, MenuContext.ENROLLMENT);
                
                if (userChoice == 4) {
                    returnToMain = true;
                    continue;
                }
                
                if (option == null) {
                    displayError("Invalid choice. Please try again.");
                    continue;
                }
                
                switch (option) {
                    case REGISTER_ENROLLMENT:
                        registerStudentInCourse();
                        break;
                    case VIEW_ENROLLMENTS:
                        displayStudentEnrollments();
                        break;
                    case CHANGE_ENROLLMENT_STATUS:
                        modifyEnrollmentStatus();
                        break;
                    default:
                        displayError("Option not implemented.");
                }
            } catch (Exception e) {
                displayError(e.getMessage());
            }
        }
    }

    private static void showEnrollmentMenu() {
        System.out.println("\n┌── ENROLLMENT OPERATIONS ──┐");
        System.out.println("│ 1. Enroll Student        │");
        System.out.println("│ 2. View Enrollments      │");
        System.out.println("│ 3. Update Status         │");
        System.out.println("│ 4. Return to Main Menu   │");
        System.out.println("└──────────────────────────┘");
        System.out.print("→ Choose operation: ");
    }

    private static void registerStudentInCourse() {
        try {
            System.out.println("\n--- Enroll Student in Course ---");
            
            System.out.print("Student ID: ");
            int studentId = getUserInput();

            System.out.print("Course ID: ");
            int courseId = getUserInput();

            enrollmentService.enrollStudent(studentId, courseId);
        } catch (EntityNotFoundException | InvalidInputException e) {
            displayError(e.getMessage());
        }
    }

    private static void displayStudentEnrollments() {
        try {
            System.out.print("\nEnter Student ID: ");
            int studentId = getUserInput();

            ArrayList<Enrollment> studentEnrollments = enrollmentService.getStudentEnrollments(studentId);
            
            if (studentEnrollments.isEmpty()) {
                System.out.println("\n📝 No enrollments found for student #" + studentId);
                return;
            }
            
            System.out.println("\n" + SECTION_DIVIDER);
            System.out.println("  ENROLLMENTS FOR STUDENT #" + studentId);
            System.out.println(SECTION_DIVIDER);
            for (Enrollment enrollment : studentEnrollments) {
                System.out.println("  " + enrollment);
            }
            System.out.println(SECTION_DIVIDER);
        } catch (EntityNotFoundException e) {
            displayError(e.getMessage());
        }
    }

    private static void modifyEnrollmentStatus() {
        try {
            System.out.print("\nEnter Enrollment ID: ");
            int enrollmentId = getUserInput();

            System.out.println("\nSelect New Status:");
            System.out.println("  1. ACTIVE (In Progress)");
            System.out.println("  2. COMPLETED (Finished)");
            System.out.println("  3. CANCELLED (Withdrawn)");
            System.out.print("→ Enter choice: ");
            int statusChoice = getUserInput();

            EnrollmentStatus newStatus = EnrollmentStatus.fromChoice(statusChoice);
            
            if (newStatus == null) {
                displayError("Invalid status selection.");
                return;
            }

            enrollmentService.updateEnrollmentStatus(enrollmentId, newStatus);
        } catch (EntityNotFoundException e) {
            displayError(e.getMessage());
        }
    }

    private static int getUserInput() {
        while (true) {
            try {
                String userInput = scanner.nextLine().trim();
                int parsedValue = Integer.parseInt(userInput);
                return parsedValue;
            } catch (NumberFormatException e) {
                System.out.print("⚠️  Invalid input. Please enter a valid number: ");
            }
        }
    }
}
