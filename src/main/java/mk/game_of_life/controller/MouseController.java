package mk.game_of_life.controller;

import mk.game_of_life.model.Cell;
import mk.game_of_life.view.GamePanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MouseController extends MouseAdapter implements MouseMotionListener {

    private GamePanel gamePanel;
    private ArrayList<Cell> updatedCells;

    public MouseController(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.updatedCells = new ArrayList<>();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point cellPoint = getCellLocation(e.getX(), e.getY());

        Cell cell = gamePanel.getCells().get((int) cellPoint.getX()).get((int) cellPoint.getY());

        cell.toggleAlive();
        updatedCells.add(cell);
        gamePanel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        updatedCells.clear();
        gamePanel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point cellPoint = getCellLocation(e.getX(), e.getY());

        Cell cell = gamePanel.getCells().get((int) cellPoint.getX()).get((int) cellPoint.getY());

        if (!updatedCells.contains(cell)){
            cell.toggleAlive();
            updatedCells.add(cell);
        }

        gamePanel.repaint();

    }

    public Point getCellLocation(int x, int y) {
        int i = x / (gamePanel.getCELL_WIDTH()  + gamePanel.getCELL_PADDING());
        int j = y / (gamePanel.getCELL_HEIGHT() + gamePanel.getCELL_PADDING());

        // checks to prevent IndexOutOfBounds error
        if (x < 0) i = 0;
        if (y < 0) j = 0;

        if (i >= gamePanel.getCOLUMNS()) i = gamePanel.getCOLUMNS() - 1;
        if (j >= gamePanel.getROWS()) j = gamePanel.getROWS() - 1;

        return new Point(i, j);
    }
}
