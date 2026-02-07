package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import java.util.ArrayList;

public class StudentService {
    private StudentRepository repository;

    public StudentService() {
        this.repository = new StudentRepository();
    }

    public void addStudent(String firstName, String lastName, String email, String batch)
            throws InvalidInputException {
        if (!InputValidator.isValidName(firstName) || !InputValidator.isValidName(lastName)) {
            throw new InvalidInputException("First name and last name cannot be empty");
        }
        if (email != null && !email.trim().isEmpty() && !InputValidator.isValidEmail(email)) {
            throw new InvalidInputException("Invalid email format");
        }
        if (!InputValidator.isValidName(batch)) {
            throw new InvalidInputException("Batch cannot be empty");
        }

        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch);
        repository.save(student);
        System.out.println("✓ Student registered successfully!");
        System.out.println("  Assigned ID: " + id);
        System.out.println("  Name: " + student.getDisplayName());
    }

    public ArrayList<Student> listAllStudents() {
        return repository.findAll();
    }

    public Student findStudentById(int id) throws EntityNotFoundException {
        Student student = repository.findById(id);
        if (student == null) {
            throw new EntityNotFoundException("Student with ID " + id + " not found");
        }
        return student;
    }

    public void deactivateStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setActive(false);
        System.out.println("✓ Student account deactivated");
        System.out.println("  Student: " + student.getDisplayName() + " [#" + id + "]");
    }

    public void updateStudent(int id, String firstName, String lastName, String email, String batch)
            throws EntityNotFoundException, InvalidInputException {
        Student student = findStudentById(id);

        if (firstName != null && !firstName.trim().isEmpty()) {
            if (!InputValidator.isValidName(firstName)) {
                throw new InvalidInputException("Invalid first name");
            }
            student.setFirstName(firstName);
        }

        if (lastName != null && !lastName.trim().isEmpty()) {
            if (!InputValidator.isValidName(lastName)) {
                throw new InvalidInputException("Invalid last name");
            }
            student.setLastName(lastName);
        }

        if (email != null && !email.trim().isEmpty()) {
            if (!InputValidator.isValidEmail(email)) {
                throw new InvalidInputException("Invalid email format");
            }
            student.setEmail(email);
        }

        if (batch != null && !batch.trim().isEmpty()) {
            student.setBatch(batch);
        }

        System.out.println("✓ Student information updated successfully");
    }
    
    public int getActiveStudentCount() {
        return (int) repository.findAll().stream()
                .filter(Student::isActive)
                .count();
    }
}
