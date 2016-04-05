package nl.hanze.CarParkSimulation.logic;

import nl.hanze.CarParkSimulation.view.AbstractView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class AbstractModel
 * This is the abstract model class that all models should extend.
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public abstract class AbstractModel {
    // List of views that are linked to the model
    private List<AbstractView> views;

    /**
     * Constructor for AbstractModel that creates a list with views that belong to the model that is created.
     */
    public AbstractModel() {
        this.views = new ArrayList<AbstractView>();
    }

    /**
     * Method to add a view to the model
     *
     * @param view AbstractView that belongs to the model
     */
    public void addView(AbstractView view) {
        this.views.add(view);
    }
}
