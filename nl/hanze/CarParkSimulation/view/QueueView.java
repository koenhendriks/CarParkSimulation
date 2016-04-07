package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.logic.CarPark;

import javax.swing.*;
import java.awt.*;

/**
 * Class QueueView
 *
 * @author Ruben Buisman
 * @version 0.1 (05-04-2016)
 */

public class QueueView extends AbstractView {

    private Dimension size;
    private JLabel titleLabel;
    private JLabel enterLabel;
    private JLabel exitLabel;
    private JLabel payLabel;
    private int entranceIndex;
    private int exitIndex;
    private int payIndex;


    /**
     * Constructor of AbstractView that expects a model belonging to this view
     *
     * @param model AbstractModel that belongs to this view
     */
    public QueueView(CarPark model) {
        super(model);

        this.size = new Dimension(100, 100);

        /**
         * Create labels
         */
        this.titleLabel = new JLabel();
        this.enterLabel = new JLabel();
        this.exitLabel = new JLabel();
        this.payLabel = new JLabel();

        /**
         * Set font for title label
         */
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        /**
         * Set the location for the labels
         */
        titleLabel.setBounds(10, 5, 200, 20);
        enterLabel.setBounds(10,40,200,20);
        exitLabel.setBounds(10,60,200,20);
        payLabel.setBounds(10,80,200,20);

        /**
         * Add the labels to the view
         */
        add(titleLabel);
        add(enterLabel);
        add(exitLabel);
        add(payLabel);

        /**
         * Set the title
         */
        titleLabel.setText("Carpark info");
    }

    /**
     * Get's called by the super model class when something needs to be updated
     */
    public void updateView(){

        CarPark carPark = (CarPark) super.model;

        entranceIndex = carPark.getEntranceIndex();
        exitIndex = carPark.getExitIndex();
        payIndex = carPark.getPayIndex();

        enterLabel.setText("Used the entrance: " + entranceIndex);
        exitLabel.setText("Left the carpark: " + exitIndex);
        payLabel.setText("Paying customers: " + payIndex);

        setVisible(true);
        super.updateView();

    }

}
