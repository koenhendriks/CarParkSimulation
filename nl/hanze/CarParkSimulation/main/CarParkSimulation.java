package nl.hanze.CarParkSimulation.main;

import nl.hanze.CarParkSimulation.controller.AbstractController;
import nl.hanze.CarParkSimulation.controller.Controller;
import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarPark;
import nl.hanze.CarParkSimulation.logic.CarQueue;
import nl.hanze.CarParkSimulation.logic.Time;
import nl.hanze.CarParkSimulation.view.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class CarParkSimulation
 *
 * This will be an instance of the main simulation.
 *
 * @author Koen Hendriks, Ruben Buisman, Joey Boum Bletterman
 * @version 0.4 (11-04-2016)
 */
public final class CarParkSimulation
{
    // the main specifications
    public static final JFrame SCREEN = new JFrame(Language.get("title"));
    private static CarPark carParkModel;
    private static Time timeModel;
    private int width;
    private int height;

    // the simulations speed means the amount of milliseconds
    // it takes to simulate 1 minute in the car park.
    public static int simulationSpeed = 1000;

    // state of the simulation
    public static boolean running;

    // the menu
    public static final JMenuBar menubar = new JMenuBar();
    public static final JMenu fileMenu = new JMenu(Language.get("fileMenu"));
    public static final JMenu helpMenu = new JMenu(Language.get("helpMenu"));
    public static final JMenuItem resetItem = new JMenuItem(Language.get("resetItem"));
    public static final JMenuItem exitItem = new JMenuItem(Language.get("exitItem"));
    public static final JMenuItem aboutItem = new JMenuItem(Language.get("aboutItem"));

    // the views
    private AbstractView carParkView;
    private AbstractView queueView;
    private AbstractView queueLengthView;
    private AbstractView dayView;
    private AbstractView gridView;
    private AbstractView alternateStatiscticsView;
    private AbstractView legend;
    private static GraphView graphView;
    private static StatisticsView statisticsView;

    // the controller
    private AbstractController controller;

    /*
     * Constructor for the simulation.
     */
    public CarParkSimulation() {
        //Set the dimensions for the application
        this.width = 1200;
        this.height = 750;

        /*
         * Create the model, view and controller that
         * we need for the Car Park Simulation
         */
        timeModel = new Time();
        carParkModel = new CarPark(3, 6, 30,timeModel);

        this.carParkView = new CarParkView(carParkModel);
        this.queueView = new QueueView(carParkModel);
        this.queueLengthView = new QueueLengthView(carParkModel);
        this.dayView = new DayView(timeModel);
        statisticsView = new StatisticsView(carParkModel);
        this.alternateStatiscticsView = new AlternateStatisticsView(carParkModel);
        graphView = new GraphView(carParkModel);
        this.legend = new LegendView(carParkModel);

        this.controller = new Controller(carParkModel);

        /*
         * Create the JFrame that will display the views
         * and add these views to this JFrame
         */
        SCREEN.setSize(this.width, this.height);
        SCREEN.setLayout(null);

        // add the menu bar
        SCREEN.add(menubar);

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

        // add the views to the main SCREEN
        SCREEN.getContentPane().add(carParkView);
        SCREEN.getContentPane().add(dayView);
        SCREEN.getContentPane().add(queueView);
        SCREEN.getContentPane().add(queueLengthView);
        SCREEN.getContentPane().add(statisticsView);
        SCREEN.getContentPane().add(alternateStatiscticsView);
        SCREEN.getContentPane().add(legend);
        SCREEN.getContentPane().add(graphView);

        // set the location of the views on the SCREEN
        carParkView.setBounds(260,30,680,330);
        statisticsView.setBounds(30,190, 220,120);
        queueView.setBounds(30,30,220,160);
        queueLengthView.setBounds(30,420,220,120);
        dayView.setBounds(970,30,200, 330);
        alternateStatiscticsView.setBounds(260,400,680,140);
        graphView.setBounds(0,540,1200,300);
        legend.setBounds(970,370,200,170);


        // add the controllers to the main SCREEN
        SCREEN.getContentPane().add(controller);
        controller.setBounds(30,320,910,90);

        /*
         * Add a window listener to the SimulatorView so we can send
         * a confirmation to the user so we know they are sure if
         * they want to close the Car Park Simulation.
         */
        SCREEN.addWindowListener(new WindowAdapter() {
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

        /*
         * We only want to exit the application if the user has
         * confirmed our message, otherwise we don't want to
         * do anything, so we change the default close.
         */
        SCREEN.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // show the main SCREEN, disable resizing and notify the views to update.
        SCREEN.setVisible(true);
        SCREEN.setResizable(false);

        // initialize views
        carParkView.updateView();
        timeModel.notifyViews();

        running = true;

        while(true){
            if (running) {
                carParkModel.tick();
            }
            try {
                Thread.sleep(simulationSpeed);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method for resetting the entire simulation.
     */
    public static void resetSimulation(){
        statisticsView.resetStats();
        timeModel.resetTime();
        graphView.reset();
        carParkModel.resetPark();
    }
}
