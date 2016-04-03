/**
 * This is the Car class, it represents a car that enters, pays and leaves the car park.
 * It has a certain location in the parking car park and a certain amount of time that
 * it stays in the car park.
 */
public abstract class Car {

    // Location for the Car object.
    private Location location;

    // Minutes the car has left for parking.
    private int minutesLeft;

    // Boolean indicating that the car is paying.
    private boolean isPaying;

    // Customer object that belongs to this car.
    private Customer customer;

    /**
     * Constructor for objects of class Car.
     */
    public Car() {

    }

    /**
     * Constructor for a Car with a customer.
     *
     * @param customer Customer that belongs to the car.
     */
    public Car(Customer customer){
        this.customer = customer;
    }

    /**
     * Getter for the location of the Car object.
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
     * Tel the car that it is paying.
     *
     * @param isPaying boolean whether the car is paying or not
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * Get the customer belonging to this car
     *
     * @return customer belonging to this car
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Set the customer belonging to this car.
     *
     * @param customer Customer that belongs to this car.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Method for decreasing the minutes the Car object has left for parking.
     */
    public void tick() {
        minutesLeft--;
    }

}