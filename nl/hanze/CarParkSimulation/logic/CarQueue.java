package nl.hanze.CarParkSimulation.logic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class CarQueue
 *
 * @author Ruben Buisman
 * @version 0.1 (05-04-2016)
 */

/**
 * Class for creating different Queue collections of Car objects.
 * The class is used for determining cars that need to enter the
 * car park, need to pay their fee or want to exit the car park.
 */

public class CarQueue extends AbstractModel {

    // Queue collection Car objects.
    private Queue<Car> queue = new LinkedList<>();

    /**
     * Method for checking whether a car has been added to the Queue.
     * @param car added to the Queue
     * @return true or false
     */
    public boolean addCar(Car car) {
        return queue.add(car);
    }

    /**
     * Method for obtaining a Car object from a Queue.
     * @return Car object from the Queue
     */
    public Car removeCar() {
        return queue.poll();
    }

    public int countCars(){
            return queue.size();
    }

}
