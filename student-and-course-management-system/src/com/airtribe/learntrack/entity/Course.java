package com.airtribe.learntrack.entity;

public class Course {
    private int id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;

    public Course() {
        this.active = true;
    }

    public Course(int id, String courseName, String description, int durationInWeeks) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.active = true;
    }
    
    // Builder for flexible object creation
    public static class Builder {
        private int id;
        private String courseName;
        private String description;
        private int durationInWeeks;
        
        public Builder withId(int id) {
            this.id = id;
            return this;
        }
        
        public Builder withName(String courseName) {
            this.courseName = courseName;
            return this;
        }
        
        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }
        
        public Builder withDuration(int weeks) {
            this.durationInWeeks = weeks;
            return this;
        }
        
        public Course build() {
            return new Course(id, courseName, description, durationInWeeks);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAvailabilityStatus() {
        return active ? "Available" : "Unavailable";
    }
    
    public String formatDuration() {
        return durationInWeeks + (durationInWeeks == 1 ? " week" : " weeks");
    }

    @Override
    public String toString() {
        return String.format("[#%d] %s | Duration: %s | Status: %s%s",
                id, courseName, formatDuration(), getAvailabilityStatus(),
                description != null && !description.isEmpty() ? "\n    Description: " + description : "");
    }
}
