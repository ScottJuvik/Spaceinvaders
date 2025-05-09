package data;

import java.io.*;
import java.util.*;

public class ScoreReader {
    private static final String FILE_PATH = "src/resources/highscores.txt";

    public static List<String> readScoresWithNames() {
        List<String> scores = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                scores.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("High score file not found.");
        }
        return scores;
    }
}