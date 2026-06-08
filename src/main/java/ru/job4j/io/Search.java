package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate (String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        Path start = Paths.get(args[0]);
        String ext = args[1];
        if (!Files.exists(start)) {
            throw new IllegalArgumentException(String.format("Not exist %s", start.toAbsolutePath()));
        }
        if (!Files.isDirectory(start)) {
            throw new IllegalArgumentException(String.format("Not directory %s", start.toAbsolutePath()));
        }
        if (ext.isEmpty()) {
            throw new IllegalArgumentException("Extension cannot be empty");
        }
    }
}
