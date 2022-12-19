package mk.game_of_life.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame {
    private static MainFrame instance = null;
    private MainMenu menu;
    private GamePanel gamePanel;

    private MainFrame() {}

    private void initialize() {
        setTitle("Game of life");

        menu = new MainMenu();
        gamePanel = new GamePanel();

        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

        this.add(menu, BorderLayout.NORTH);
        this.add(gamePanel, BorderLayout.CENTER);

        gamePanel.setSize(gamePanel.calculateSize());
        gamePanel.setPreferredSize(gamePanel.calculateSize());
        gamePanel.setMinimumSize(gamePanel.calculateSize());


        this.pack();
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

}
