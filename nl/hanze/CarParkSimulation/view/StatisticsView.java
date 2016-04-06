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

        this.stats = new JLabel();
        this.current = new JLabel();
        this.expected = new JLabel();
    }

    @Override
    public void updateView() {
        stats.setText("Statistics");
        stats.setBounds(10,5,200,20);
        stats.setFont(new Font("Serif", Font.PLAIN, 20));
        this.add(stats);

        current.setText("Current revenue: ");
        current.setBounds(10,40,200,20);
        this.add(current);

        expected.setText("Expected revenue: ");
        expected.setBounds(10,60,200,20);
        this.add(expected);


        setVisible(true);
        super.updateView();
    }
}
