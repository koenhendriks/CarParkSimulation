package nl.hanze.CarParkSimulation.logic;

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
    private JLabel time;
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



    }

}
