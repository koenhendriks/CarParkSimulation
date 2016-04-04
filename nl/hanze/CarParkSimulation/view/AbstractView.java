package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.logic.AbstractModel;

/**
 * Class AbstractView
 * This is the abstract view class that all views should extend.
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public class AbstractView {
    // All views have a certain model
    protected AbstractModel model;

    /**
     * Constructor of AbstractView that expects a model belonging to this view
     *
     * @param model
     */
    public AbstractView(AbstractModel model) {
        this.model = model;
    }
}
