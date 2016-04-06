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
    private JLabel titleLabel;
    private JLabel enterLabel;
    private JLabel exitLabel;
    private JLabel payLabel;
    private int queueSize;

    /**
     * Constructor of AbstractView that expects a model belonging to this view
     *
     * @param model AbstractModel that belongs to this view
     */
    public QueueView(CarQueue model) {
        super(model);
        queueSize = model.countCars();

        this.size = new Dimension(100, 100);
        this.titleLabel = new JLabel();
        this.enterLabel = new JLabel();
        this.exitLabel = new JLabel();
        this.payLabel = new JLabel();

        titleLabel.setText("Cars in queue");
        enterLabel.setText("Entrance: ");
        exitLabel.setText("Exit: ");
        payLabel.setText("Pay: ");

    }

    public void updateView(){

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
