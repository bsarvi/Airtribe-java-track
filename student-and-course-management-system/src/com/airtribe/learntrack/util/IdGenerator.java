package com.airtribe.learntrack.util;

/**
 * Utility class for generating unique sequential IDs across different entities
 */
public class IdGenerator {
    private static int studentSequence = 1000;
    private static int courseSequence = 2000;
    private static int enrollmentSequence = 3000;
    
    // Prevent instantiation
    private IdGenerator() {
        throw new UnsupportedOperationException("IdGenerator is a utility class and cannot be instantiated");
    }

    public static synchronized int getNextStudentId() {
        return ++studentSequence;
    }

    public static synchronized int getNextCourseId() {
        return ++courseSequence;
    }

    public static synchronized int getNextEnrollmentId() {
        return ++enrollmentSequence;
    }
    
    // For testing or reset purposes
    static void resetCounters() {
        studentSequence = 1000;
        courseSequence = 2000;
        enrollmentSequence = 3000;
    }
    
    public static int getCurrentStudentId() {
        return studentSequence;
    }
    
    public static int getCurrentCourseId() {
        return courseSequence;
    }
    
    public static int getCurrentEnrollmentId() {
        return enrollmentSequence;
    }
}
