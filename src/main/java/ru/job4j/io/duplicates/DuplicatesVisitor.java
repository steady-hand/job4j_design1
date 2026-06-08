package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    public Map<FileProperty, List<Path>> getProperties() {
        return properties;
    }

    private final Map<FileProperty, List<Path>> properties = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String name = file.getFileName().toString();
        long size = attrs.size();
        FileProperty f = new FileProperty(name, size);
        properties.computeIfAbsent(f, key -> new ArrayList<>()).add(file);
        return super.visitFile(file, attrs);
    }
}
