package nl.hanze.CarParkSimulation.view;

import nl.hanze.CarParkSimulation.logic.Car;
import nl.hanze.CarParkSimulation.logic.CarPark;
import nl.hanze.CarParkSimulation.logic.Location;

import java.awt.*;

/**
 * Class CarParkView
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public class CarParkView extends AbstractView{

    private Image carParkImage;
    private Dimension size;

    /**
     * Constructor of CarParkView that expects a model belonging to this view
     *
     * @param model AbstractModel that belongs to this view
     */
    public CarParkView(CarPark model) {
        super(model);
        this.size = new Dimension(680, 300);
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
        // Create a new car park image if the size has changed.
        carParkImage = createImage(size.width, size.height);

        Graphics graphics = carParkImage.getGraphics();

        CarPark carPark = (CarPark) super.model;

        for (int floor = 0; floor < carPark.getNumberOfFloors(); floor++) {
            for (int row = 0; row < carPark.getNumberOfRows(); row++) {
                for (int place = 0; place < carPark.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = carPark.getCar(location);

                    Color color = Color.white;
                    if(car != null)
                        color = Color.red;

                    drawPlace(graphics, location, color);
                }
            }
        }

        super.updateView();
    }

    /**
     * Paint a place on this car park view in a given color.
     */
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                (location.getFloor() * 260 + (1 + (int) Math.floor(location.getRow() * 0.5)) * 60 + (location.getRow() % 2) * 20) -59,
//                (location.getFloor() * (20+this.floorOffset)) + ((location.getRow() * (20 + (((location.getRow() % 2) *20))  ))) ,
                location.getPlace() * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }
}
