package nl.hanze.CarParkSimulation.controller;

import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.main.CarParkSimulation;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * Class for the JOptionPane from the "About..." item.
 *
 * @author Joey Boum Bletterman
 * @version 0.1 (13-04-2016)
 */
public class AboutOptionPane extends JOptionPane implements HyperlinkListener
{
    private String title;
    private String version;
    private String authors;

    public AboutOptionPane(){
        title = Language.get("aboutMessage");
        version = Language.get("version");
        authors = Language.get("names");
        this.showMessageDialog(CarParkSimulation.SCREEN,
                version + "\n" + authors
                ,title
                ,JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {

    }
}
