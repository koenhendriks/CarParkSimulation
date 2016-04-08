package nl.hanze.CarParkSimulation.localization.en;

import java.util.HashMap;

/**
 * This class is used to retrieve strings that are shown in
 * the Front end of the project. If we want to change the
 * language or change a sentence we can do it here. The
 * HashMap stores all the strings with a defined code.
 */
public abstract class Language {
    private static HashMap<String, String> stringCollection = null;
    static
    {
        // general
        stringCollection = new HashMap<String, String>();
        stringCollection.put("confirmExit", "Are you sure you want to close the Car Park Simulation?");
        stringCollection.put("confirmExitTitle", "Exit Confirmation");
        stringCollection.put("stepOne", "One step");
        stringCollection.put("stepMany", "One hundred steps");
        stringCollection.put("yes", "Yes");
        stringCollection.put("no", "No");

        // controller
        stringCollection.put("insertSteps","Insert steps: ");
        stringCollection.put("input","1");
        stringCollection.put("startLimit","Start");
        stringCollection.put("speedLabel","Size of minute in milliseconds: ");
        stringCollection.put("speedField","1000");
        stringCollection.put("speedButton","Set");
        stringCollection.put("startButton","Start");
        stringCollection.put("stopButton","Stop");

        // day view
        stringCollection.put("runTime","Time Info");
        stringCollection.put("runningTime","Simulation is running:");
        stringCollection.put("startTime", "Simulating since:");
        stringCollection.put("currentTime", "Current time in simulation:");
        stringCollection.put("mins","In minutes: ");
        stringCollection.put("hrs","In hours: ");
        stringCollection.put("days","In days: ");
        stringCollection.put("wks","In Weeks: ");
        stringCollection.put("weekend","Weekend?");

        // simulation
        stringCollection.put("title","Car Park Simulation");

        // car park view
        stringCollection.put("birdView","Car Park Bird View");
        stringCollection.put("cars0","Total cars: 0");
        stringCollection.put("pass0","Total passholds: 0");
        stringCollection.put("cars","Total cars: ");
        stringCollection.put("pass","Total Passholders: ");

        // queue view
        stringCollection.put("info","Carpark info");
        stringCollection.put("used","Used the entrance: ");
        stringCollection.put("left","Left the carpark: ");
        stringCollection.put("payCash","Paid with cash: ");
        stringCollection.put("payPass","Paid with pass: ");

        // statistics view
        stringCollection.put("stats","Statistics");
        stringCollection.put("current","Current revenue: ");
        stringCollection.put("expected","Expected revenue: ");
    }

    /**
     * This function will return the string that belongs to the given code.
     *
     * @param code The code to retrieve the string from
     * @return String that belongs to the given code
     */
    public static String get(String code){
        return stringCollection.get(code);
    }
}
