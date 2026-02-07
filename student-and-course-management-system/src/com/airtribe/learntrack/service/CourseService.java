package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import java.util.ArrayList;

public class CourseService {
    private CourseRepository repository;

    public CourseService() {
        this.repository = new CourseRepository();
    }

    public void addCourse(String courseName, String description, int durationInWeeks)
            throws InvalidInputException {
        if (!InputValidator.isValidName(courseName)) {
            throw new InvalidInputException("Course name cannot be empty");
        }
        if (!InputValidator.isPositiveNumber(durationInWeeks)) {
            throw new InvalidInputException("Duration must be a positive number");
        }

        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks);
        repository.save(course);
        System.out.println("✓ Course created successfully!");
        System.out.println("  Course ID: " + id);
        System.out.println("  Name: " + courseName);
        System.out.println("  Duration: " + course.formatDuration());
    }

    public ArrayList<Course> listAllCourses() {
        return repository.findAll();
    }

    public Course findCourseById(int id) throws EntityNotFoundException {
        Course course = repository.findById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course with ID " + id + " not found");
        }
        return course;
    }

    public void toggleCourseStatus(int id) throws EntityNotFoundException {
        Course course = findCourseById(id);
        boolean previousStatus = course.isActive();
        course.setActive(!previousStatus);
        System.out.println("✓ Course availability updated");
        System.out.println("  Course: " + course.getCourseName() + " [#" + id + "]");
        System.out.println("  Status: " + (previousStatus ? "Available → Unavailable" : "Unavailable → Available"));
    }
    
    public int getActiveCourseCount() {
        return (int) repository.findAll().stream()
                .filter(Course::isActive)
                .count();
    }
}
