package nl.hanze.CarParkSimulation.controller;

import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarPark;
import nl.hanze.CarParkSimulation.main.CarParkSimulation;
import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.view.AboutView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTML;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class Controller
 * This class is used to add controllers to views in the view package.
 *
 * @author Koen Hendriks, Joey Boum Bletterman
 * @version 0.2 (11-04-2016)
 */
public class Controller extends AbstractController implements ActionListener
{
    // about dialog
    public static JDialog aboutDialog;

    // labels for steps and speed
    private JLabel insertSteps;
    private JLabel speedLabel;

    // fields for steps and speed
    private JTextField stepsField;
    private JTextField speedField;

    // buttons for steps, speed, starting and stopping
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

        setLayout(null);

        // menu
        CarParkSimulation.resetItem.addActionListener(this);
        CarParkSimulation.exitItem.addActionListener(this);
        CarParkSimulation.aboutItem.addActionListener(this);

        // about dialog
        aboutDialog = new JDialog(CarParkSimulation.SCREEN, Language.get("about"), true);
        aboutDialog.add(new AboutView());
        aboutDialog.pack();
        aboutDialog.setLocationRelativeTo(CarParkSimulation.SCREEN);
        aboutDialog.setLocation(350,85);
        aboutDialog.setSize(500,500);
        aboutDialog.setResizable(false);

        // custom step counter
        insertSteps = new JLabel(Language.get("insertSteps"));
        insertSteps.setBounds(10,0,200,20);
        add(insertSteps);

        stepsField = new JTextField(Language.get("input"));
        stepsField.setBounds(10,20,75,20);
        stepsField.addActionListener(this);
        add(stepsField);

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
        speedField.addActionListener(this);
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

    /**
     * Method for action definition of Reset item.
     */
    private void resetPressed() {
        CarParkSimulation.resetSimulation();
    }

    /**
     * Method for action definition of Exit item.
     */
    private void exitPressed() {
        int confirm = JOptionPane.showOptionDialog(CarParkSimulation.SCREEN,
                Language.get("confirmExit"),
                Language.get("confirmExitTitle"), JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == 0) {
            System.exit(0); // 0 when execution went fine;
        }
    }

    /**
     * Method for action definition of About item.
     */
    private void aboutPressed() {
        aboutDialog.setVisible(true);
    }

    /**
     * Method for action definition of start button.
     */
    private void startPressed() {
        CarParkSimulation.running = true;
    }

    /**
     * Method for action definition of stop button.
     */
    private void stopPressed() {
        CarParkSimulation.running = false;
    }

    /**
     * Method for action definition of startStep button.
     */
    private void startStepPressed(){

        try{
            int steps = Integer.parseInt(stepsField.getText());

            setSteps(steps);

        } catch (NumberFormatException e){
            // TODO notify user that the field is not a number!
        }
    }

    /**
     * Method for action definition of speed button.
     */
    private void speedPressed(){

        try{
            int speed = Integer.parseInt(speedField.getText());

            CarParkSimulation.simulationSpeed = speed;

        } catch (NumberFormatException e){
            // TODO notify user that the field is not a number!
        }
    }

    /**
     * Method for setting steps in the simulator.
     *
     * @param steps Amount of steps we should make.
     */
    private void setSteps(int steps) {
        CarPark carPark = (CarPark) super.model;

        for(int i =0; i < steps; i++)
            carPark.tick();
    }

    /**
     * Method to close the about dialog
     */
    public static void closeAboutDialog(){
        aboutDialog.setVisible(false);
    }

    /**
     * Method for performing actions.
     * @param actionEvent The action we are listening for.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getSource() == startButton){
            this.startPressed();
        }
        else if(actionEvent.getSource() == stopButton){
            this.stopPressed();
        }
        else if(actionEvent.getSource() == startLimit){
            this.startStepPressed();
        }
        else if(actionEvent.getSource() == stepsField) {
            this.startStepPressed();
        }
        else if(actionEvent.getSource() == speedButton){
            this.speedPressed();
        }
        else if(actionEvent.getSource() == speedField) {
            this.speedPressed();
        }
        else if(actionEvent.getSource() == CarParkSimulation.exitItem){
            this.exitPressed();
        }
        else if(actionEvent.getSource() == CarParkSimulation.aboutItem) {
           this.aboutPressed();
        }
        else if(actionEvent.getSource() == CarParkSimulation.resetItem) {
            this.resetPressed();
        }

    }
}
