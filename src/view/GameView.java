package view;

import model.*;
import model.entitites.Alien;
import model.entitites.Bullet;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private final GameModel model;

    public GameView(GameModel model) {
        this.model = model;
        setPreferredSize(new Dimension(GameModel.BOARD_WIDTH, GameModel.BOARD_HEIGHT));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw ship
        model.getShip().draw(g);

        // Draw aliens
        for (Alien alien : model.getAliens()) {
            if (alien.isAlive()) {
                alien.draw(g);
            }
        }

        // Draw bullets
        g.setColor(Color.WHITE);
        for (Bullet bullet : model.getBullets()) {
            if (!bullet.isUsed()) {
                g.drawRect(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
            }
        }

        // Draw score
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        String text = model.isGameOver() ? "Game Over: " + model.getScoreManager().getScore()
                : String.valueOf(model.getScoreManager().getScore());
        g.drawString(text, 10, 35);
    }
}