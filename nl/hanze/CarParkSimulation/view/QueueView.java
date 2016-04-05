package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarQueue;

/**
 * Class QueueView
 *
 * @author Ruben Buisman
 * @version 0.1 (05-04-2016)
 */

public class QueueView extends AbstractView {


    /**
     * Constructor of AbstractView that expects a model belonging to this view
     *
     * @param model AbstractModel that belongs to this view
     */
    public QueueView(CarQueue model) {
        super(model);
    }

    public void updateView(){

    }

}
