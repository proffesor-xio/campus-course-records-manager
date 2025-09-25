package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CSVParser {

    public static List<String[]> parseCSV(Path filePath) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        return lines.stream()
                .map(line -> line.split(","))
                .toList();
    }
}
