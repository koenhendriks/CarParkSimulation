package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.logic.AbstractModel;
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
    private JLabel label;

    /**
     * Constructor of AbstractView that expects a model belonging to this view
     *
     * @param model AbstractModel that belongs to this view
     */
    public QueueView(CarQueue model) {
        super(model);
        this.size = new Dimension(100, 100);
        this.label = new JLabel();
        label.setText("The queue is: ");
    }

    public void updateView(){
        label.setBounds(0,0,200,20);
        add(label);

        setVisible(true);

        super.updateView();

    }

}
