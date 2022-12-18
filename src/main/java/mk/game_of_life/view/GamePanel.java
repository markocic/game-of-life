package mk.game_of_life.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class GamePanel extends JPanel {

    private int millisecondsDelay = 10;

    public GamePanel() {
        setBackground(Color.DARK_GRAY);
        initializeArray();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

    }

    public void initializeArray() {
    }

    public void showUpdate() {
        updateUI();
        try {
            Thread.sleep(millisecondsDelay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void showUpdate(int delay) {
        updateUI();
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMillisecondsDelay(int millisecondsDelay) {
        this.millisecondsDelay = millisecondsDelay;
        repaint();
    }
}
