package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JPanel {
    private JButton startButton;
    private JButton highScoresButton;
    private JButton quitButton;

    public MainMenuView() {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        JLabel title = new JLabel("Space Invaders");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(Color.WHITE);

        startButton = new JButton("▶ Start Game");
        highScoresButton = new JButton("★ High Scores");
        quitButton = new JButton("✖ Quit");

        Font buttonFont = new Font("Arial", Font.PLAIN, 24);
        startButton.setFont(buttonFont);
        highScoresButton.setFont(buttonFont);
        quitButton.setFont(buttonFont);

        startButton.setFocusPainted(false);
        highScoresButton.setFocusPainted(false);
        quitButton.setFocusPainted(false);

        startButton.setBackground(new Color(30, 144, 255));
        startButton.setForeground(Color.BLACK);
        highScoresButton.setBackground(new Color(255, 215, 0));
        highScoresButton.setForeground(Color.BLACK);
        quitButton.setBackground(new Color(220, 20, 60));
        quitButton.setForeground(Color.BLACK);

        startButton.setPreferredSize(new Dimension(200, 50));
        highScoresButton.setPreferredSize(new Dimension(200, 50));
        quitButton.setPreferredSize(new Dimension(200, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(20, 20, 20, 20);

        gbc.gridy = 0;
        add(title, gbc);

        gbc.gridy = 1;
        add(startButton, gbc);

        gbc.gridy = 2;
        add(highScoresButton, gbc);

        gbc.gridy = 3;
        add(quitButton, gbc);
    }

    public void addStartButtonListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    public void addQuitButtonListener(ActionListener listener) {
        quitButton.addActionListener(listener);
    }

    public void addHighScoresButtonListener(ActionListener listener) {
        highScoresButton.addActionListener(listener);
    }
}