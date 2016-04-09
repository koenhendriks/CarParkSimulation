package nl.hanze.CarParkSimulation.main;

import nl.hanze.CarParkSimulation.controller.AbstractController;
import nl.hanze.CarParkSimulation.controller.Controller;
import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarPark;
import nl.hanze.CarParkSimulation.logic.Time;
import nl.hanze.CarParkSimulation.view.DayView;
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

    private static CarPark carParkModel;
    private Time timeModel;
    private AbstractView carParkView;
    private AbstractView queueView;
    private AbstractView dayView;
    private AbstractView gridView;
    private static StatisticsView statisticsView;
    private AbstractController controller;
    private int width;
    private int height;
    public static final JFrame screen = new JFrame(Language.get("title"));

    // the menu
    public static final JMenuBar menubar = new JMenuBar();
    public static final JMenu fileMenu = new JMenu(Language.get("fileMenu"));
    public static final JMenu helpMenu = new JMenu(Language.get("helpMenu"));
    public static final JMenuItem resetItem = new JMenuItem(Language.get("resetItem"));
    public static final JMenuItem exitItem = new JMenuItem(Language.get("exitItem"));
    public static final JMenuItem aboutItem = new JMenuItem(Language.get("aboutItem"));

    /**
     * The simulations speed means the amount of miliseconds
     * it takes to simulate 1 minute in the car park.
     */
    public static int simulationSpeed = 1000;

    public static boolean running;

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
        this.timeModel = new Time();
        this.carParkModel = new CarPark(3, 6, 30,timeModel);
        this.carParkView = new CarParkView(carParkModel);
        this.queueView = new QueueView(carParkModel);
        this.dayView = new DayView(timeModel);
        this.statisticsView = new StatisticsView(carParkModel);
        this.controller = new Controller(carParkModel);

        /**
         * Create the JFrame that will display the views
         * and add these views to this JFrame
         */
        screen.setSize(this.width, this.height);
        screen.setLayout(null);

        /**
         * Add the menu to the frame
         */
        // add the menu bar
        screen.add(menubar);

        // add the file menu and items
        menubar.add(fileMenu);
        fileMenu.add(resetItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(exitItem);


        // add the help menu and items
        menubar.add(helpMenu);
        helpMenu.add(aboutItem);

        menubar.setBounds(0,0,1200,20);
        menubar.setVisible(true);

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
        carParkView.setBounds(260,30,680,300);
        statisticsView.setBounds(30,160, 200,100);
        queueView.setBounds(30,30,200,120);
        dayView.setBounds(970,30,200, 330);


        /**
         * Add the controllers to the main screen
         */
        screen.getContentPane().add(controller);

        controller.setBounds(30,270,910,90);
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
        timeModel.notifyViews();

        running = true;

        while(true){
            if (running) {
                carParkModel.tick();
            }
            try{
                Thread.sleep(simulationSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void resetSimulation(){
        carParkModel.resetPark();
        statisticsView.resetStats();
    }
}
