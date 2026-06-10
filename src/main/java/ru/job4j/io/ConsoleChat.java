package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isWorking = true;
        boolean readyToAnswer = true;
        List<String> list = readPhrases();
        List<String> logList = new ArrayList<>();
        Random random = new Random();
        while (isWorking) {
            String input = reader.readLine();
            logList.add(input);
            if (OUT.equals(input)) {
                isWorking = false;
            }
            if (STOP.equals(input)) {
                readyToAnswer = false;
            } else if (CONTINUE.equals(input)) {
                readyToAnswer = true;
            }
            if (readyToAnswer && !OUT.equals(input) && !CONTINUE.equals(input)) {
                String botAnswer = list.get(random.nextInt(list.size()));
                logList.add(botAnswer);
                System.out.println(botAnswer);
            }
        }
        saveLog(logList);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            list = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat consoleChat = new ConsoleChat("data/log.txt", "data/botAnswers.txt");
        consoleChat.run();
    }
}
