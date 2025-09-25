package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrollmentService {
    private final List<Enrollment> enrollments = new ArrayList<>();
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public void menu(Scanner sc) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Enrollment Management ---");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. Unenroll Student from Course");
            System.out.println("3. Record Grade");
            System.out.println("4. List Enrollments");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            String input = sc.nextLine();
            switch (input) {
                case "1" -> enrollStudent(sc);
                case "2" -> unenrollStudent(sc);
                case "3" -> recordGrade(sc);
                case "4" -> listEnrollments();
                case "0" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void enrollStudent(Scanner sc) {
        System.out.print("Enter student ID: ");
        String studentId = sc.nextLine();
        Student student = studentService.findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = sc.nextLine();
        Course course = courseService.findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        Enrollment enrollment = new Enrollment(student, course, LocalDate.now());
        enrollments.add(enrollment);
        System.out.println("Enrollment successful!");
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    private void unenrollStudent(Scanner sc) {
        System.out.print("Enter student ID: ");
        String studentId = sc.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = sc.nextLine();

        Enrollment enrollment = enrollments.stream()
                .filter(e -> e.getStudent().getId().equals(studentId) &&
                        e.getCourse().getCode().equalsIgnoreCase(courseCode))
                .findFirst()
                .orElse(null);

        if (enrollment == null) {
            System.out.println("Enrollment not found.");
            return;
        }

        enrollments.remove(enrollment);
        System.out.println("Unenrollment successful!");
    }

    private void recordGrade(Scanner sc) {
        System.out.print("Enter student ID: ");
        String studentId = sc.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = sc.nextLine();

        Enrollment enrollment = enrollments.stream()
                .filter(e -> e.getStudent().getId().equals(studentId) &&
                        e.getCourse().getCode().equalsIgnoreCase(courseCode))
                .findFirst()
                .orElse(null);

        if (enrollment == null) {
            System.out.println("Enrollment not found.");
            return;
        }

        System.out.print("Enter grade (S, A, B, C, D, E, F): ");
        String gradeStr = sc.nextLine().toUpperCase();

        try {
            Grade grade = Grade.valueOf(gradeStr);
            enrollment.setGrade(grade);
            System.out.println("Grade recorded successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid grade entered.");
        }
    }

    private void listEnrollments() {
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }
        System.out.println("List of Enrollments:");
        enrollments.forEach(System.out::println);
    }
}
