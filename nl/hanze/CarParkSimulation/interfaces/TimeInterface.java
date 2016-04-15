package nl.hanze.CarParkSimulation.interfaces;

/**
 * Class TimeInterface
 *
 * @author Koen Hendriks
 * @version 0.1 (12-04-2016)
 */
public interface TimeInterface {

    /**
     * Method to include in the classes that implement this interface.
     * It gets called every minute.
     */
    void tick();
}
