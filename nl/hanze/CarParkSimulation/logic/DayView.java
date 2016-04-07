package nl.hanze.CarParkSimulation.logic;

import nl.hanze.CarParkSimulation.localization.en.Language;
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
    private JLabel week;
    private JLabel year;

    /**
     * Constructor of AbstractView that expects a model belonging to this view
     *
     * @param model AbstractModel that belongs to this view
     */
    public DayView(CarPark model) {
        super(model);
        this.size = new Dimension(100, 100);

        /**
         * Create labels
         */
        this.titleLabel = new JLabel();
        this.minutes = new JLabel();
        this.hour = new JLabel();
        this.day = new JLabel();
        this.week = new JLabel();
        this.year = new JLabel();

        /**
         * Set font for title label
         */
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        /**
         * Set the location for the labels
         */
        titleLabel.setBounds(10, 5, 200, 20);
        minutes.setBounds(10,40,200,20);
        hour.setBounds(10,60,200,20);
        day.setBounds(10,80,200,20);
        week.setBounds(10,100,200,20);
        year.setBounds(10,120,200,20);

        /**
         * Add the labels to the view
         */
        add(titleLabel);
        add(minutes);
        add(hour);
        add(day);
        add(week);
        add(year);

        /**
         * Set the title
         */
        titleLabel.setText(Language.get("runTime"));
    }

    public void updateView(){

        minutes.setText(Language.get("min"));
        hour.setText(Language.get("hrs"));
        day.setText(Language.get("day"));
        week.setText(Language.get("wks"));
        year.setText(Language.get("yrs"));

        setVisible(true);
        super.updateView();

    }
}
