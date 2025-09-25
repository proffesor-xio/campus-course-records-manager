package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.service.StudentService;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.TranscriptService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.io.BackupService;

import java.util.Scanner;

/**
 * Entry point for the Campus Course & Records Manager (CCRM).
 * Menu-driven console app.
 */
public class Main {

    public static void main(String[] args) {
        // Initialize Singleton config
        AppConfig config = AppConfig.getInstance();
        System.out.println("=== Campus Course & Records Manager (CCRM) ===");
        System.out.println("Data folder: " + config.getDataPath());

        // Initialize services
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);
        TranscriptService transcriptService = new TranscriptService(enrollmentService.getEnrollments());
        ImportExportService ioService = new ImportExportService();
        BackupService backupService = new BackupService();

        Scanner sc = new Scanner(System.in);

        mainLoop: // label for demonstrating labeled break
        while (true) {
            printMenu();
            System.out.print("Enter your choice: ");
            String input = sc.nextLine();

            int choice;
            try {
                choice = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue; // demonstrate "continue"
            }

            switch (choice) {
                case 1 -> studentService.menu(sc);
                case 2 -> courseService.menu(sc);
                case 3 -> enrollmentService.menu(sc);
                case 4 -> transcriptService.menu(sc);
                case 5 -> ioService.menu(sc);
                case 6 -> backupService.menu(sc);
                case 7 -> {
                    // Example: Stream report would go here
                    System.out.println("Reports feature coming soon...");
                }
                case 0 -> {
                    System.out.println("Exiting program...");
                    break mainLoop; // demonstrate labeled break
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }

        sc.close();
        System.out.println("=== Program Ended ===");
    }

    private static void printMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Enrollment & Grades");
        System.out.println("4. Print Transcripts");
        System.out.println("5. Import/Export Data");
        System.out.println("6. Backup & Utilities");
        System.out.println("7. Reports");
        System.out.println("0. Exit");
    }
}
