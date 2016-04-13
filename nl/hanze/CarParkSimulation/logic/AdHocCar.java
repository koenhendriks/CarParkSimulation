package nl.hanze.CarParkSimulation.logic;

import nl.hanze.CarParkSimulation.interfaces.TimeInterface;

/**
 * Class AdHocCar
 * This class extends the car class and is meant for creating cars ad hoc.
 *
 * @author Ruben Buisman, Joey Boum Bletterman
 * @version 0.2 (11-04-2016)
 */
public class AdHocCar extends Car implements TimeInterface
{

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
}
