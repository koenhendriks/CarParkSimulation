package nl.hanze.CarParkSimulation.logic;


import nl.hanze.CarParkSimulation.view.AbstractView;

import java.util.List;

/**
 * Class CarPark
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public class CarPark extends AbstractModel{

    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private Car[][][] cars;

    /**
     * Constructor of the CarPark model expecting the floors, rows and places.
     *
     * @param numberOfFloors int with the amount of floors to draw
     * @param numberOfRows   int with the amount of rows per floor to draw
     * @param numberOfPlaces int with the amount of places per row to draw
     */
    public CarPark(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;

        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
    }

    /**
     * Get a car from a certain location in the car park.
     *
     * @param location Location object where to get the car from.
     * @return car object that is located at the given location.
     */
    public Car getCar(Location location) {
        if (!checkLocation(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * Get number of floors
     *
     * @return int number of floors in the car park
     */
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * Get number of rows
     *
     * @return int number of rows in the car park
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Get number of places
     *
     * @return int number of places in the car park
     */
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * Check if a location is valid in the car park.
     *
     * @param location Location object to check
     * @return boolean whether location is valid
     */
    private boolean checkLocation(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        return !(floor < 0 || floor >= getNumberOfFloors() || row < 0 || row > getNumberOfRows() || place < 0 || place > getNumberOfPlaces());
    }
}
