package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.logic.AbstractModel;

import javax.swing.*;
import java.awt.*;

/**
 * Class QueueLengthView
 *
 * @author Ruben Buisman
 * @version 0.1 (14-04-2016)
 */
public class QueueLengthView extends AbstractView {

    // queue view size
    private Dimension size;

    // queue view labels
    private JLabel titleLabel;
    private JLabel entranceLabel;
    private JLabel payLabel;
    private JLabel exitLabel;


    /**
     * Constructor of AbstractView that expects a model belonging to this view.
     *
     * @param model AbstractModel that belongs to this view.
     */
    public QueueLengthView(AbstractModel model) {
        super(model);
        this.size = new Dimension(100, 140);

        // create labels
        this.titleLabel = new JLabel();
        this.entranceLabel = new JLabel();
        this.payLabel = new JLabel();
        this.exitLabel = new JLabel();

        // set font for titleLabel
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        // set the location for the labels
        titleLabel.setBounds(10,5,200,20);
        entranceLabel.setBounds(10,40,200,20);
        payLabel.setBounds(10,60,200,20);
        exitLabel.setBounds(10,80,200,20);

        // add the labels to the view
        add(titleLabel);
        add(entranceLabel);
        add(payLabel);
        add(exitLabel);

        //set the title
        titleLabel.setText(Language.get("lengthTitle"));

    }


    /**
     * Gets called by the super model class when something needs to be updated.
     */
    public void updateView(){


    }

}
