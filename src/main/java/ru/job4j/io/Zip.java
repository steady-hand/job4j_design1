package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path s : sources) {
                zip.putNextEntry(new ZipEntry(s.toString()));
                try (BufferedInputStream input = new BufferedInputStream(Files.newInputStream(s))) {
                    zip.write(input.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(input.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validate(ArgsName argsName) {
        String directory = argsName.get("d");
        String exception = argsName.get("e");
        String name = argsName.get("o");
        File folder = new File(directory);
        if (!folder.isDirectory() || !folder.exists()) {
            throw new IllegalArgumentException("The file does not exist or is not a directory.");
        }
        if (!exception.startsWith(".")) {
            throw new IllegalArgumentException("The extension must start with '.'");
        }
        if (!name.endsWith(".zip")) {
            throw new IllegalArgumentException("The archive name must have an extension '.zip'");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        Path start = Paths.get(argsName.get("d"));
        String exception = argsName.get("e");
        String name = argsName.get("o");
        List<Path> absoluteSources = Search.search(start, p -> !p.toFile().getName().endsWith(exception));
        List<Path> relativeSources = new ArrayList<>();
        for (Path r : absoluteSources) {
            relativeSources.add(start.relativize(r));
        }
        new Zip().packFiles(relativeSources, new File(name));
    }
}
