package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.logic.CarPark;

import javax.swing.*;

/**
 * Class for viewing a legend.
 * This view can clarify the objects
 * on screen for the user.
 *
 * @author Joey Boum Bletterman
 * @version 0.1 (14-04-2016)
 */
public class LegendView extends AbstractView
{
    private JLabel title;
    private JLabel regular;
    private JLabel passholder;
    private JLabel reserved;
    private JLabel used;

    public LegendView(CarPark model) {
        super(model);

        title = new JLabel(Language.get("legTit"));
        regular = new JLabel(Language.get("legReg"));
        passholder = new JLabel(Language.get("legPas"));
        reserved = new JLabel(Language.get("legRes"));
        used = new JLabel(Language.get("legUse"));

        add(title);
        add(regular);
        add(passholder);
        add(reserved);
        add(used);

        title.setBounds(0,0,150,50);
        regular.setBounds(0,50,150,50);
        passholder.setBounds(0,70,150,50);
        reserved.setBounds(0,90,150,50);
        used.setBounds(0,110,150,50);
    }
}
