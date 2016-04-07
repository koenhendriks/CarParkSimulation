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

        /**
         * Create the labels
         */
        this.stats = new JLabel();
        this.current = new JLabel();
        this.expected = new JLabel();

        /**
         * Set the location for the labels
         */
        stats.setBounds(10,5,200,20);
        current.setBounds(10,40,200,20);
        expected.setBounds(10,60,200,20);

        /**
         * Set the text and the font for the title
         */
        stats.setFont(new Font("Serif", Font.PLAIN, 20));
        stats.setText("Statistics");

        /**
         * Add the labels to the view
         */
        this.add(expected);
        this.add(current);
        this.add(stats);
    }

    @Override
    public void updateView() {

        current.setText("Current revenue: ");
        expected.setText("Expected revenue: ");

        setVisible(true);
        super.updateView();
    }
}
