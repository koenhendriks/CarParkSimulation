package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.logic.CarPark;
import nl.hanze.CarParkSimulation.logic.Time;
import nl.hanze.CarParkSimulation.view.AbstractView;

import javax.swing.*;
import java.awt.*;

/**
 * Class DayView
 *
 * @author Ruben Buisman
 * @version 0.1 (07-04-2016)
 */
public class DayView extends AbstractView {

    private Dimension size;

    private JLabel titleLabel;
    private JLabel minutes;
    private JLabel hour;
    private JLabel day;
    private JLabel startTime;
    private JLabel startTimeString;
    private JLabel currentTime;
    private JLabel simulationTime;

    /**
     * Constructor of AbstractView that expects a model belonging to this view
     *
     * @param model AbstractModel that belongs to this view
     */
    public DayView(Time model) {
        super(model);
        this.size = new Dimension(200, 330);
        Time time = (Time) super.model;


        /**
         * Create labels
         */
        this.titleLabel = new JLabel();
        this.minutes = new JLabel();
        this.hour = new JLabel();
        this.day = new JLabel();
        this.startTime = new JLabel();
        this.startTimeString = new JLabel();
        this.currentTime = new JLabel();
        this.simulationTime = new JLabel();

        /**
         * Set font for title label
         */
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        /**
         * Set static text labels
         */
        titleLabel.setText(Language.get("runTime"));
        startTime = new JLabel(Language.get("startTime"));
        startTimeString = new JLabel(time.getStartTime());
        currentTime = new JLabel(Language.get("currentTime"));
        simulationTime = new JLabel(time.getStartTime());


        /**
         * Set the location for the labels
         */
        titleLabel.setBounds(10, 5, 200, 20);

        startTime.setBounds(10, 40, 200, 20);
        startTimeString.setBounds(10, 60, 200, 20);

        currentTime.setBounds(10, 100, 200, 20);
        simulationTime.setBounds(10,120,200,20);

        minutes.setBounds(10,270,200,20);
        hour.setBounds(10,290,200,20);
        day.setBounds(10,310,200,20);

        /**
         * Add the labels to the view
         */
        add(titleLabel);
        add(startTime);
        add(startTimeString);
        add(currentTime);
        add(simulationTime);
        add(minutes);
        add(hour);
        add(day);
    }

    public void updateView(){

        Time time = (Time) super.model;

        simulationTime.setText(time.getCurrentTime());

        minutes.setText(Language.get("min")+time.getMinute());
        hour.setText(Language.get("hrs"));
        day.setText(Language.get("day"));

        setVisible(true);
        super.updateView();

    }
}
