package nl.hanze.CarParkSimulation.controller;

import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarPark;
import nl.hanze.CarParkSimulation.main.CarParkSimulation;
import nl.hanze.CarParkSimulation.runner.CarParkSimulationRunner;
import nl.hanze.CarParkSimulation.localization.en.Language;

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
    private JLabel speedLabel;
    private JTextField input;
    private JTextField speedField;
    private JButton startLimit;
    private JButton speedButton;
    private JButton startButton;
    private JButton stopButton;

    /**
     * Constructor of AbstractController with a model belong to this controller
     *
     * @param model AbstractModel that belongs to this controller
     */
    public Controller(AbstractModel model) {
        super(model);

        CarPark carPark = (CarPark) super.model;

        setLayout(null);

        // custom step counter
        description = new JLabel(Language.get("insertSteps"));
        description.setBounds(10,0,200,20);
        add(description);

        input = new JTextField(Language.get("input"));
        input.setBounds(10,20,75,20);
        add(input);

        startLimit = new JButton(Language.get("startLimit"));
        startLimit.setBounds(110,20,70,20);
        startLimit.addActionListener(this);
        add(startLimit);

        // custom speed counter
        speedLabel = new JLabel(Language.get("speedLabel"));
        speedLabel.setBounds(10,45,200,20);
        add(speedLabel);

        speedField = new JTextField(Language.get("speedField"));
        speedField.setBounds(10,65,75,20);
        add(speedField);

        speedButton = new JButton(Language.get("speedButton"));
        speedButton.setBounds(110,65,70,20);
        speedButton.addActionListener(this);
        add(speedButton);

        // start and stop
        startButton = new JButton(Language.get("startButton"));
        startButton.setBounds(410,60,70,20);
        startButton.addActionListener(this);
        add(startButton);

        stopButton = new JButton(Language.get("stopButton"));
        stopButton.setBounds(670,60,70,20);
        stopButton.addActionListener(this);
        add(stopButton);
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

    private void speedPressed(){

        try{
            int speed = Integer.parseInt(speedField.getText());

            CarParkSimulation.simulationSpeed = speed;

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
        } else if(actionEvent.getSource() == speedButton){
            this.speedPressed();
        }

    }
}
