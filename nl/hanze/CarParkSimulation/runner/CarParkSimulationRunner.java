package nl.hanze.CarParkSimulation.runner;

import nl.hanze.CarParkSimulation.main.CarParkSimulation;

/**
 * Class CarParkSimulationRunner
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public class CarParkSimulationRunner {
    // the main simulation
    public static final CarParkSimulation simulation = new CarParkSimulation();

    /**
     * Getter for simulator
     * @return the main simulation
     */
    public static CarParkSimulation getSimulation() {
        return simulation;
    }

    /**
     * Main method for executing the runner
     * @param args args for runner
     */
    public static void main(String[] args) {
        getSimulation();
    }
}
