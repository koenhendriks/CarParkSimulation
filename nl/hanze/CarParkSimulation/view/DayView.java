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
    private JLabel hours;
    private JLabel days;
    private JLabel weeks;
    private JLabel startTime;
    private JLabel startTimeString;
    private JLabel currentTime;
    private JLabel simulationTime;
    private JLabel runningTime;
    private JLabel weekend;
    private JLabel weekendFalse;
    private JLabel weekendTrue;

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
        this.hours = new JLabel();
        this.minutes = new JLabel();
        this.days = new JLabel();
        this.weeks = new JLabel();
        this.runningTime = new JLabel();
        this.startTime = new JLabel();
        this.startTimeString = new JLabel();
        this.currentTime = new JLabel();
        this.simulationTime = new JLabel();
        this.weekendTrue = new JLabel();
        this.weekendFalse = new JLabel();

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
        runningTime = new JLabel(Language.get("runningTime"));


        /**
         * Set the location for the labels
         */
        titleLabel.setBounds(10, 5, 200, 20);

        startTime.setBounds(10, 40, 200, 20);
        startTimeString.setBounds(10, 60, 200, 20);

        currentTime.setBounds(10, 100, 200, 20);
        simulationTime.setBounds(10,120,200,20);

        runningTime.setBounds(10,160,200,20);
        minutes.setBounds(10,180,200,20);
        hours.setBounds(10,200,200,20);
        days.setBounds(10,220,200,20);
        weeks.setBounds(10,240,200,20);

        /**
         * Add the labels to the view
         */
        add(titleLabel);
        add(startTime);
        add(startTimeString);
        add(currentTime);
        add(simulationTime);
        add(runningTime);
        add(minutes);
        add(hours);
        add(days);
        add(weeks);
    }

    public void updateView(){

        Time time = (Time) super.model;

        simulationTime.setText(time.getCurrentTime());

        minutes.setText(Language.get("mins")+time.getRunningMinutes());
        hours.setText(Language.get("hrs")+time.getRunningHours());
        days.setText(Language.get("days")+time.getRunningDays());
        weeks.setText(Language.get("wks")+time.getRunningWeeks());


        setVisible(true);
        super.updateView();
    }
}
