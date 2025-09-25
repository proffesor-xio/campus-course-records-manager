package edu.ccrm.service;

import edu.ccrm.domain.Student;
//import edu.ccrm.domain.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public void menu(Scanner sc) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Update Student");
            System.out.println("4. Deactivate Student");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            String input = sc.nextLine();
            switch (input) {
                case "1" -> addStudent(sc);
                case "2" -> listStudents();
                case "3" -> updateStudent(sc);
                case "4" -> deactivateStudent(sc);
                case "0" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addStudent(Scanner sc) {
        System.out.print("Enter ID: ");
        String id = sc.nextLine();
        System.out.print("Enter full name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        LocalDate dob = LocalDate.parse(sc.nextLine());
        System.out.print("Enter registration number: ");
        String regNo = sc.nextLine();

        Student student = new Student(id, name, email, dob, regNo);
        students.add(student);
        System.out.println("Student added successfully!");
    }

    private void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("List of Students:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void updateStudent(Scanner sc) {
        System.out.print("Enter student ID to update: ");
        String id = sc.nextLine();
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new full name: ");
        student.setFullName(sc.nextLine());
        System.out.print("Enter new email: ");
        student.setEmail(sc.nextLine());
        System.out.println("Student updated successfully!");
    }

    private void deactivateStudent(Scanner sc) {
        System.out.print("Enter student ID to deactivate: ");
        String id = sc.nextLine();
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        student.deactivate();
        System.out.println("Student deactivated successfully!");
    }

    public Student findStudentById(String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
