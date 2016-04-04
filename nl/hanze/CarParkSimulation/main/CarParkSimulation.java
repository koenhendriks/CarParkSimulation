package nl.hanze.CarParkSimulation.main;

import nl.hanze.CarParkSimulation.controller.AbstractController;
import nl.hanze.CarParkSimulation.controller.Controller;
import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarPark;
import nl.hanze.CarParkSimulation.view.AbstractView;

import javax.swing.*;

/**
 * Class CarParkSimulation
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public class CarParkSimulation {

    private AbstractModel carParkModel;
    private JFrame screen;
    private AbstractView carParkView;
    private AbstractController carParkController;

    public CarParkSimulation() {
        this.carParkModel = new CarPark();
        this.carParkController = new Controller(carParkModel);
        this.

    }
}
