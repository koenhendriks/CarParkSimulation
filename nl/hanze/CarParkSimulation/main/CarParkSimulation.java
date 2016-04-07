package nl.hanze.CarParkSimulation.main;

import nl.hanze.CarParkSimulation.controller.AbstractController;
import nl.hanze.CarParkSimulation.controller.Controller;
import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarPark;
import nl.hanze.CarParkSimulation.logic.CarQueue;
import nl.hanze.CarParkSimulation.logic.DayView;
import nl.hanze.CarParkSimulation.view.AbstractView;
import nl.hanze.CarParkSimulation.view.CarParkView;
import nl.hanze.CarParkSimulation.view.GridView;
import nl.hanze.CarParkSimulation.view.StatisticsView;
import nl.hanze.CarParkSimulation.view.QueueView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class CarParkSimulation
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public final class CarParkSimulation {

    private CarPark carParkModel;
    private JFrame screen;
    private AbstractView carParkView;
    private AbstractView queueView;
    private AbstractView dayView;
    private AbstractView gridView;
    private AbstractView statisticsView;
    private AbstractController carParkController;
    private AbstractController controller;
    private int width;
    private int height;
    private int steps;

    /**
     * The simulations speed means the amount of miliseconds
     * it takes to simulate 1 minute in the car park.
     */
    public static int simulationSpeed = 1000;

    public static boolean running;

    public CarParkSimulation() {
        // set default number of steps
        steps = 300000;

        /**
         * Set the dimension for the application
         */
        this.width = 1200;
        this.height = 750;

        /**
         * Create the model, view and controller that
         * we need for the Car Park Simulation
         */
        this.carParkModel = new CarPark(3, 6, 30);
        this.carParkController = new Controller(carParkModel);
        this.carParkView = new CarParkView(carParkModel);
        this.queueView = new QueueView(carParkModel);
        this.dayView = new DayView(carParkModel);
        this.statisticsView = new StatisticsView(carParkModel);
        this.controller = new Controller(carParkModel);

        /**
         * Create the JFrame that will display the views
         * and add these views to this JFrame
         */
        screen = new JFrame(Language.get("title"));
        screen.setSize(this.width, this.height);
        screen.setLayout(null);

        /**
         * Add the views to the main screen
         */
        screen.getContentPane().add(carParkView);
        screen.getContentPane().add(dayView);
        screen.getContentPane().add(queueView);
        screen.getContentPane().add(statisticsView);

        /**
         * Set the location of the views on the screen
         */
        carParkView.setBounds(260,10,680,300);
        statisticsView.setBounds(30,140, 200,100);
        queueView.setBounds(30,10,200,120);
        dayView.setBounds(970,10,200, 150);


        /**
         * Add the controllers to the main screen
         */
        screen.getContentPane().add(controller);

        controller.setBounds(30,250,910,90);
        /**
         * Add a window listener to the SimulatorView so we can send
         * a confirmation to the user so we know they are sure if
         * they want to close the Car Park Simulation
         */
        screen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(carParkView,
                        Language.get("confirmExit"),
                        Language.get("confirmExitTitle"), JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    System.exit(0); // 0 when execution went fine;
                }
            }
        });

        /**
         * We only want to exit the application if the user has
         * confirmed our message, otherwise we don't want to
         * do anything, so we change the default close
         */
        screen.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        /**
         * Show the main screen, disable resizing and notify the views to update
         */
        screen.setVisible(true);
        screen.setResizable(false);

        /**
         * Debug: Draw a grid with 10 by 10 squares
         */
        this.gridView = new GridView(new AbstractModel() {}, this.width, this.height);
        gridView.setBounds(0, 0, this.width, this.height);
        screen.getContentPane().add(gridView);

        /**
         * Initialize views
         */
        carParkView.updateView();

        running = true;

        while(true){
            if (running) {
                carParkModel.tick(true);
            }
            try{
                Thread.sleep(simulationSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Getter for steps
     * @return the amount of steps the simulation executes
     */
    public int getSteps() {
        return steps;
    }

    /**
     * Setter for steps
     * @param steps the amount of steps the simulation executes
     */
    public void setSteps(int steps) {
        this.steps = steps;
    }

}
