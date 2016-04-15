package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.controller.Controller;
import nl.hanze.CarParkSimulation.localization.en.Language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Class AboutView
 *
 * @author Koen Hendriks
 * @version 0.1 (13-04-2016)
 */
public class AboutView extends JPanel {

    private JLabel title;
    private JLabel version;
    private JLabel name1;
    private JLabel name2;
    private JLabel name3;
    private JLabel name4;
    private JLabel name5;
    private JLabel github;
    private JButton close;
    private ImageIcon githubLogo;
    private JLabel githubLabel;


    /**
     * Constructor for the About view which shows some information about the application
     */
    public AboutView() {
        // using absolute positioning so we set the layout to null
        setLayout(null);

        // construct the components
        title = new JLabel(Language.get("aboutMessage"),SwingConstants.CENTER);
        version = new JLabel(Language.get("version"),SwingConstants.CENTER);
        name1 = new JLabel(Language.get("nameLine1"));
        name2 = new JLabel(Language.get("nameLine2"));
        name3 = new JLabel(Language.get("nameLine3"));
        name4 = new JLabel(Language.get("nameLine4"));
        name5 = new JLabel(Language.get("nameLine5"));
        github = new JLabel(Language.get("github"),SwingConstants.CENTER);
        close = new JButton(Language.get("close"));
        githubLogo = new ImageIcon(getClass().getResource("/nl/hanze/CarParkSimulation/resources/images/github/GitHub-Mark-64px.png"));
        githubLabel = new JLabel(githubLogo, SwingConstants.CENTER);

        // set font for title label
        title.setFont(new Font("Serif", Font.PLAIN, 20));

        // set location and size of components
        title.setBounds(0,30,500,20);
        version.setBounds(0,25,500,70);
        name1.setBounds(125,100,500,20);
        name2.setBounds(155,130,500,20);
        name3.setBounds(155,150,500,20);
        name4.setBounds(155,170,500,20);
        name5.setBounds(155,190,500,20);
        github.setBounds(0,330,500,20);
        githubLabel.setBounds(0,250,500,65);
        close.setBounds(200,400,100,20);

        // add the components to the view
        add(title);
        add(version);
        add(name1);
        add(name2);
        add(name3);
        add(name4);
        add(name5);
        add(github);
        add(githubLabel);
        add(close);

        // set url links on github labels
        githubLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        githubLabel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                try {
                    openWebpage(new URI("https://github.com/koenhendriks/CarParkSimulation"));
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });

        github.setCursor(new Cursor(Cursor.HAND_CURSOR));
        github.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                try {
                    openWebpage(new URI("https://github.com/koenhendriks/CarParkSimulation"));
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // create listener for close button
        close.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                Controller.closeAboutDialog();
            }
        } );

    }

    /**
     * Method to open a URL external in a browser
     *
     * @param uri URI that should be opened
     */
    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to open a URL external in a browser
     *
     * @param url URI that should be opened
     */
    public static void openWebpage(URL url) {
        try {
            openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
