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

    private JLabel stats;
    private JLabel current;
    private JLabel expected;

    /**
     * Constructor of AbstractView that expects a model belonging to this view
     * @param model AbstractModel that belongs to this view
     */
    public StatisticsView(AbstractModel model) {
        super(model);
    }

    @Override
    public void updateView() {
        stats = new JLabel("Statistics");
        stats.setBounds(0,0,200,20);
        this.add(stats);

        current = new JLabel("Current revenue: ");
        current.setBounds(0,30,200,20);
        this.add(current);

        expected = new JLabel("Expected revenue: ");
        expected.setBounds(0,50,200,20);
        this.add(expected);


        setVisible(true);
        super.updateView();
    }
}
