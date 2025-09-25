package edu.ccrm.io;

//import edu.ccrm.domain.Student;
//import edu.ccrm.domain.Course;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;
//import java.util.stream.Collectors;

public class ImportExportService {

    private static final Path EXPORT_DIR = Paths.get("exports");

    public void menu(Scanner sc) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Import/Export ---");
            System.out.println("1. Import Students");
            System.out.println("2. Import Courses");
            System.out.println("3. Export Students");
            System.out.println("4. Export Courses");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            String input = sc.nextLine();
            switch (input) {
                case "1" -> importStudents(sc);
                case "2" -> importCourses(sc);
                case "3" -> exportStudents();
                case "4" -> exportCourses();
                case "0" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void importStudents(Scanner sc) {
        System.out.print("Enter students CSV file path: ");
        String path = sc.nextLine();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                System.out.println("Imported Student: " + line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private void importCourses(Scanner sc) {
        System.out.print("Enter courses CSV file path: ");
        String path = sc.nextLine();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                System.out.println("Imported Course: " + line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private void exportStudents() {
        try {
            if (!Files.exists(EXPORT_DIR)) {
                Files.createDirectories(EXPORT_DIR);
            }
            Path file = EXPORT_DIR.resolve("students_export.csv");
            Files.writeString(file, "id,regNo,fullName,email,status\n"); // Example
            System.out.println("Students exported to: " + file);
        } catch (IOException e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }

    private void exportCourses() {
        try {
            if (!Files.exists(EXPORT_DIR)) {
                Files.createDirectories(EXPORT_DIR);
            }
            Path file = EXPORT_DIR.resolve("courses_export.csv");
            Files.writeString(file, "code,title,credits,instructor,semester,department\n"); // Example
            System.out.println("Courses exported to: " + file);
        } catch (IOException e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }
}
