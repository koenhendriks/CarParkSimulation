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

    public ReservationCar(String company) {
        this.company = company;
    }

    /**
     * Method to interact with time passing in the
     * car park simulation
     */
    public void tick(){
        super.tick();
        if (super.getMinutesLeft() <= 0 && !super.getIsPaying()) {
            super.setIsPaying(true);
            CarPark.payCar(this);
            CarPark.addCashIndex();
        }
    }

    public String getCompany() {
        return company;
    }
}
