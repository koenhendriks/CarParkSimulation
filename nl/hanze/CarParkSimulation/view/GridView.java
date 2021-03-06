package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.logic.AbstractModel;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Class GridView
 *
 * This is a class for a grid display.
 *
 * @author Koen Hendriks, Joey Boum Bletterman
 * @version 0.2 (11-04-2016)
 */
public class GridView extends AbstractView
{
    // grid dimensions
    private Dimension size;

    /**
     * Constructor of GridView that expects a model belonging to this view.
     *
     * @param model AbstractModel that belongs to this view.
     */
    public GridView(AbstractModel model) {
        super(model);
        this.size = new Dimension(1200,750);
    }

    /**
     * Constructor of GridView that expects a model belonging to this view
     * and a given width and height to set the grid to.
     *
     * @param model AbstractModel that belongs to this view.
     * @param width int width of the application.
     * @param height int height of the application.
     */
    public GridView(AbstractModel model, int width, int height) {
        super(model);
        this.size = new Dimension(width,height);
    }

    /**
     * Paint the grid on the JPanel.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        // draw the vertical lines of the grid
        for (int i = 10; i < size.width; i=i+10){
            Line2D line = new Line2D.Float(i,0,i,size.height);
            g2.draw(line);
        }

        // draw the horizontal lines of the grid
        for (int i = 10; i < size.height; i=i+10){
            Line2D line = new Line2D.Float(0,i,size.width,i);
            g2.draw(line);
        }
    }
}
