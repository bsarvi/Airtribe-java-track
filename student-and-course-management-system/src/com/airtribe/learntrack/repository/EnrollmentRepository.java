package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnrollmentRepository {
    private final ArrayList<Enrollment> dataStore;

    public EnrollmentRepository() {
        this.dataStore = new ArrayList<>();
    }

    public void save(Enrollment enrollment) {
        if (enrollment != null) {
            dataStore.add(enrollment);
        }
    }

    public ArrayList<Enrollment> findAll() {
        return new ArrayList<>(dataStore);
    }

    public Enrollment findById(int id) {
        return dataStore.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public Optional<Enrollment> findByIdOptional(int id) {
        return dataStore.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    public ArrayList<Enrollment> findByStudentId(int studentId) {
        return dataStore.stream()
                .filter(e -> e.getStudentId() == studentId)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<Enrollment> findByCourseId(int courseId) {
        return dataStore.stream()
                .filter(e -> e.getCourseId() == courseId)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<Enrollment> findByStatus(EnrollmentStatus status) {
        return dataStore.stream()
                .filter(e -> e.getStatus() == status)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public boolean isStudentEnrolledInCourse(int studentId, int courseId) {
        return dataStore.stream()
                .anyMatch(e -> e.getStudentId() == studentId && 
                              e.getCourseId() == courseId &&
                              e.getStatus() == EnrollmentStatus.ACTIVE);
    }

    public boolean removeById(int id) {
        return dataStore.removeIf(e -> e.getId() == id);
    }
    
    public int count() {
        return dataStore.size();
    }
    
    public boolean exists(int id) {
        return dataStore.stream().anyMatch(e -> e.getId() == id);
    }
}
