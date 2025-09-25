package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Grade;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TranscriptService {
    private final List<Enrollment> enrollments;

    public TranscriptService(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void menu(Scanner sc) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Transcript Management ---");
            System.out.println("1. Print Student Transcript");
            System.out.println("2. GPA Report");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            String input = sc.nextLine();
            switch (input) {
                case "1" -> printTranscript(sc);
                case "2" -> gpaReport();
                case "0" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void printTranscript(Scanner sc) {
        System.out.print("Enter student ID: ");
        String studentId = sc.nextLine();

        List<Enrollment> studentEnrollments = enrollments.stream()
                .filter(e -> e.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());

        if (studentEnrollments.isEmpty()) {
            System.out.println("No enrollments found for this student.");
            return;
        }

        System.out.println("\n--- Transcript ---");
        Student student = studentEnrollments.get(0).getStudent();
        System.out.println("Student: " + student.getFullName() + " (" + student.getRegNo() + ")");
        System.out.println("Email: " + student.getEmail());

        double totalPoints = 0;
        int totalCredits = 0;

        for (Enrollment e : studentEnrollments) {
            Grade grade = e.getGrade();
            int credits = e.getCourse().getCredits();
            System.out.println(e.getCourse().getCode() + " | " + e.getCourse().getTitle() +
                    " | Credits: " + credits + " | Grade: " + grade);

            totalPoints += grade.getGradePoints() * credits;
            totalCredits += credits;
        }

        double gpa = totalCredits > 0 ? totalPoints / totalCredits : 0.0;
        System.out.printf("GPA: %.2f%n", gpa);
    }

    private void gpaReport() {
        System.out.println("\n--- GPA Report ---");
        enrollments.stream()
                .collect(Collectors.groupingBy(e -> e.getStudent().getId()))
                .forEach((studentId, studentEnrollments) -> {
                    double totalPoints = 0;
                    int totalCredits = 0;
                    for (Enrollment e : studentEnrollments) {
                        totalPoints += e.getGrade().getGradePoints() * e.getCourse().getCredits();
                        totalCredits += e.getCourse().getCredits();
                    }
                    double gpa = totalCredits > 0 ? totalPoints / totalCredits : 0.0;
                    System.out.printf("Student ID: %s | GPA: %.2f%n", studentId, gpa);
                });
    }
}
