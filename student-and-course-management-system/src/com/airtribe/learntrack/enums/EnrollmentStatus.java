package com.airtribe.learntrack.enums;

public enum EnrollmentStatus {
    ACTIVE("In Progress"),
    COMPLETED("Finished"),
    CANCELLED("Withdrawn");
    
    private final String displayName;
    
    EnrollmentStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public static EnrollmentStatus fromChoice(int choice) {
        switch (choice) {
            case 1: return ACTIVE;
            case 2: return COMPLETED;
            case 3: return CANCELLED;
            default: return null;
        }
    }
}
