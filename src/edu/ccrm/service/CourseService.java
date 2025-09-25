package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Semester;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    public void menu(Scanner sc) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add Course");
            System.out.println("2. List Courses");
            System.out.println("3. Update Course");
            System.out.println("4. Search Courses");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            String input = sc.nextLine();
            switch (input) {
                case "1" -> addCourse(sc);
                case "2" -> listCourses();
                case "3" -> updateCourse(sc);
                case "4" -> searchCourses(sc);
                case "0" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addCourse(Scanner sc) {
        System.out.print("Enter course code: ");
        String code = sc.nextLine();
        System.out.print("Enter course title: ");
        String title = sc.nextLine();
        System.out.print("Enter credits: ");
        int credits = Integer.parseInt(sc.nextLine());

        System.out.print("Enter instructor name: ");
        String instructorName = sc.nextLine();
        Instructor instructor = new Instructor(
                "I" + code,
                instructorName,
                instructorName.toLowerCase().replace(" ", "") + "@college.edu",
                java.time.LocalDate.now().minusYears(30),
                "E" + code,
                "Unknown"
        );

        System.out.print("Enter semester (SPRING, SUMMER, FALL): ");
        Semester semester = Semester.valueOf(sc.nextLine().toUpperCase());
        System.out.print("Enter department: ");
        String department = sc.nextLine();

        Course course = new Course(code, title, credits, instructor, semester, department);
        courses.add(course);
        System.out.println("Course added successfully!");
    }

    private void listCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        System.out.println("List of Courses:");
        courses.forEach(System.out::println);
    }

    private void updateCourse(Scanner sc) {
        System.out.print("Enter course code to update: ");
        String code = sc.nextLine();
        Course course = findCourseByCode(code);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.print("Enter new title: ");
        course.setTitle(sc.nextLine());
        System.out.print("Enter new credits: ");
        course.setCredits(Integer.parseInt(sc.nextLine()));
        System.out.print("Enter new department: ");
        course.setDepartment(sc.nextLine());
        System.out.println("Course updated successfully!");
    }

    private void searchCourses(Scanner sc) {
        System.out.println("Search by:");
        System.out.println("1. Instructor");
        System.out.println("2. Department");
        System.out.println("3. Semester");
        System.out.print("Choice: ");
        String choice = sc.nextLine();

        List<Course> results = new ArrayList<>();
        switch (choice) {
            case "1" -> {
                System.out.print("Enter instructor name: ");
                String name = sc.nextLine();
                results = courses.stream()
                        .filter(c -> c.getInstructor().getFullName().equalsIgnoreCase(name))
                        .collect(Collectors.toList());
            }
            case "2" -> {
                System.out.print("Enter department: ");
                String dept = sc.nextLine();
                results = courses.stream()
                        .filter(c -> c.getDepartment().equalsIgnoreCase(dept))
                        .collect(Collectors.toList());
            }
            case "3" -> {
                System.out.print("Enter semester (SPRING, SUMMER, FALL): ");
                Semester sem = Semester.valueOf(sc.nextLine().toUpperCase());
                results = courses.stream()
                        .filter(c -> c.getSemester() == sem)
                        .collect(Collectors.toList());
            }
            default -> System.out.println("Invalid choice.");
        }

        if (results.isEmpty()) {
            System.out.println("No matching courses found.");
        } else {
            results.forEach(System.out::println);
        }
    }

    public Course findCourseByCode(String code) {
        return courses.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }
}
