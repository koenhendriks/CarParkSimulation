package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.logic.CarPark;

import javax.swing.*;
import java.awt.*;

/**
 * Class QueueView
 *
 * This is a class for displaying the queue view.
 *
 * @author Ruben Buisman, Joey Boum Bletterman
 * @version 0.2 (11-04-2016)
 */

public class QueueView extends AbstractView
{
    // queue view size
    private Dimension size;

    // queue view labels
    private JLabel titleLabel;
    private JLabel enterLabel;
    private JLabel exitLabel;
    private JLabel payCashLabel;
    private JLabel payPassLabel;
    private JLabel payReservationLabel;

    // indices
    private int entranceIndex;
    private int exitIndex;
    private int payCashIndex;
    private int payPassIndex;
    private int payReservationIndex;

    /**
     * Constructor of AbstractView that expects a model belonging to this view.
     *
     * @param model AbstractModel that belongs to this view.
     */
    public QueueView(CarPark model) {
        super(model);

        this.size = new Dimension(100, 140);

        // create labels
        this.titleLabel = new JLabel();
        this.enterLabel = new JLabel();
        this.exitLabel = new JLabel();
        this.payCashLabel = new JLabel();
        this.payPassLabel = new JLabel();
        this.payReservationLabel = new JLabel();

        // set font for title label
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        // set the location for the labels
        titleLabel.setBounds(10, 5, 200, 20);
        enterLabel.setBounds(10,40,200,20);
        exitLabel.setBounds(10,60,200,20);
        payCashLabel.setBounds(10,80,200,20);
        payPassLabel.setBounds(10,100,200,20);
        payReservationLabel.setBounds(10,120,200,20);

        // add the labels to the view
        add(titleLabel);
        add(enterLabel);
        add(exitLabel);
        add(payCashLabel);
        add(payPassLabel);
        add(payReservationLabel);

        // set the title
        titleLabel.setText(Language.get("info"));
    }

    /**
     * Gets called by the super model class when something needs to be updated.
     */
    public void updateView(){

        CarPark carPark = (CarPark) super.model;

        entranceIndex = carPark.getEntranceIndex();
        exitIndex = carPark.getExitIndex();
        payCashIndex = carPark.getPayCashIndex();
        payPassIndex = carPark.getPayPassIndex();
        payReservationIndex = carPark.getPayReservationIndex();

        enterLabel.setText(Language.get("used") + entranceIndex);
        exitLabel.setText(Language.get("left") + exitIndex);
        payCashLabel.setText(Language.get("payCash") + payCashIndex);
        payPassLabel.setText(Language.get("payPass") + payPassIndex);
        payReservationLabel.setText(Language.get("payReservations") + payReservationIndex);

        setVisible(true);
        super.updateView();
    }
}
