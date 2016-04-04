package nl.hanze.CarParkSimulation.logic;

import nl.hanze.CarParkSimulation.view.AbstractView;

import java.util.List;

/**
 * Class AbstractModel
 * This is the abstract model class that all models should extend.
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public class AbstractModel {
    // List of views that are linked to the model
    private List<AbstractView> views;

    /**
     * Constructor for AbstractModel that expects a list with views that belong to the model that is created.
     *
     * @param views List of views that belong to the model
     */
    public AbstractModel(List<AbstractView> views) {
        this.views = views;
    }
}
