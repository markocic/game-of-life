package mk.game_of_life.view;

import lombok.Getter;
import lombok.Setter;
import mk.game_of_life.model.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class GamePanel extends JPanel {

    private final int CELL_WIDTH = 20;
    private final int CELL_HEIGHT = 20;
    private final int CELL_PADDING = 2;
    private final int ROWS = 30;
    private final int COLUMNS = 60;

    private int millisecondsDelay = 10;
    private ArrayList<ArrayList<Cell>> cells = new ArrayList<>();

    public GamePanel() {
        setBackground(new Color(51, 51, 51));
        initializeArray();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (ArrayList<Cell> row : cells) {
            for (Cell cell : row) {
                cell.draw(g2);
            }
        }

    }

    public void initializeArray() {
        for (int i = 0; i < COLUMNS; i++) {
            ArrayList<Cell> temp = new ArrayList<>();
            for (int j = 0; j < ROWS; j++) {
                temp.add(new Cell(new Point((CELL_WIDTH + CELL_PADDING) * i, (CELL_WIDTH + CELL_PADDING) * j),
                                  new Dimension(CELL_WIDTH, CELL_HEIGHT), true));
            }

            cells.add(temp);
        }
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

    public Dimension calculateSize() {
        return new Dimension(COLUMNS * (CELL_WIDTH + CELL_PADDING), ROWS * (CELL_HEIGHT + CELL_PADDING));
    }
}
