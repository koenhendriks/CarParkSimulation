package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.logic.AbstractModel;

import javax.swing.*;
import java.awt.*;

/**
 * Class for displaying statistics of the simulation.
 * @author Joey Boum Bletterman
 * @version 0.1 (5/4/2016)
 */
public class StatisticsView extends AbstractView {

    private Dimension size;
    private int parked;
    private JTextField revenue;

    /**
     * Constructor of AbstractView that expects a model belonging to this view
     * @param model AbstractModel that belongs to this view
     */
    public StatisticsView(AbstractModel model) {
        super(model);
        this.size = new Dimension(100,100);
    }

    /**
     * Overloaded constructor of AbstractView that expects a model belonging to this view
     * Dimensions can be specified in parameters
     * @param model AbstractModel that belongs to this view
     * @param width Width of the model
     * @param height Height of the model
     */
    public StatisticsView(AbstractModel model, int width, int height) {
        super(model);
        this.size = new Dimension(width,height);
    }

    @Override
    public void updateView() {
        super.updateView();
    }
}
