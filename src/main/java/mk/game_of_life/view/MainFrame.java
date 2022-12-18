package mk.game_of_life.view;

import lombok.Getter;
import lombok.Setter;
import mk.game_of_life.view.GamePanel;
import mk.game_of_life.view.MainMenu;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame {
    private static MainFrame instance = null;
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    private MainMenu menu;
    private GamePanel gamePanel;

    private MainFrame() {}

    private void initialize() {
        setTitle("Sorting Algorithms Visualizer");
        getContentPane().setBackground(Color.DARK_GRAY);
        getContentPane().setForeground(Color.WHITE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

        menu = new MainMenu();
        gamePanel = new GamePanel();

        getContentPane().add(menu, BorderLayout.NORTH);
        getContentPane().add(gamePanel, BorderLayout.CENTER);

        pack();
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

}
