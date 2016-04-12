package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.logic.AbstractModel;
import nl.hanze.CarParkSimulation.logic.CarPark;

import javax.swing.*;
import java.awt.*;

/**
 * AlternateStatisticsView class
 * This is a view for displaying some of the charts or graphs.
 *
 * @author Joey Boum Bletterman
 * @version 0.1 (11-4-2016)
 */
public class AlternateStatisticsView extends AbstractView
{
    CarPark carPark = (CarPark) super.model;

    private int totalCars;
    private int passholders;
    private int regular;
    private int percent1;
    private int percent2;

    private JLabel progress;
    private JProgressBar bar1;
    private JProgressBar bar2;

    public static final int SPACES = 540;
    /**
     * Constructor of AbstractView that expects a model belonging to this view.
     *
     * @param model AbstractModel that belongs to this view.
     */
    public AlternateStatisticsView(AbstractModel model) {
        super(model);

        // label
        progress = new JLabel(Language.get("bar"));
        progress.setBounds(0,0,680,20);
        add(progress);

        // bar 1: regular
        bar1 = new JProgressBar();
        bar1.setBounds(0, 30, 680, 30);
        bar1.setBorderPainted(true);
        bar1.setStringPainted(true);
        add(bar1);

        // bar 2: pass holders
        bar2 = new JProgressBar();
        bar2.setBounds(0, 70, 680, 30);
        bar2.setBorderPainted(true);
        bar2.setStringPainted(true);
        add(bar2);
    }

    @Override
    public void updateView() {
        CarPark carPark = (CarPark) super.model;

        // indices
        totalCars = carPark.getTotalCars();
        passholders = carPark.getTotalPassholderIndex();
        regular = totalCars - passholders;

        bar1.setMaximum(SPACES);
        bar1.setValue(regular);
        percent1 = (int) Math.floor(bar1.getPercentComplete() * 100);
        System.out.println(""+percent1);
        bar1.setString(Language.get("regbar") + percent1 + "%");

        bar2.setMaximum(SPACES);
        bar2.setValue(passholders);
        percent2 = (int) Math.floor(bar2.getPercentComplete() * 100);
        bar2.setString(Language.get("pasbar") + percent2 + "%");

        setVisible(true);
        super.updateView();
    }
}
