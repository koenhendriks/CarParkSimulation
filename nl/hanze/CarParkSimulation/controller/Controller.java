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
    private JButton start;
    /**
     * Constructor of AbstractController with a model belong to this controller
     *
     * @param model AbstractModel that belongs to this controller
     */
    public Controller(AbstractModel model) {
        super(model);
        start = new JButton("start");
        start.setBounds(50,0,50,50);
        add(start);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
