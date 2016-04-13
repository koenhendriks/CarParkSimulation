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

    public Reservations() {
        reservations = new HashMap<>();
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

    public ArrayList<Location> getCompanyLocations(String company){
        return reservations.get(company);
    }

    public HashMap<String, ArrayList<Location>> getReservations() {
        return reservations;
    }
}




