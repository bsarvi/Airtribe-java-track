package com.airtribe.learntrack.util;

public class InputValidator {
    
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 100;

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return email.trim().matches(EMAIL_PATTERN);
    }

    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        int length = name.trim().length();
        return length >= MIN_NAME_LENGTH && length <= MAX_NAME_LENGTH;
    }

    public static boolean isPositiveNumber(int number) {
        return number > 0;
    }
    
    public static boolean isWithinRange(int value, int min, int max) {
        return value >= min && value <= max;
    }
    
    public static String sanitizeInput(String input) {
        return input == null ? "" : input.trim();
    }
}
