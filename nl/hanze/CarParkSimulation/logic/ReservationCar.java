package nl.hanze.CarParkSimulation.logic;

import nl.hanze.CarParkSimulation.interfaces.TimeInterface;

/**
 * Class ReservationCar
 *
 * @author Koen Hendriks
 * @version 0.1 (13-04-2016)
 */
public class ReservationCar extends Car implements TimeInterface{

    private String company;

    /**
     * Constructor of the Reservation Car
     *
     * @param company String of the company the car belongs to
     */
    public ReservationCar(String company) {
        this.company = company;
    }

    /**
     * Method to interact with time passing in the
     * car park simulation
     */
    public void tick(){
        super.tick();
        if (super.getMinutesLeft() <= 0) {
            CarPark.removeCarAt(super.getLocation());
            CarPark.exitCar(this);
            CarPark.addReservationIndex();
        }
    }

    /**
     * Method to get the company from the reservation car
     *
     * @return String with the company
     */
    public String getCompany() {
        return company;
    }
}
