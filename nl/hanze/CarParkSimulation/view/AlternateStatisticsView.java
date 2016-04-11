package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarPark;

import javax.swing.*;

/**
 * AlternateStatisticsView class
 * This is a view for displaying some of the charts or graphs.
 *
 * @author Joey Boum Bletterman
 * @version 0.1 (11-4-2016)
 */
public class AlternateStatisticsView extends AbstractView
{
    private int totalCars;
    private int passholders;
    private int regular;

    private JLabel bar1;
    private JLabel bar2;
    /**
     * Constructor of AbstractView that expects a model belonging to this view.
     *
     * @param model AbstractModel that belongs to this view.
     */
    public AlternateStatisticsView(AbstractModel model) {
        super(model);

        // indices
        totalCars = CarPark.totalCarIndex;
        passholders = CarPark.totalPassholderIndex;
        regular = totalCars - passholders;

        // bar 1
        bar1 = new JLabel("bar1");
        bar1.setBounds(0,0,680,30);
        add(bar1);

        // bar 2
        bar2 = new JLabel("bar2");
        bar2.setBounds(0,40,680,30);
        add(bar2);
    }

    @Override
    public void updateView() {
        super.updateView();
    }
}
