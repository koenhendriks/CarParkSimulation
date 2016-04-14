package nl.hanze.CarParkSimulation.view;

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
    }


    /**
     * Gets called by the super model class when something needs to be updated.
     */
    public void updateView(){

    }

}
