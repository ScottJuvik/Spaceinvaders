package model;

public class ScoreManager {
    private int score = 0;

    public void addPoints(int points) {
        score += points;
    }

    public void reset() {
        score = 0;
    }

    public int getScore() {
        return score;
    }
}