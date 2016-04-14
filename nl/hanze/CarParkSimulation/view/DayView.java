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
 * This view describes the current time statistics.
 * It indicates when the simulation started and for
 * how long it has been running.
 *
 * @author Ruben Buisman, Joey Boum Bletterman, Koen Hendriks
 * @version 0.3 (11-04-2016)
 */
public class DayView extends AbstractView
{
    // size of the view
    private Dimension size;

    // time labels
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
     * Constructor of AbstractView that expects a model belonging to this view.
     *
     * @param model AbstractModel that belongs to this view.
     */
    public DayView(Time model) {
        super(model);
        this.size = new Dimension(200, 330);
        Time time = (Time) super.model;

        // create labels
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
        this.weekend = new JLabel();
        this.weekendTrue = new JLabel();
        this.weekendFalse = new JLabel();

        // set font for title label
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        // set static text labels
        titleLabel.setText(Language.get("runTime"));
        startTime = new JLabel(Language.get("startTime"));
        startTimeString = new JLabel(time.getStartTime());
        currentTime = new JLabel(Language.get("currentTime"));
        simulationTime = new JLabel(time.getStartTime());
        runningTime = new JLabel(Language.get("runningTime"));
        weekend = new JLabel(Language.get("weekend"));
        weekendFalse = new JLabel(Language.get("no"));
        weekendTrue = new JLabel(Language.get("yes"));

        // set the location for the labels
        titleLabel.setBounds(10, 5, 200, 20);

        // start time
        startTime.setBounds(10, 40, 200, 20);
        startTimeString.setBounds(10, 60, 200, 20);

        // current time
        currentTime.setBounds(10, 100, 200, 20);
        simulationTime.setBounds(10,120,200,20);

        // time running
        runningTime.setBounds(10,160,200,20);
        minutes.setBounds(10,180,200,20);
        hours.setBounds(10,200,200,20);
        days.setBounds(10,220,200,20);
        weeks.setBounds(10,240,200,20);
        weekend.setBounds(10,280,200,20);
        weekendFalse.setBounds(10,300,200,20);
        weekendTrue.setBounds(10,300,200,20);

        // add the labels to the view
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
        add(weekend);
        add(weekendTrue);
        add(weekendFalse);
    }

    /**
     * Method for updating views.
     */
    @Override
    public void updateView(){

        Time time = (Time) super.model;

        simulationTime.setText(time.getCurrentTime());
        startTimeString.setText(time.getStartTime());

        minutes.setText(Language.get("mins")+time.getRunningMinutes());
        hours.setText(Language.get("hrs")+time.getRunningHours());
        days.setText(Language.get("days")+time.getRunningDays());
        weeks.setText(Language.get("wks")+time.getRunningWeeks());

        if (time.isWeekend()) {
            weekendFalse.setVisible(false);
            weekendTrue.setVisible(true);
        }
        else {
            weekendTrue.setVisible(false);
            weekendFalse.setVisible(true);
        }

        setVisible(true);
        super.updateView();
    }
}
