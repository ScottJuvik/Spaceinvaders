import controller.GameController;
import model.GameModel;
import view.GameView;
import view.MainMenuView;
import view.HighScoresView;
import data.ScoreReader;

import javax.swing.*;
import java.awt.Image;
import java.util.List;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Space Invaders");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(512, 512);
            frame.setLocationRelativeTo(null);

            MainMenuView mainMenu = new MainMenuView();
            frame.setContentPane(mainMenu);
            frame.setVisible(true);

            mainMenu.addStartButtonListener(e -> {
                Image shipImage = new ImageIcon(App.class.getResource("/resources/ship.png")).getImage();
                List<Image> alienImages = Arrays.asList(
                        new ImageIcon(App.class.getResource("/resources/alien.png")).getImage(),
                        new ImageIcon(App.class.getResource("/resources/alien-cyan.png")).getImage(),
                        new ImageIcon(App.class.getResource("/resources/alien-magenta.png")).getImage(),
                        new ImageIcon(App.class.getResource("/resources/alien-yellow.png")).getImage());

                GameModel model = new GameModel(shipImage, alienImages);
                GameView view = new GameView(model);

                frame.setContentPane(view);
                frame.revalidate();

                new GameController(model, view);
            });

            mainMenu.addHighScoresButtonListener(e -> {
                List<String> scores = ScoreReader.readScoresWithNames();
                HighScoresView scoreView = new HighScoresView(scores, evt -> {
                    frame.setContentPane(mainMenu);
                    frame.revalidate();
                });
                frame.setContentPane(scoreView);
                frame.revalidate();
            });

            mainMenu.addQuitButtonListener(e -> System.exit(0));
        });
    }
}