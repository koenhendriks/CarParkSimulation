public abstract class Car {

    // Location for the Car object.
    private Location location;

    // Minutes the car has left for parking.
    private int minutesLeft;

    // Boolean indicating that the car is paying.
    private boolean isPaying;

    /**
     * Constructor for objects of class Car.
     */
    public Car() {

    }

    /**
     * Getter for the location of the Car object.
     * @return Location of the Car object.
     */
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * Method for decreasing the minutes the Car object has left for parking.
     */
    public void tick() {
        minutesLeft--;
    }

}