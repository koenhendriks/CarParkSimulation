package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.logic.AbstractModel;

import javax.swing.*;

/**
 * Class for displaying statistics of the simulation.
 * @author Joey Boum Bletterman
 * @version 0.1 (5/4/2016)
 */
public class StatisticsView extends AbstractView {

    private int parked;
    private JTextField revenue;

    /**
     * Constructor of AbstractView that expects a model belonging to this view
     * @param model AbstractModel that belongs to this view
     */
    public StatisticsView(AbstractModel model) {
        super(model);
        revenue = new JTextField(parked);
    }

    public int checkParked(){
        return 0;
    }

    @Override
    public void updateView() {
        super.updateView();
    }
}
