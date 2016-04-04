package nl.hanze.CarParkSimulation.controller;

import nl.hanze.CarParkSimulation.logic.AbstractModel;

import javax.swing.*;

/**
 * Class AbstractController
 * This is the abstract controller class that all controllers should extend.
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public class AbstractController extends JPanel {

    // A controller should have a certain instance of the AbstractModel
    private AbstractModel model;

    /**
     * Constructor of AbstractController with a model belong to this controller
     * @param model AbstractModel that belongs to this controller
     */
    public AbstractController(AbstractModel model) {
        this.model = model;
    }
}
