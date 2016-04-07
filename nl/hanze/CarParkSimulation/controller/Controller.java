package nl.hanze.CarParkSimulation.controller;

import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarPark;
import nl.hanze.CarParkSimulation.main.CarParkSimulation;
import nl.hanze.CarParkSimulation.runner.CarParkSimulationRunner;

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
    private JButton startLimit;
    private JButton startButton;
    private JButton stopButton;
    private static boolean running;

    /**
     * Constructor of AbstractController with a model belong to this controller
     *
     * @param model AbstractModel that belongs to this controller
     */
    public Controller(AbstractModel model) {
        super(model);

        CarPark carPark = (CarPark) super.model;

        setLayout(null);

        description = new JLabel("Insert number of steps: ");
        description.setBounds(10,15,200,20);
        add(description);

        input = new JTextField("1");
        input.setBounds(10,40,75,20);
        add(input);

        startLimit = new JButton("Start");
        startLimit.setBounds(110,40,70,20);
        startLimit.addActionListener(this);
        add(startLimit);

        startButton = new JButton("Start");
        startButton.setBounds(410,55,70,20);
        startButton.addActionListener(this);
        add(startButton);

        stopButton = new JButton("Stop");
        stopButton.setBounds(670,55,70,20);
        stopButton.addActionListener(this);
        add(stopButton);

        running = true;


    }

    private void startPressed() {
        CarParkSimulation.running = true;
    }

    private void stopPressed() {
        CarParkSimulation.running = false;
    }

    private void startStepPressed(){

        try{
            int steps = Integer.parseInt(input.getText());

            setSteps(steps);

        } catch (NumberFormatException e){
            // TODO notify user that the field is not a number!
        }
    }

    /**
     * Set steps in the simulator
     *
     * @param steps amount of steps we should do
     */
    private void setSteps(int steps) {
        CarPark carPark = (CarPark) super.model;

        for(int i =0; i < steps; i++)
            carPark.tick(false);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getSource() == startButton){
            this.startPressed();
        } else if(actionEvent.getSource() == stopButton){
            this.stopPressed();
        } else if(actionEvent.getSource() == startLimit){
            this.startStepPressed();
        }

    }
}
