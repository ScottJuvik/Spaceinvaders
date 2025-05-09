package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class HighScoresView extends JPanel {
    private final JButton backButton;

    public HighScoresView(List<String> scores) {
        this(scores, null);
    }

    public HighScoresView(List<String> scores, ActionListener backListener) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JLabel title = new JLabel("High Scores", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String score : scores) {
            String[] parts = score.split(",");
            if (parts.length == 2) {
                String name = parts[0].trim().toUpperCase();
                String value = parts[1].trim();
                listModel.addElement(name + ": " + value);
            } else {
                listModel.addElement(score);
            }
        }

        JList<String> scoreList = new JList<>(listModel);
        scoreList.setFont(new Font("Monospaced", Font.PLAIN, 20));
        scoreList.setForeground(Color.WHITE);
        scoreList.setBackground(Color.BLACK);
        add(new JScrollPane(scoreList), BorderLayout.CENTER);

        backButton = new JButton("← Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 20));
        backButton.setBackground(new Color(169, 169, 169));
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(120, 40));

        if (backListener != null) {
            backButton.addActionListener(backListener);
        }

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}