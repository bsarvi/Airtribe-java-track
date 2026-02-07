package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.ArrayList;

public class EnrollmentService {
    private EnrollmentRepository repository;
    private StudentService studentService;
    private CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.repository = new EnrollmentRepository();
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public void enrollStudent(int studentId, int courseId)
            throws EntityNotFoundException, InvalidInputException {
        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        if (!student.isActive()) {
            throw new InvalidInputException("Cannot enroll inactive student");
        }
        if (!course.isActive()) {
            throw new InvalidInputException("Cannot enroll in inactive course");
        }

        int enrollmentId = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(enrollmentId, studentId, courseId);
        repository.save(enrollment);
        System.out.println("✓ Enrollment completed successfully!");
        System.out.println("  Enrollment ID: " + enrollmentId);
        System.out.println("  Student: " + student.getDisplayName() + " [#" + studentId + "]");
        System.out.println("  Course: " + course.getCourseName() + " [#" + courseId + "]");
    }

    public ArrayList<Enrollment> getStudentEnrollments(int studentId) throws EntityNotFoundException {
        studentService.findStudentById(studentId);
        return repository.findByStudentId(studentId);
    }

    public void updateEnrollmentStatus(int enrollmentId, EnrollmentStatus status)
            throws EntityNotFoundException {
        Enrollment enrollment = repository.findById(enrollmentId);
        if (enrollment == null) {
            throw new EntityNotFoundException("Enrollment with ID " + enrollmentId + " not found");
        }
        EnrollmentStatus previousStatus = enrollment.getStatus();
        enrollment.setStatus(status);
        System.out.println("✓ Enrollment status updated");
        System.out.println("  Enrollment ID: " + enrollmentId);
        System.out.println("  Status change: " + previousStatus + " → " + status);
    }

    public ArrayList<Enrollment> listAllEnrollments() {
        return repository.findAll();
    }
    
    public int getActiveEnrollmentCount() {
        return (int) repository.findAll().stream()
                .filter(e -> e.getStatus() == EnrollmentStatus.ACTIVE)
                .count();
    }
}
