package controller;

import data.ScoreReader;
import data.ScoreWriter;
import model.*;
import model.entitites.Ship;
import view.GameView;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class GameController implements KeyListener, ActionListener {
    private final GameModel model;
    private final GameView view;
    private final Timer timer;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.view.addKeyListener(this);
        this.view.setFocusable(true);
        this.view.requestFocusInWindow();

        this.timer = new Timer(1000 / 60, this); // ~60 FPS
        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.update();
        view.repaint();
        if (model.isGameOver()) {
            timer.stop();
            int score = model.getScoreManager().getScore();
            String name = JOptionPane.showInputDialog(view, "Enter your name:", "Game Over", JOptionPane.PLAIN_MESSAGE);
            if (name != null && !name.trim().isEmpty()) {
                ScoreWriter.writeScore(name.trim(), score);
            }
            List<String> scores = ScoreReader.readScoresWithNames();
            System.out.println("High Scores: " + scores);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (model.isGameOver()) {
            model.reset();
            timer.start();
            return;
        }

        Ship ship = model.getShip();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> ship.moveLeft(GameModel.BOARD_WIDTH);
            case KeyEvent.VK_RIGHT -> ship.moveRight(GameModel.BOARD_WIDTH);
            case KeyEvent.VK_SPACE -> model.shoot();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}