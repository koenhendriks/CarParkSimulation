package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarPark;

import javax.swing.*;
import java.awt.*;

/**
 * Class for displaying statistics of the simulation.
 *
 * @author Joey Boum Bletterman, Ruben Buisman
 * @version 0.2 (11-4-2016)
 */
public class StatisticsView extends AbstractView
{
    // statistics labels
    private JLabel stats;
    private JLabel current;
    private JLabel expected;

    // price and revenue
    private int parkingPrice;
    private int currentRevenue;
    private int expectedRevenue;

    // euro sign string
    String euro = "\u20ac";

    /**
     * Constructor of AbstractView that expects a model belonging to this view.
     *
     * @param model AbstractModel that belongs to this view.
     */
    public StatisticsView(AbstractModel model) {
        super(model);

        // set the parking price in cents
        this.parkingPrice = 200;

        // create the labels
        this.stats = new JLabel();
        this.current = new JLabel();
        this.expected = new JLabel();

        // set the location for the labels
        stats.setBounds(10,5,200,20);
        current.setBounds(10,40,200,20);
        expected.setBounds(10,60,200,20);

        // set the text and the font for the title
        stats.setFont(new Font("Serif", Font.PLAIN, 20));
        stats.setText(Language.get("stats"));

        // add the labels to the view
        this.add(expected);
        this.add(current);
        this.add(stats);
    }

    /**
     * Method for updating the statistics view.
     */
    @Override
    public void updateView() {

        // get the time of a car parked and multiply this with the price of an hour
        CarPark carPark = (CarPark) super.model;
        this.currentRevenue = (((carPark.getTotalMinutes() / 60) * parkingPrice) / 100);
        this.expectedRevenue = (((carPark.getInMinutes() / 60) * parkingPrice) / 100);

        // set the text in the view with an euro sign and the current and expected revenue
        current.setText(Language.get("current")+ this.euro + (this.currentRevenue));
        expected.setText(Language.get("expected")+ this.euro + (this.expectedRevenue));

        setVisible(true);
        super.updateView();
    }

    /**
     * Method for resetting the statistics.
     */
    public void resetStats(){
        currentRevenue = 0;
        expectedRevenue = 0;
        updateView();
    }
}
