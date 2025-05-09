package data;

import java.io.*;

public class ScoreWriter {
    private static final String FILE_PATH = "src/resources/highscores.txt";

    public static void writeScore(String name, int score) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            writer.println(name + "," + score);
        } catch (IOException e) {
            System.out.println("Error writing score: " + e.getMessage());
        }
    }
}