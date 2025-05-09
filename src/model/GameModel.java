package model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.entitites.Alien;
import model.entitites.Bullet;
import model.entitites.Ship;

public class GameModel {
    public static final int TILE_SIZE = 32;
    public static final int ROWS = 16;
    public static final int COLUMNS = 16;
    public static final int BOARD_WIDTH = TILE_SIZE * COLUMNS;
    public static final int BOARD_HEIGHT = TILE_SIZE * ROWS;

    private Ship ship;
    private List<Alien> aliens;
    private List<Bullet> bullets;
    private ScoreManager scoreManager;

    private int alienColumns = 3;
    private int alienRows = 2;
    private int alienCount;
    private int alienVelocityX = 1;

    private boolean gameOver = false;
    private final int bulletVelocityY = -10;

    private List<Image> alienImages;

    public GameModel(Image shipImage, List<Image> alienImages) {
        this.alienImages = alienImages;
        scoreManager = new ScoreManager();
        bullets = new ArrayList<>();
        aliens = new ArrayList<>();

        int shipWidth = TILE_SIZE * 2;
        int shipHeight = TILE_SIZE;
        int shipX = TILE_SIZE * COLUMNS / 2 - TILE_SIZE;
        int shipY = TILE_SIZE * ROWS - TILE_SIZE * 2;

        ship = new Ship(shipX, shipY, shipWidth, shipHeight, shipImage, TILE_SIZE);
        createAliens();
    }

    public void update() {
        if (gameOver)
            return;

        boolean edgeReached = false;
        for (Alien alien : aliens) {
            if (!alien.isAlive())
                continue;
            alien.setX(alien.getX() + alienVelocityX);
            if (alien.getX() + alien.getWidth() >= BOARD_WIDTH || alien.getX() <= 0) {
                edgeReached = true;
            }
            if (alien.getY() >= ship.getY()) {
                gameOver = true;
            }
        }

        if (edgeReached) {
            alienVelocityX *= -1;
            for (Alien alien : aliens) {
                alien.setX(alien.getX() + alienVelocityX * 2);
                alien.setY(alien.getY() + TILE_SIZE);
            }
        }

        for (Bullet bullet : bullets) {
            bullet.update();
            for (Alien alien : aliens) {
                if (!bullet.isUsed() && alien.isAlive() && bullet.getBounds().intersects(alien.getBounds())) {
                    bullet.setUsed(true);
                    alien.setAlive(false);
                    alienCount--;
                    scoreManager.addPoints(100);
                }
            }
        }

        bullets.removeIf(b -> b.isUsed() || b.getY() < 0);

        if (alienCount == 0) {
            scoreManager.addPoints(alienColumns * alienRows * 100);
            alienColumns = Math.min(alienColumns + 1, COLUMNS / 2 - 2);
            alienRows = Math.min(alienRows + 1, ROWS - 6);
            aliens.clear();
            bullets.clear();
            createAliens();
        }
    }

    private void createAliens() {
        Random rand = new Random();
        int alienWidth = TILE_SIZE * 2;
        int alienHeight = TILE_SIZE;
        int alienX = TILE_SIZE;
        int alienY = TILE_SIZE;

        for (int c = 0; c < alienColumns; c++) {
            for (int r = 0; r < alienRows; r++) {
                Image img = alienImages.get(rand.nextInt(alienImages.size()));
                Alien alien = new Alien(
                        alienX + c * alienWidth,
                        alienY + r * alienHeight,
                        alienWidth,
                        alienHeight,
                        img);
                aliens.add(alien);
            }
        }
        alienCount = aliens.size();
    }

    public void reset() {
        int shipX = TILE_SIZE * COLUMNS / 2 - TILE_SIZE;
        ship.setX(shipX);
        bullets.clear();
        aliens.clear();
        alienColumns = 3;
        alienRows = 2;
        alienVelocityX = 1;
        alienCount = 0;
        scoreManager.reset();
        gameOver = false;
        createAliens();
    }

    public void shoot() {
        int bulletWidth = TILE_SIZE / 8;
        int bulletHeight = TILE_SIZE / 2;
        int bulletX = ship.getX() + ship.getWidth() * 15 / 32;
        int bulletY = ship.getY();
        bullets.add(new Bullet(bulletX, bulletY, bulletWidth, bulletHeight, bulletVelocityY));
    }

    public Ship getShip() {
        return ship;
    }

    public List<Alien> getAliens() {
        return aliens;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}