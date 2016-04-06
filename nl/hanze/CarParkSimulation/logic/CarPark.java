package nl.hanze.CarParkSimulation.logic;


import nl.hanze.CarParkSimulation.view.AbstractView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class CarPark
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public class CarPark extends AbstractModel{

    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;

    private CarQueue entranceCarQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;

    // Number of arriving cars per hour.
    int weekDayArrivals= 50; // average number of arriving cars per hour
    int weekendArrivals = 90; // average number of arriving cars per hour

    // Intervals for entering, paying and exiting cars.
    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 10; // number of cars that can pay per minute
    int exitSpeed = 9; // number of cars that can leave per minute
    int passHolderProbability = 5; // this means one in five cars will be a passholder

    private Car[][][] cars;

    /**
     * Constructor of the CarPark model expecting the floors, rows and places.
     *
     * @param numberOfFloors int with the amount of floors to draw
     * @param numberOfRows   int with the amount of rows per floor to draw
     * @param numberOfPlaces int with the amount of places per row to draw
     */
    public CarPark(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;

        this.entranceCarQueue = new CarQueue();
        this.paymentCarQueue = new CarQueue();
        this.exitCarQueue = new CarQueue();

        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
    }

    /**
     * Get a car from a certain location in the car park.
     *
     * @param location Location object where to get the car from.
     * @return car object that is located at the given location.
     */
    public Car getCar(Location location) {
        if (!checkLocation(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * Park a car in a certain location in the car park.
     *
     * @param location Location object where to put the car.
     * @param car Car object
     * @return boolean whether car is successfully placed or not
     */
    public boolean parkCar(Location location, Car car) {
        if (!this.checkLocation(location)) {
            return false;
        }
        Car oldCar = this.getCar(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            return true;
        }
        return false;
    }

    public void tick() {
        // Advance the time by one minute.
        this.minute++;
        while (this.minute > 59) {
            this.minute -= 60;
            this.hour++;
        }
        while (this.hour > 23) {
            this.hour -= 24;
            this.day++;
        }
        while (this.day > 6) {
            this.day -= 7;
        }

        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = this.day < 5
                ? this.weekDayArrivals
                : this.weekendArrivals;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.1;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfCarsPerMinute = (int)Math.round(numberOfCarsPerHour / 60);

        // Add the cars to the back of the queue.
        for (int i = 0; i < numberOfCarsPerMinute; i++) {

            /**
             * Here we create the customers where the probability
             * is used to check if we create a passholder or a
             * regular customer.
             */
            int customerChance = random.nextInt(this.passHolderProbability);

            if(customerChance == 0){
                Car car = new PassHolder();
                this.entranceCarQueue.addCar(car);
            }else{
                Car car = new AdHocCar();
                this.entranceCarQueue.addCar(car);
            }

            super.notifyViews();
        }

        // Remove car from the front of the queue and assign to a parking space.
        for (int i = 0; i < enterSpeed; i++) {
            Car car = entranceCarQueue.removeCar();

            super.notifyViews();

            if (car == null) {
                break;
            }
            // Find a space for this car.
            Location freeLocation = this.getFirstFreeLocation();
            if (freeLocation != null) {
                this.parkCar(freeLocation, car);
                int stayMinutes = (int) (15 + random.nextFloat() * 10 * 60);
                car.setMinutesLeft(stayMinutes);
            }

            super.notifyViews();
        }

        this.tickCars();

        // Add leaving cars to the exit queue.
        while (true) {
            Car car = this.getFirstLeavingCar();
            if (car == null) {
                break;
            }

            /**
             * If the customer is an instance of the passholder
             * we can skip the payment and leave the car park
             * immediately by adding the car to the exit que.
             */
            if(car instanceof PassHolder){
                this.removeCarAt(car.getLocation());
                exitCarQueue.addCar(car);
            }else{
                car.setIsPaying(true);
                paymentCarQueue.addCar(car);
            }

            super.notifyViews();
        }

        // Let cars pay.
        for (int i = 0; i < paymentSpeed; i++) {
            Car car = paymentCarQueue.removeCar();
            super.notifyViews();
            if (car == null) {
                break;
            }
            // TODO Handle payment.
            this.removeCarAt(car.getLocation());
            super.notifyViews();
            exitCarQueue.addCar(car);
            super.notifyViews();
        }

        // Let cars leave.
        for (int i = 0; i < exitSpeed; i++) {
            Car car = exitCarQueue.removeCar();
            if (car == null) {
                break;
            }
            super.notifyViews();
        }

        // Update the car park view.
        super.notifyViews();

        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Loop trough the car park to get all cars and call the tick method
     */
    private void tickCars() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = this.getCar(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    /**
     * Counts the cars that are in the car park.
     *
     *
     * @return ArrayList with the first integer the amount of cars, the second the amount of passholders
     */
    public ArrayList<Integer> totalCars(){
        int totalCars = 0;
        int passholderCars = 0;

        ArrayList<Integer> totals = new ArrayList<>();

        for (int floor = 0; floor < this.getNumberOfFloors(); floor++) {
            for (int row = 0; row < this.getNumberOfRows(); row++) {
                for (int place = 0; place < this.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = this.getCar(location);
                    if(car instanceof PassHolder){
                        passholderCars++;
                        totalCars++;
                    }else if(car !=null){
                        totalCars++;
                    }
                }
            }
        }

        totals.add(totalCars);
        totals.add(passholderCars);

        return totals;
    }

    /**
     * Get number of floors
     *
     * @return int number of floors in the car park
     */
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * Get number of rows
     *
     * @return int number of rows in the car park
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Get number of places
     *
     * @return int number of places in the car park
     */
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * Check if a location is valid in the car park.
     *
     * @param location Location object to check
     * @return boolean whether location is valid
     */
    public boolean checkLocation(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        return !(floor < 0 || floor >= getNumberOfFloors() || row < 0 || row > getNumberOfRows() || place < 0 || place > getNumberOfPlaces());
    }

    /**
     * Gets the first free parking place that is available in the car park.
     *
     * @return null | Location object when free place is found, otherwise null
     */
    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < this.getNumberOfFloors(); floor++) {
            for (int row = 0; row < this.getNumberOfRows(); row++) {
                for (int place = 0; place < this.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (this.getCar(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Get the first leaving car in the parking garage.
     *
     * @return null | Car object when a leaving car is found, otherwise null
     */
    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = this.getCar(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Remove a car at a certain location in the car park.
     *
     * @param location Location object where to remove the car
     * @return null | Car object when successfully removed, null if it failed
     */
    public Car removeCarAt(Location location) {
        if (!this.checkLocation(location)) {
            return null;
        }
        Car car = getCar(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        return car;
    }

    public Car[][][] getCars() {
        return cars;
    }

    public CarQueue getEntranceCarQueue() {
        return entranceCarQueue;
    }

    public CarQueue getPaymentCarQueue() {
        return paymentCarQueue;
    }

    public CarQueue getExitCarQueue() {
        return exitCarQueue;
    }


}
