package nl.hanze.CarParkSimulation.controller;

import nl.hanze.CarParkSimulation.logic.AbstractModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class Controller
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public class Controller extends AbstractController implements ActionListener {
    private JLabel description;
    private JTextField input;
    private JButton start;
    /**
     * Constructor of AbstractController with a model belong to this controller
     *
     * @param model AbstractModel that belongs to this controller
     */
    public Controller(AbstractModel model) {
        super(model);
        setLayout(null);

        description = new JLabel("Insert number of steps: ");
        description.setBounds(10,0,200,20);
        add(description);

        input = new JTextField("");
        input.setBounds(10,25,75,20);
        add(input);

        start = new JButton("start");
        start.setBounds(110,25,70,20);
        add(start);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
