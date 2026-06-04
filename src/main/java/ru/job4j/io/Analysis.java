package ru.job4j.io;

import java.io.*;

public class Analysis {
    public static void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String line;
            boolean statusFail = false;
            String startTime = null;
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(" ");
                String status = array[0];
                String time = array[1];
                if (status.equals("400") || status.equals("500")) {
                    statusFail = true;
                    startTime = time;
                } else if ((status.equals("200") || status.equals("300")) && statusFail) {
                    statusFail = false;
                    writer.write(startTime + ";" + time);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
