package nl.hanze.CarParkSimulation.main;

import nl.hanze.CarParkSimulation.controller.AbstractController;
import nl.hanze.CarParkSimulation.controller.Controller;
import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarPark;
import nl.hanze.CarParkSimulation.view.AbstractView;
import nl.hanze.CarParkSimulation.view.CarParkView;
import nl.hanze.CarParkSimulation.view.GridView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class CarParkSimulation
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public class CarParkSimulation {

    private CarPark carParkModel;
    private JFrame screen;
    private AbstractView carParkView;
    private AbstractView gridView;
    private AbstractController carParkController;
    private int width;
    private int height;

    public CarParkSimulation() {
        /**
         * Set the dimension for the application
         */
        this.width = 1200;
        this.height = 750;

        /**
         * Create the model, view and controller that
         * we need for the Car Park Simulation
         */
        this.carParkModel = new CarPark(3,6,30);
        this.carParkController = new Controller(carParkModel);
        this.carParkView = new CarParkView(carParkModel);
        this.gridView = new GridView(carParkModel,this.width,this.height);

        /**
         * Create the JFrame that will display the views
         * and add these views to this JFrame
         */
        screen = new JFrame("Car Park Simulation");
        screen.setSize(this.width,this.height);
        screen.setLayout(null);

        /**
         * Add the views to the main screen
         */
        screen.getContentPane().add(carParkView);
        screen.getContentPane().add(gridView);

        carParkView.setBounds(10,10,800,500);
        gridView.setBounds(0,0,this.width,this.height);

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
         * Show the main screen and notify the views to update
         */
        screen.setVisible(true);
        carParkModel.notifyViews();
    }
}
