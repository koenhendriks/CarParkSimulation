package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarPark;
import nl.hanze.CarParkSimulation.logic.CarQueue;

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

    /**
     * Constructor of AbstractView that expects a model belonging to this view
     *
     * @param model AbstractModel that belongs to this view
     */
    public QueueView(CarPark model) {
        super(model);

        this.size = new Dimension(100, 100);
        this.titleLabel = new JLabel();
        this.enterLabel = new JLabel();
        this.exitLabel = new JLabel();
        this.payLabel = new JLabel();

    }

    public void updateView(){

        CarPark carPark = (CarPark) super.model;

        int entranceIndex = carPark.getEntranceIndex();
        int exitIndex = carPark.getExitIndex();
        int payIndex = carPark.getPayIndex();

        titleLabel.setText("Carpark info:");
        enterLabel.setText("Used the entrance: " + entranceIndex);
        exitLabel.setText("Left the carpark: " + exitIndex);
        payLabel.setText("Paying customers: " + payIndex);

        titleLabel.setBounds(10, 5, 200, 20);
        enterLabel.setBounds(10,40,200,20);
        exitLabel.setBounds(10,60,200,20);
        payLabel.setBounds(10,80,200,20);

        add(titleLabel);
        add(enterLabel);
        add(exitLabel);
        add(payLabel);

        setVisible(true);
        super.updateView();

    }

}
