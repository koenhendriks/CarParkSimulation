package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.logic.AbstractModel;

import java.awt.*;

/**
 * Class CarParkView
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public class CarParkView extends AbstractView{
    /**
     * Constructor of AbstractView that expects a model belonging to this view
     *
     * @param model AbstractModel that belongs to this view
     */
    public CarParkView(AbstractModel model) {
        super(model);
        setSize(new Dimension(800, 500));
    }

}
