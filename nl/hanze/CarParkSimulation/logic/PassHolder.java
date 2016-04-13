package nl.hanze.CarParkSimulation.logic;

import nl.hanze.CarParkSimulation.interfaces.TimeInterface;

/**
 * Class PassHolder
 *
 * This class can create instances of Pass Holders,
 * as opposed to regular customers.
 *
 * @author Ruben Buisman, Joey Boum Bletterman
 * @version 0.2 (11-04-2016)
 */
public class PassHolder extends Car implements TimeInterface
{

    /**
     * Method to interact with time passing in the
     * car park simulation
     */
    public void tick(){
        super.tick();
        if (super.getMinutesLeft() <= 0) {
            CarPark.removeCarAt(super.getLocation());
            CarPark.exitCar(this);
            CarPark.addPassIndex();
        }
    }
}
