package nl.hanze.CarParkSimulation.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Class CarPark
 * This class can generate an instance of a car park.
 *
 * @author Koen Hendriks, Joey Boum Bletterman
 * @version 0.2 (11-04-2016)
 */
public final class CarPark extends AbstractModel implements TimeInterface{

    private static int numberOfFloors;
    private static int numberOfRows;
    private static int numberOfPlaces;

    // car queue types
    private static CarQueue entranceCarQueue;
    private static CarQueue paymentCarQueue;
    private static CarQueue exitCarQueue;

    // time object
    private Time time;
    // Number of arriving cars per hour.
    int weekDayArrivals= 50; // average number of arriving cars per hour
    int weekendArrivals = 90; // average number of arriving cars per hour

    // Intervals for entering, paying and exiting cars.
    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 10; // number of cars that can pay per minute
    int exitSpeed = 9; // number of cars that can leave per minute
    int passHolderProbability = 5; // this means one in five cars will be a passholder

    private static int entranceIndex = 0;
    private static int exitIndex = 0;
    private static int payCashIndex = 0;
    private static int payPassIndex = 0;
    private static int totalCarIndex = 0;
    private static int totalPassholderIndex = 0;
    private static int totalMinutes;
    private static int inMinutes;

    private static HashMap<Location, Car> carLocationMap;

    /**
     * Constructor of the CarPark model expecting the floors, rows and places.
     *
     * @param floors int with the amount of floors to draw
     * @param rows int with the amount of rows per floor to draw
     * @param places int with the amount of places per row to draw
     * @param time Time model that we use to keep track and progress the time
     */
    public CarPark(int floors, int rows, int places, Time time) {
        /**
         * The time object for the Car Park
         */
        this.time = time;

        /**
         * Create the park with the number of floors, rows and places
         * and create the queues available for the cars
         */
        numberOfFloors = floors;
        numberOfRows = rows;
        numberOfPlaces = places;
        entranceCarQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

        /**
         * Here we generate our hash map with all the locations
         * we only need to do this ones here which saves a lot
         * of resources in comparison if we do this every tick
         */
        carLocationMap = new HashMap<>();
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    carLocationMap.put(location,null);
                }
            }
        }
    }

    /**
     * Get a car from a certain location in the car park.
     *
     * @param location Location object where to get the car from.
     * @return car object that is located at the given location.
     */
    public static Car getCar(Location location) {
        if (!checkLocation(location)) {
            return null;
        }
        return carLocationMap.get(location);
    }

    /**
     * Park a car in a certain location in the car park.
     *
     * @param location Location object where to put the car.
     * @param car Car object.
     * @return boolean whether car is successfully placed or not.
     */
    public boolean parkCar(Location location, Car car) {
        if (!checkLocation(location)) {
            return false;
        }
        Car oldCar = getCar(location);
        if (oldCar == null) {
            carLocationMap.put(location,car);
            car.setLocation(location);
            return true;
        }
        return false;
    }

    /**
     * Method for running a simulation step.
     */
    public void tick() {
        time.tick();

        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = time.isWeekend()
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

            if (customerChance == 0){
                Car car = new PassHolder();
                entranceCarQueue.addCar(car);
                totalPassholderIndex++;
            }
            else {
                Car car = new AdHocCar();
                entranceCarQueue.addCar(car);
            }
            entranceIndex ++;
            totalCarIndex ++;

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
            if (FreeLocation.location != null) {

                int stayMinutes = (int) (15 + random.nextFloat() * 10 * 60);
                car.setStayMinutes(stayMinutes);

                /**
                 * Park the car and generate a new free location
                 * for the next car that comes in the car park.
                 */
                this.parkCar(FreeLocation.location, car);

                FreeLocation.setNext();
            }

            super.notifyViews();
        }

        this.tickCars();

        super.notifyViews();

        // Let cars pay.
        for (int i = 0; i < paymentSpeed; i++) {
            Car car = paymentCarQueue.removeCar();
            super.notifyViews();
            if (car == null) {
                break;
            }

            removeCarAt(car.getLocation());
            exitCarQueue.addCar(car);

            super.notifyViews();
        }

        // Let cars leave.
        for (int i = 0; i < exitSpeed; i++) {
            Car car = exitCarQueue.removeCar();

            if (car == null) {
                break;
            }

            if(car instanceof PassHolder){
                totalPassholderIndex--;
            }
            totalMinutes = (totalMinutes + car.getStayMinutes());
            totalCarIndex--;
            exitIndex++;
            super.notifyViews();
        }

        // Update the car park view.
        super.notifyViews();

    }

    /**
     * Loop trough the car park to get all cars and call the tick method.
     */
    private void tickCars() {
        inMinutes = 0;

        for (Car car : carLocationMap.values()) {
            if(car != null){
                car.tick();
                inMinutes =(inMinutes + car.getStayMinutes());
            }
        }
    }

    /**
     * Get number of floors.
     *
     * @return int number of floors in the car park.
     */
    public static int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * Get number of rows.
     *
     * @return int number of rows in the car park.
     */
    public static int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Get number of places.
     *
     * @return int number of places in the car park.
     */
    public static int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * Check if a location is valid in the car park.
     *
     * @param location Location object to check.
     * @return boolean whether location is valid.
     */
    public static boolean checkLocation(Location location) {

        if(location == null)
            return false;

        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        return !(floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces);
    }

    /**
     * Remove a car at a certain location in the car park.
     *
     * @param location Location object where to remove the car
     */
    public static void removeCarAt(Location location) {
        if (checkLocation(location)) {
            Car car = carLocationMap.get(location);
            car.setLocation(null);

            FreeLocation.checkRemovedLocation(location);

            carLocationMap.put(location, null);
        }
    }

    /**
     * Increase the amount of cars that have
     * payed and are leaving the car park.
     */
    public static void addCashIndex(){
        payCashIndex ++;
    }

    /**
     * Increase the amount of passholders that have
     * payed and are leaving the car park.
     */
    public static void addPassIndex(){
        payPassIndex ++;
    }

    /**
     * Let a car exit by adding it to the end
     * of the exit queue.
     *
     * @param car Car object that should be added to the exit queue
     */
    public static void payCar(Car car){
        paymentCarQueue.addCar(car);
    }

    /**
     * Let a car exit by adding it to the end
     * of the exit queue.
     *
     * @param car Car object that should be added to the exit queue
     */
    public static void exitCar(Car car){
        exitCarQueue.addCar(car);
    }

    /**
     * Getter for the entrance index.
     * @return int entrance index.
     */
    public int getEntranceIndex() {
        return entranceIndex;
    }

    /**
     * Getter for the exit index.
     * @return int exit index.
     */
    public int getExitIndex() {
        return exitIndex;
    }

    /**
     * Getter for the pay pass index.
     * @return int pay pass index
     */
    public int getPayPassIndex() {
        return payPassIndex;
    }

    /**
     * Getter for the pay cash index.
     * @return int pay cash index.
     */
    public int getPayCashIndex() {
        return payCashIndex;
    }

    /**
     * Getter for the total amount of minutes.
     * @return int total minutes
     */
    public int getTotalMinutes() {
        return totalMinutes;
    }

    /**
     * Getter for inMinutes.
     * @return int inMinutes.
     */
    public int getInMinutes() {
        return inMinutes;
    }

    /**
     * Getter for the total car index.
     * @return int total car.
     */
    public int getTotalCars() {
        int cars = 0;
        for (Car car : carLocationMap.values()) {
            if(car != null){

                cars++;

            }
        }
        return cars;
    }

    /**
     * Getter for the total pass holder index.
     * @return int total pass holder index.
     */
    public int getTotalPassholderIndex() {
        return totalPassholderIndex;
    }

    /**
     * Reset method for the park.
     */
    public void resetPark(){
        // reset time
        this.time.reset();
        totalMinutes = 0;
        inMinutes = 0;

        // reset indices
        entranceIndex = 0;
        exitIndex = 0;
        payCashIndex = 0;
        payPassIndex = 0;

        totalCarIndex = 0;
        totalPassholderIndex = 0;

        // reset park
        numberOfFloors = 3;
        numberOfRows = 6;
        numberOfPlaces = 30;

        entranceCarQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

        //reset locations
        for (Location location: carLocationMap.keySet()) {
            carLocationMap.put(location, null);
        }
        FreeLocation.location = new Location(0,0,0);

        //Update the views
        super.notifyViews();
    }

    public Time getTime() {
        return time;
    }

    public static void setTotalMinutes(int totalMinutes) {
        CarPark.totalMinutes = totalMinutes;
    }
}