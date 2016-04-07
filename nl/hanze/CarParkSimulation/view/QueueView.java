package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.localization.en.Language;
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
    private JLabel payCashLabel;
    private JLabel payPassLabel;

    private int entranceIndex;
    private int exitIndex;
    private int payCashIndex;
    private int payPassIndex;


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
        this.payCashLabel = new JLabel();
        this.payPassLabel = new JLabel();

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
        payCashLabel.setBounds(10,80,200,20);
        payPassLabel.setBounds(10,100,200,20);

        /**
         * Add the labels to the view
         */
        add(titleLabel);
        add(enterLabel);
        add(exitLabel);
        add(payCashLabel);
        add(payPassLabel);

        /**
         * Set the title
         */
        titleLabel.setText(Language.get("info"));
    }

    /**
     * Get's called by the super model class when something needs to be updated
     */
    public void updateView(){

        CarPark carPark = (CarPark) super.model;

        entranceIndex = carPark.getEntranceIndex();
        exitIndex = carPark.getExitIndex();
        payCashIndex = carPark.getPayCashIndex();
        payPassIndex = carPark.getPayPassIndex();

        enterLabel.setText(Language.get("used") + entranceIndex);
        exitLabel.setText(Language.get("left") + exitIndex);
        payCashLabel.setText(Language.get("payCash") + payCashIndex);
        payPassLabel.setText(Language.get("payPass") + payPassIndex);

        setVisible(true);
        super.updateView();

    }

}
