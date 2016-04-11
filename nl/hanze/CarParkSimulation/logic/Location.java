package nl.hanze.CarParkSimulation.logic;

/**
 * Class Location
 *
 * This class represents a location in the car park which
 * is on a certain floor in a certain row in a place.
 *
 * @author Ruben Buisman, Joey Boum Bletterman
 * @version 0.2 (11-04-2016)
 */
public class Location extends AbstractModel
{
    private int floor;
    private int row;
    private int place;

    /**
     * Constructor for objects of class Location.
     */
    public Location(int floor, int row, int place) {
        this.floor = floor;
        this.row = row;
        this.place = place;
    }

    /**
     * Implement content equality.
     *
     * @return boolean true or false.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Location) {
            Location other = (Location) obj;
            return floor == other.getFloor() && row == other.getRow() && place == other.getPlace();
        }
        else {
            return false;
        }
    }

    /**
     * Return a string of the form floor,row,place.
     *
     * @return A string representation of the location.
     */
    @Override
    public String toString() {
        return floor + "," + row + "," + place;
    }

    /**
     * Use the 10 bits for each of the floor, row and place
     * values. Except for very big car parks, this should give
     * a unique hash code for each (floor, row, place) tupel.
     *
     * @return A hashcode for the location.
     */
    @Override
    public int hashCode() {
        return (floor << 20) + (row << 10) + place;
    }

    /**
     * Getter for floor.
     *
     * @return The floor.
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Getter for row.
     *
     * @return The row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter for place.
     *
     * @return The place.
     */
    public int getPlace() {
        return place;
    }
}
