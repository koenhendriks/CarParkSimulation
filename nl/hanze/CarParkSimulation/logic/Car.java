package nl.hanze.CarParkSimulation.logic;

/**
 * Class Car
 *
 * @author Ruben Buisman
 * @version 0.1 (04-04-2016)
 */

/**
 * This is the Car class, it represents a car that enters, pays and leaves the car park.
 * It has a certain location in the parking car park and a certain amount of time that
 * it stays in the car park.
 */
public abstract class Car extends AbstractModel
{

    // location for the Car object
    private Location location;

    // minutes the car has left for parking
    private int minutesLeft;

    // minutes the car is staying in the carpark
    private int stayMinutes;

    // boolean indicating that the car is paying
    private boolean isPaying;

    // license for the car
    private String license;

    /**
     * Constructor for objects of class Car.
     */
    public Car() {
        License license = new License();
        this.license = license.generateLicenseNumber();
    }

    /**
     * Getter for the location of the Car object.
     *
     * @return Location of the Car object.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Set the location of a car in the parking car park.
     *
     * @param location Location of the car
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Get the amount of minutes the car is still going to be in the car park.
     *
     * @return int amount of minutes left
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * Set the amount of minutes the car is going to stay in the car park.
     *
     * @param minutesLeft int amount of minutes the car is going to stay.
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    /**
     * Check if the car is paying at the moment.
     *
     * @return boolean if the car is paying
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * Tell the car if it is paying.
     *
     * @param isPaying boolean whether the car is paying or not
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * Getter for the amount of minutes a car is staying in the car park.
     *
     * @return int The amount of minutes the car is staying.
     */
    public int getStayMinutes() {
        return stayMinutes;
    }

    /**
     * Set the amount of minutes a car stays in the car park.
     *
     * @param stayMinutes The amount of minutes a car is staying.
     */
    public void setStayMinutes(int stayMinutes) {
        this.stayMinutes = stayMinutes;
    }

    /**
     * Method for decreasing the minutes the Car object has left for parking.
     */
    public void tick() {
        minutesLeft--;
    }

}
