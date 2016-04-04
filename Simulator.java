import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

/**
 * Class for simulating cars parking in a car park.
 *
 * @author (Modified by) Joey Boum Bletterman
 * @version 2.0
 */
public class Simulator implements ActionListener {

    // Queue object for entering cars.
    private CarQueue entranceCarQueue;

    // Queue object for paying cars.
    private CarQueue paymentCarQueue;

    // Queue object for exiting cars.
    private CarQueue exitCarQueue;

    // Instance for a graphic display of the simulation.
    private SimulatorView simulatorView;

    // Time intervals used by the methods in this class.
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

    /**
     * Constructor for the car park simulation.
     */
    public Simulator() {
        entranceCarQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        simulatorView = new SimulatorView(3, 6, 30, this);
    }

    /**
     * Method for running the simulation for a given duration.
     */
    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }

    /**
     * Method for executing the simulation per minute.
     */
    private void tick() {
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDayArrivals
                : weekendArrivals;

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
            int customerChance = random.nextInt(passHolderProbability);

            if(customerChance == 0){
                Customer passHolder = new PassHolder();
                Car car = new AdHocCar(passHolder);
                entranceCarQueue.addCar(car);
            }else{
                Customer customer = new Customer();
                Car car = new AdHocCar(customer);
                entranceCarQueue.addCar(car);
            }
        }

        // Remove car from the front of the queue and assign to a parking space.
        for (int i = 0; i < enterSpeed; i++) {
            Car car = entranceCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // Find a space for this car.
            Location freeLocation = simulatorView.getFirstFreeLocation();
            if (freeLocation != null) {
                simulatorView.setCarAt(freeLocation, car);
                int stayMinutes = (int) (15 + random.nextFloat() * 10 * 60);
                car.setMinutesLeft(stayMinutes);
            }
        }

        // Perform car park tick.
        simulatorView.tick();

        // Add leaving cars to the exit queue.
        while (true) {
            Car car = simulatorView.getFirstLeavingCar();
            if (car == null) {
                break;
            }

            /**
             * If the customer is an instance of the passholder
             * we can skip the payment and leave the car park
             * immediately by adding the car to the exit que.
             */
            if(car.getCustomer() instanceof PassHolder){
                simulatorView.removeCarAt(car.getLocation());
                exitCarQueue.addCar(car);
            }else{
                car.setIsPaying(true);
                paymentCarQueue.addCar(car);
            }
        }

        // Let cars pay.
        for (int i = 0; i < paymentSpeed; i++) {
            Car car = paymentCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // TODO Handle payment.
            simulatorView.removeCarAt(car.getLocation());
            exitCarQueue.addCar(car);
        }

        // Let cars leave.
        for (int i = 0; i < exitSpeed; i++) {
            Car car = exitCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // Bye!
        }

        // Update the car park view.
        simulatorView.updateView();

        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Main method for executing the simulator.
     * @param args for the main method
     */
    public static void main(String[] args) {
        Simulator sim1 = new Simulator();
        sim1.run();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        /**
         * Here we cast the source of the event to a JButton and check
         * the action command string to check what we need to do.
         */
        String command = ((JButton) actionEvent.getSource()).getActionCommand();

        if(command.equals("oneStep")){
            System.out.println("Step 1 time, button pressed!"); // Debug log
            this.tick();
        }else if(command.equals("manyStep")){
            System.out.println("Step 100 times, button pressed!"); // Debug log
            for(int i=0; i < 100; i++)
                this.tick();
        }
    }
}
