package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.localization.en.Language;
import nl.hanze.CarParkSimulation.logic.Car;
import nl.hanze.CarParkSimulation.logic.CarPark;
import nl.hanze.CarParkSimulation.logic.Location;
import nl.hanze.CarParkSimulation.logic.PassHolder;

import javax.swing.*;
import java.awt.*;

/**
 * Class CarParkView
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public class CarParkView extends AbstractView{

    private JLabel title;
    private JLabel totalCars;
    private JLabel totalPassholders;
    private Image carParkImage;
    private Dimension size;

    /**
     * Constructor of CarParkView that expects a model belonging to this view
     *
     * @param model AbstractModel that belongs to this view
     */
    public CarParkView(CarPark model) {
        super(model);
        this.title = new JLabel(Language.get("birdView"));
        this.totalCars = new JLabel(Language.get("cars0"));
        this.totalPassholders = new JLabel(Language.get("pass0"));
        this.size = new Dimension(680, 330);

        /**
         * Set location of the labels
         */
        totalPassholders.setBounds(500,5,250,10);
        totalCars.setBounds(270,5,150,10);
        title.setBounds(10,5, 150,10);

        /**
         * Add the labels to the view
         */
        add(title);
        add(totalCars);
        add(totalPassholders);
    }

    @Override
    /**
     * The car park view component needs to be redisplayed. Copy the
     * internal image to screen.
     */
    public void paintComponent(Graphics g) {
        if (carParkImage == null) {
            return;
        }

        g.drawImage(carParkImage, 0, 0, null);
    }

    /**
     * Called by the model that belongs to this view
     * to notify that the view should by updated
     */
    public void updateView() {

        CarPark carPark = (CarPark) super.model;

        totalCars.setText(Language.get("cars")+ carPark.getTotalCars());
        totalPassholders.setText(Language.get("pass")+carPark.getTotalPassholderIndex());

        // Create a new car park image if the size has changed.
        carParkImage = createImage(size.width, size.height);

        Graphics graphics = carParkImage.getGraphics();


        for (int floor = 0; floor < carPark.getNumberOfFloors(); floor++) {
            for (int row = 0; row < carPark.getNumberOfRows(); row++) {
                for (int place = 0; place < carPark.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = carPark.getCar(location);
                    Color color;

                    if(car instanceof PassHolder){
                        color = Color.blue;
                    } else if(car != null){
                        color = Color.red;
                    } else {
                        color = Color.white;
                    }

                    drawPlace(graphics, location, color);
                }
            }
        }

        setVisible(true);
        super.updateView();
    }

    /**
     * Paint a place on this car park view in a given color.
     */
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                (location.getFloor() * 260 + (1 + (int) Math.floor(location.getRow() * 0.5)) * 60 + (location.getRow() % 2) * 20) -59,
                location.getPlace() * 10 + 30,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }
}
