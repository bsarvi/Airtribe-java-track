package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseRepository {
    private final ArrayList<Course> dataStore;

    public CourseRepository() {
        this.dataStore = new ArrayList<>();
    }

    public void save(Course course) {
        if (course != null) {
            dataStore.add(course);
        }
    }

    public ArrayList<Course> findAll() {
        return new ArrayList<>(dataStore);
    }

    public Course findById(int id) {
        return dataStore.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public Optional<Course> findByIdOptional(int id) {
        return dataStore.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }
    
    public ArrayList<Course> findActiveCourses() {
        return dataStore.stream()
                .filter(Course::isActive)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<Course> findByDuration(int weeks) {
        return dataStore.stream()
                .filter(c -> c.getDurationInWeeks() == weeks)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean removeById(int id) {
        return dataStore.removeIf(c -> c.getId() == id);
    }
    
    public int count() {
        return dataStore.size();
    }
    
    public boolean exists(int id) {
        return dataStore.stream().anyMatch(c -> c.getId() == id);
    }
}
