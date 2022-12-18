package mk.game_of_life.view;

import lombok.Getter;
import lombok.Setter;
import mk.game_of_life.controller.MouseController;
import mk.game_of_life.model.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class GamePanel extends JPanel {

    /*
     *  You can set custom dimensions and the window size will adjust accordingly.
     */

    private final int CELL_WIDTH = 20;
    private final int CELL_HEIGHT = 20;
    private final int CELL_PADDING = 2;
    private final int ROWS = 30;
    private final int COLUMNS = 60;

    private int millisecondsDelay = 10;
    private ArrayList<ArrayList<Cell>> cells = new ArrayList<>();

    private boolean isGameRunning = false;

    public GamePanel() {
        setBackground(new Color(51, 51, 51));
        this.addMouseListener(new MouseController());
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

    public void startGame() {
        isGameRunning = true;

        while(isGameRunning) {
            System.out.println("here");

            updateCells();

            showUpdate();
        }
    }

    private void updateCells() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Cell cell = cells.get(i).get(j);
                if (cell.isAlive() && isUnderOrOverpopulated(i, j)) {
                    cell.toggleAlive();
                }
                else if (!cell.isAlive() && isReproducted(i, j)) {
                    cell.toggleAlive();
                }
            }
        }
    }

    private boolean isReproducted(int x, int y) {
        // cell has exactly 3 live neighbours -> true
        // else -> false
        int count = getAliveCount(x, y);
        return count == 3;
    }

    private boolean isUnderOrOverpopulated(int x, int y) {
        // cell has 2 or 3 live neighbours -> false
        // else -> true
        int count = getAliveCount(x, y);
        return !(count == 2 || count == 3);
    }

    private int getAliveCount(int x, int y) {
        int count = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (x + i >= COLUMNS || x + i < 0) continue;
                else if (y + j >= ROWS || y + i < 0) continue;

                if (cells.get(x + i).get(y + j).isAlive()) count++;
            }
        }

        return count;
    }

    public void mousePressed(int x, int y) {
        for (ArrayList<Cell> row : cells) {
            for (Cell cell : row) {
                if (cell.getShape().contains(new Point(x, y))) {
                    cell.toggleAlive();
                    repaint();
                    return;
                }
            }
        }
    }
}
