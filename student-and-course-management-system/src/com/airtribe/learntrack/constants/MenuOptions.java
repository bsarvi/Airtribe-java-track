package com.airtribe.learntrack.constants;

public enum MenuOptions {
    // Main Menu Options
    MANAGE_STUDENTS(1, "Student Management"),
    MANAGE_COURSES(2, "Course Management"),
    MANAGE_ENROLLMENTS(3, "Enrollment Management"),
    EXIT_APP(4, "Exit Application"),
    
    // Student Operations
    CREATE_STUDENT(1, "Register New Student"),
    LIST_STUDENTS(2, "Display All Students"),
    FIND_STUDENT(3, "Search Student by ID"),
    DISABLE_STUDENT(4, "Deactivate Student Account"),
    GO_BACK(5, "Return to Main Menu"),
    
    // Course Operations
    CREATE_COURSE(1, "Create New Course"),
    LIST_COURSES(2, "Display All Courses"),
    MODIFY_COURSE_STATUS(3, "Toggle Course Availability"),
    
    // Enrollment Operations
    REGISTER_ENROLLMENT(1, "Enroll Student in Course"),
    VIEW_ENROLLMENTS(2, "View Student's Enrollments"),
    CHANGE_ENROLLMENT_STATUS(3, "Update Enrollment Status");
    
    private final int code;
    private final String description;
    
    MenuOptions(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static MenuOptions fromCode(int code, MenuContext context) {
        for (MenuOptions option : values()) {
            if (option.code == code && isValidInContext(option, context)) {
                return option;
            }
        }
        return null;
    }
    
    private static boolean isValidInContext(MenuOptions option, MenuContext context) {
        switch (context) {
            case MAIN:
                return option == MANAGE_STUDENTS || option == MANAGE_COURSES || 
                       option == MANAGE_ENROLLMENTS || option == EXIT_APP;
            case STUDENT:
                return option == CREATE_STUDENT || option == LIST_STUDENTS || 
                       option == FIND_STUDENT || option == DISABLE_STUDENT || option == GO_BACK;
            case COURSE:
                return option == CREATE_COURSE || option == LIST_COURSES || 
                       option == MODIFY_COURSE_STATUS || (option.code == 4);
            case ENROLLMENT:
                return option == REGISTER_ENROLLMENT || option == VIEW_ENROLLMENTS || 
                       option == CHANGE_ENROLLMENT_STATUS || (option.code == 4);
            default:
                return false;
        }
    }
    
    public enum MenuContext {
        MAIN, STUDENT, COURSE, ENROLLMENT
    }
}
