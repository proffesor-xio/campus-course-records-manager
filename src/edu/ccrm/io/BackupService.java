package edu.ccrm.io;

import edu.ccrm.util.RecursionUtils;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BackupService {

    private static final Path EXPORT_DIR = Paths.get("exports");
    private static final Path BACKUP_DIR = Paths.get("backups");

    public void menu(Scanner sc) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Backup & Utilities ---");
            System.out.println("1. Backup Data");
            System.out.println("2. Show Backup Size");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            String input = sc.nextLine();
            switch (input) {
                case "1" -> backupData();
                case "2" -> showBackupSize();
                case "0" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void backupData() {
        try {
            if (!Files.exists(BACKUP_DIR)) {
                Files.createDirectories(BACKUP_DIR);
            }
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm"));
            Path backupPath = BACKUP_DIR.resolve(timestamp);
            Files.createDirectories(backupPath);

            if (Files.exists(EXPORT_DIR)) {
                Files.walk(EXPORT_DIR)
                        .forEach(source -> {
                            try {
                                Path destination = backupPath.resolve(EXPORT_DIR.relativize(source));
                                if (Files.isDirectory(source)) {
                                    Files.createDirectories(destination);
                                } else {
                                    Files.copy(source, destination);
                                }
                            } catch (IOException e) {
                                System.out.println("Error backing up: " + e.getMessage());
                            }
                        });
                System.out.println("Backup created at: " + backupPath);
            } else {
                System.out.println("No exports found to backup.");
            }
        } catch (IOException e) {
            System.out.println("Backup failed: " + e.getMessage());
        }
    }

    private void showBackupSize() {
        if (Files.exists(BACKUP_DIR)) {
            try {
                long size = RecursionUtils.getDirectorySize(BACKUP_DIR);
                System.out.println("Total backup size: " + size + " bytes");
            } catch (IOException e) {
                System.out.println("Error calculating size: " + e.getMessage());
            }
        } else {
            System.out.println("No backups found.");
        }
    }
}
