import javax.swing.*;
import java.awt.*;

/**
 * JFrame for the Simulator where the GUI will be rendered.
 * It also draws the parking places in floors and rows.
 */
public class SimulatorView extends JFrame {
    private CarParkView carParkView;
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private Car[][][] cars;

    /**
     * Constructor for SimulatorView
     *
     * @param numberOfFloors int with the amount of floors to draw
     * @param numberOfRows   int with the amount of rows per floor to draw
     * @param numberOfPlaces int with the amount of places per row to draw
     */
    public SimulatorView(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];

        carParkView = new CarParkView();

        Container contentPane = getContentPane();
        //contentPane.add(stepLabel, BorderLayout.NORTH);
        contentPane.add(carParkView, BorderLayout.CENTER);
        //contentPane.add(population, BorderLayout.SOUTH);
        pack();
        setVisible(true);

        updateView();
    }

    /**
     * Update the view of the car park.
     */
    public void updateView() {
        carParkView.updateView();
    }

    /**
     * Get the amount of floors in the car park
     *
     * @return numberOfFloors of the car park
     */
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * Get the amount of rows in the car park
     *
     * @return numberOfRows of the car park
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Get the amount of places in the car park
     *
     * @return numberOfPlaces of the car park
     */
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * Get a car from a certain location in the car park.
     *
     * @param location Location object where to get the car from.
     * @return car object that is located at the given location.
     */
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * Set a car in a certain location in the car park.
     *
     * @param location Location object where to put the car.
     * @param car Car object
     * @return boolean whether car is successfully placed or not
     */
    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            return true;
        }
        return false;
    }

    /**
     * Remove a car at a certain location in the car park.
     *
     * @param location Location object where to remove the car
     * @return null | Car object when successfully removed, null if it failed
     */
    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        return car;
    }

    /**
     * Gets the first free parking place that is available in the car park.
     *
     * @return null | Location object when free place is found, otherwise null
     */
    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Get the first leaving car in the parking garage.
     *
     * @return null | Car object when a leaving car is found, otherwise null
     */
    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Tick runs a tick of the simulation for all cars
     */
    public void tick() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    /**
     * Check if a location is valid in the car park.
     *
     * @param location Location object to check
     * @return boolean whether location is valid
     */
    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }

    /**
     * The Car Park View is a extension of JPanel and represents the visual
     * look of the car park. It draws the car park with all floors, rows
     * and parking places.
     */
    private class CarParkView extends JPanel {

        private Dimension size;
        private Image carParkImage;

        /**
         * Constructor for objects of class CarPark
         */
        public CarParkView() {
            size = new Dimension(0, 0);
        }

        /**
         * Overridden. Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize() {
            return new Dimension(800, 500);
        }

        /**
         * Overriden. The car park view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g) {
            if (carParkImage == null) {
                return;
            }

            Dimension currentSize = getSize();
            if (size.equals(currentSize)) {
                g.drawImage(carParkImage, 0, 0, null);
            } else {
                // Rescale the previous image.
                g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
            }
        }

        /**
         * Update the view of the car park.
         */
        public void updateView() {
            // Create a new car park image if the size has changed.
            if (!size.equals(getSize())) {
                size = getSize();
                carParkImage = createImage(size.width, size.height);
            }
            Graphics graphics = carParkImage.getGraphics();
            for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);

                        Color color = Color.white;
                        if(car != null){
                            if(car.getCustomer() instanceof PassHolder){
                                color = Color.blue;
                            }else {
                                color = Color.red;
                            }
                        }

                        drawPlace(graphics, location, color);
                    }
                }
            }
            repaint();
        }

        /**
         * Paint a place on this car park view in a given color.
         */
        private void drawPlace(Graphics graphics, Location location, Color color) {
            graphics.setColor(color);
            graphics.fillRect(
                    location.getFloor() * 260 + (1 + (int) Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                    60 + location.getPlace() * 10,
                    20 - 1,
                    10 - 1); // TODO use dynamic size or constants
        }
    }

}
