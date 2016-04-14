package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.logic.AbstractModel;

/**
 * Class QueueLengthView
 *
 * @author Ruben Buisman
 * @version 0.1 (14-04-2016)
 */
public class QueueLengthView extends AbstractView {


    /**
     * Constructor of AbstractView that expects a model belonging to this view.
     *
     * @param model AbstractModel that belongs to this view.
     */
    public QueueLengthView(AbstractModel model) {
        super(model);
    }


    /**
     * Gets called by the super model class when something needs to be updated.
     */
    public void updateView(){

    }

}
