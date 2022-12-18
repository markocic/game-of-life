package mk.game_of_life.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Rectangle2D;

@Getter
@Setter
public class Cell {

    private Dimension dimension;
    private Point point;
    private Shape shape;

    private boolean isAlive;

    public Cell( Point point, Dimension dimension, boolean isAlive) {
        this.point = point;
        this.dimension = dimension;
        this.isAlive = isAlive;
        this.shape = new Rectangle2D.Double(point.getX(), point.getY(), dimension.getWidth(), dimension.getHeight());
    }

    public void draw(Graphics2D g2) {
        if (isAlive) g2.setColor(Color.WHITE);
        else g2.setColor(Color.BLACK);

        g2.fill(shape);
    }
}
