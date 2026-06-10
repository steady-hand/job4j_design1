package ru.job4j.io;

import java.io.FilterOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        Path path = Path.of(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        Scanner scanner = new Scanner(path);
        String[] array = scanner.nextLine().split(delimiter);
        String[] array2 = filter.split(",");
        List<String> headers = List.of(array);
        List<Integer> indices = new ArrayList<>();
        for (String w : array2) {
            int i = headers.indexOf(w);
            if (i != -1) {
                indices.add(i);
            }
        }
        PrintStream outStream = null;
        if ("stdout".equals(out)) {
            outStream = System.out;
        } else {
            outStream = new PrintStream(out);
        }
        StringJoiner s = new StringJoiner(delimiter);
        for (Integer i : indices) {
           s.add(headers.get(i));
        }
        outStream.println(s.toString());
        while (scanner.hasNextLine()) {
            String[] array3 = scanner.nextLine().split(delimiter);
            StringJoiner s2 = new StringJoiner(delimiter);
            for (Integer i : indices) {
                s2.add(array3[i]);
            }
            outStream.println(s2.toString());
        }
        outStream.close();
    }

    public static void main(String[] args) throws Exception {
        /* здесь добавьте валидацию принятых параметров*/
        if (args.length < 4) {
            throw new IllegalArgumentException("Pass the correct number of arguments.");
        }
        ArgsName argsName = ArgsName.of(args);
        if (!Files.exists(Path.of(argsName.get("path")))) {
            throw new IllegalArgumentException("This file does not exist");
        }
        handle(argsName);
    }
}
