package com.airtribe.learntrack.entity;

public class Student extends Person {
    private String batch;
    private boolean active;

    // Default constructor
    public Student() {
        super();
        this.active = true;
    }

    // Constructor without email (constructor overloading)
    public Student(int id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName, null);
        this.batch = batch;
        this.active = true;
    }

    // Full constructor
    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " (" + batch + ")";
    }

    public String getStatusLabel() {
        return active ? "✓ Active" : "✗ Inactive";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[Student #%d] %s", getId(), getDisplayName()));
        if (hasEmail()) {
            sb.append(String.format(" <%s>", getEmail()));
        }
        sb.append(String.format(" | Batch: %s | Status: %s", batch, getStatusLabel()));
        return sb.toString();
    }
}
