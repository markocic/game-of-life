package mk.game_of_life.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame {
    private static MainFrame instance = null;
    private int WIDTH = 1280;
    private int HEIGHT = 720;
    private MainMenu menu;
    private GamePanel gamePanel;

    private MainFrame() {}

    private void initialize() {
        setTitle("Game of life");

        menu = new MainMenu();
        gamePanel = new GamePanel();

        WIDTH = (int) (gamePanel.calculateSize().getWidth() + menu.getWidth());
        HEIGHT = (int) (gamePanel.calculateSize().getHeight() + menu.getHeight());

        getContentPane().setBackground(Color.DARK_GRAY);
        getContentPane().setForeground(Color.WHITE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

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
