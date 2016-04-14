package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.logic.CarPark;

import javax.swing.*;
import java.awt.*;

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
    private JLabel albert;
    private JLabel opera;

    public LegendView(CarPark model) {
        super(model);

        // set labels
        title = new JLabel();
        title.setFont(new Font("Serif", Font.PLAIN, 20));
        title.setText(Language.get("legTit"));



        regular = new JLabel(Language.get("legReg"));
        passholder = new JLabel(Language.get("legPas"));
        albert = new JLabel(Language.get("legAh"));
        opera = new JLabel(Language.get("legOp"));

        // add labels
        add(title);
        add(regular);
        add(passholder);
        add(albert);
        add(opera);

        // set bounds
        title.setBounds(10,5,150,50);
        regular.setBounds(10,40,150,50);
        passholder.setBounds(10,60,150,50);
        albert.setBounds(10,80,150,50);
        opera.setBounds(10,100,150,50);

        // set colors
        regular.setForeground(Color.RED);
        passholder.setForeground(Color.BLUE);
        albert.setForeground(Color.CYAN);
        opera.setForeground(Color.GREEN);
    }

    @Override
    public void updateView() {
        super.updateView();
    }

}
