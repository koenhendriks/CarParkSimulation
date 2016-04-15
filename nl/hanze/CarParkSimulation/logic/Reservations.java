package nl.hanze.CarParkSimulation.logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Reservations
 *
 * @author Koen Hendriks
 * @version 0.1 (13-04-2016)
 */
public final class Reservations {

    private static HashMap<String, ArrayList<Location>> reservations;
    private static HashMap<String, Color> colors;

    /**
     * Constructor for the Reservation which holds the hashmaps
     * of the reservations with their color
     */
    public Reservations() {
        reservations = new HashMap<>();
        colors = new HashMap<>();
    }

    /**
     * Method to add a reservation for a company.
     *
     * @param company String with the unique name of the company that makes the reservation
     * @param locations ArrayList with locations that the company has reserved
     */
    public void addReservation(String company, ArrayList<Location> locations ){
        reservations.put(company,locations);
    }

    /**
     * Method to set a color for a company reservation car
     *
     * @param company String of the company to set the color
     * @param color Color to give to the company cars
     */
    public void setColor(String company, Color color){
        colors.put(company, color);
    }

    /**
     * Method to get a color for a car from a company
     *
     * @param company String of the company to get the color from
     * @return Color the car should get
     */
    public Color getColor(String company){
        return colors.get(company);
    }

    /**
     * Method to get all the locations that have been reserved
     *
     * @return ArrayList with Location objects which have been reserved for different companies
     */
    public ArrayList<Location> getLocations(){
        ArrayList<Location> allLocations = new ArrayList<>();

        for (ArrayList<Location> locations : reservations.values()) {
            allLocations.addAll(locations);
        }

        return allLocations;
    }

    /**
     * Method to get list of locatinos for a certain company
     *
     * @param company String with the company to get the locatiosn from
     * @return ArrayList with Location objects which have been reserved by this company
     */
    public ArrayList<Location> getCompanyLocations(String company){
        return reservations.get(company);
    }

    /**
     * Method to get the reservation hashmap
     *
     * @return HashMap with String company as key and ArrayList with Location objects as value
     */
    public HashMap<String, ArrayList<Location>> getReservations() {
        return reservations;
    }
}




