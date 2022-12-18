package mk.game_of_life.controller;

import mk.game_of_life.view.MainFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseController extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
        MainFrame.getInstance().getGamePanel().mousePressed(e.getX(), e.getY());

    }
}
