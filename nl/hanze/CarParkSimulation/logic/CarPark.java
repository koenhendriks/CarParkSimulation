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
}
