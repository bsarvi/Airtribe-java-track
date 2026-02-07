package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentRepository {
    private final ArrayList<Student> dataStore;

    public StudentRepository() {
        this.dataStore = new ArrayList<>();
    }

    public void save(Student student) {
        if (student != null) {
            dataStore.add(student);
        }
    }

    public ArrayList<Student> findAll() {
        return new ArrayList<>(dataStore);
    }

    public Student findById(int id) {
        return dataStore.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public Optional<Student> findByIdOptional(int id) {
        return dataStore.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }
    
    public ArrayList<Student> findByBatch(String batch) {
        return dataStore.stream()
                .filter(s -> s.getBatch().equalsIgnoreCase(batch))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<Student> findActiveStudents() {
        return dataStore.stream()
                .filter(Student::isActive)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean removeById(int id) {
        return dataStore.removeIf(s -> s.getId() == id);
    }
    
    public int count() {
        return dataStore.size();
    }
    
    public boolean exists(int id) {
        return dataStore.stream().anyMatch(s -> s.getId() == id);
    }
}
