package edu.ccrm.util;

import java.io.IOException;
import java.nio.file.*;

public class RecursionUtils {

    public static long getDirectorySize(Path path) throws IOException {
        if (!Files.exists(path)) {
            return 0;
        }

        if (Files.isRegularFile(path)) {
            return Files.size(path);
        }

        try (var stream = Files.list(path)) {
            long size = 0;
            for (Path entry : stream.toList()) {
                size += getDirectorySize(entry);
            }
            return size;
        }
    }

    public static void printDirectoryStructure(Path path, int depth) throws IOException {
        if (!Files.exists(path)) {
            return;
        }

        String indent = " ".repeat(depth * 2);
        System.out.println(indent + path.getFileName());

        if (Files.isDirectory(path)) {
            try (var stream = Files.list(path)) {
                for (Path entry : stream.toList()) {
                    printDirectoryStructure(entry, depth + 1);
                }
            }
        }
    }
}
