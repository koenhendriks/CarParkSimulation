package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarPark;
import nl.hanze.CarParkSimulation.logic.Time;

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
    private JLabel yesterday;
    private JLabel expected;
    private JLabel total;

    // price and revenue
    private int parkingPrice;
    private int currentRevenue;
    private int expectedRevenue;
    private int yesterdayRevenue;
    private int totalRevenue;
    private int tempRevenue;

    private int currentDay;
    private Time time;

    // euro sign string
    String euro = "\u20ac";

    /**
     * Constructor of AbstractView that expects a model belonging to this view.
     *
     * @param model AbstractModel that belongs to this view.
     */
    public StatisticsView(AbstractModel model) {
        super(model);
        CarPark carPark = (CarPark) super.model;
        time = carPark.getTime();
        currentDay = Integer.valueOf(time.getRunningDays());

        // set the parking price in cents
        this.parkingPrice = 200;

        // create the labels
        this.stats = new JLabel();
        this.current = new JLabel();
        this.expected = new JLabel();
        this.yesterday = new JLabel();
        this.total = new JLabel();

        // set the location for the labels
        stats.setBounds(10,5,200,20);
        expected.setBounds(10,40,200,20);
        current.setBounds(10,60,200,20);
        yesterday.setBounds(10,80,200,20);
        total.setBounds(10,100,200,20);

        // set the text and the font for the title
        stats.setFont(new Font("Serif", Font.PLAIN, 20));
        stats.setText(Language.get("stats"));

        // add the labels to the view
        this.add(expected);
        this.add(stats);
        this.add(current);
        this.add(yesterday);
        this.add(total);
    }

    /**
     * Method for updating the statistics view.
     */
    @Override
    public void updateView() {

        CarPark carPark = (CarPark) super.model;

        int runningDay = Integer.valueOf(time.getRunningDays());

        // get the time of a car parked and multiply this with the price of an hour
        currentRevenue = (((carPark.getTotalMinutes() / 60) * parkingPrice) / 100);
        expectedRevenue = (((carPark.getInMinutes() / 60) * parkingPrice) / 100);

        if(runningDay > currentDay){
            yesterdayRevenue = currentRevenue;
            tempRevenue = tempRevenue + yesterdayRevenue;
            carPark.setTotalMinutes(0);
            currentDay = runningDay;
        }

        totalRevenue = tempRevenue + currentRevenue;

        // set the text in the view with an euro sign and the current and expected revenue
        expected.setText(Language.get("expected")+ this.euro + (this.expectedRevenue));
        current.setText(Language.get("current")+ this.euro + (this.currentRevenue));
        yesterday.setText(Language.get("yesterday")+ this.euro + (this.yesterdayRevenue));
        total.setText(Language.get("total")+ this.euro + (this.totalRevenue));

        setVisible(true);
        super.updateView();
    }

    /**
     * Method for resetting the statistics.
     */
    public void resetStats(){
        expectedRevenue = 0;
        currentRevenue = 0;
        yesterdayRevenue = 0;
        totalRevenue = 0;
        updateView();
    }
}
