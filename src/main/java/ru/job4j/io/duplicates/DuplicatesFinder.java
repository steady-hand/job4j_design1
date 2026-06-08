package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        Map<FileProperty, List<Path>> allFiles = visitor.getProperties();
        for (Map.Entry<FileProperty, List<Path>> entry : allFiles.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println(entry.getKey().getName() + " - " + entry.getKey().getSize());
                for (Path e : entry.getValue()) {
                    System.out.println("    " + e.toAbsolutePath());
                }
            }
        }
    }
}
