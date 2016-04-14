package nl.hanze.CarParkSimulation.localization.en;

import java.util.HashMap;

/**
 * This class is used to retrieve strings that are shown in
 * the Front end of the project. If we want to change the
 * language or change a sentence we can do it here. The
 * HashMap stores all the strings with a defined code.
 *
 * @author Koen Hendriks, Joey Boum Bletterman
 * @version 0.1 (5-4-2016)
 */
public abstract class Language {
    private static HashMap<String, String> stringCollection = null;

    // the entire body must be static
    static {
        // this is the hash map
        stringCollection = new HashMap<>();

        // main strings
        stringCollection.put("confirmExit", "Are you sure you want to close the Car Park Simulation?");
        stringCollection.put("confirmExitTitle", "Exit Confirmation");
        stringCollection.put("stepOne", "One step");
        stringCollection.put("stepMany", "One hundred steps");
        stringCollection.put("yes", "Yes");
        stringCollection.put("no", "No");

        // menu strings
        stringCollection.put("fileMenu", "File");
        stringCollection.put("resetItem", "Reset");
        stringCollection.put("exitItem", "Exit");
        stringCollection.put("helpMenu", "Help");
        stringCollection.put("close", "Close");
        stringCollection.put("about", "About");
        stringCollection.put("aboutItem", "About...");
        stringCollection.put("aboutMessage", "About Car Park Simulation");
        stringCollection.put("version", "version 1.0");
        stringCollection.put("nameLine1","Written by:");
        stringCollection.put("nameLine2","Koen Hendriks");
        stringCollection.put("nameLine3","Ruben Buisman");
        stringCollection.put("nameLine4","Robin Meles");
        stringCollection.put("nameLine5","Joey Boum Bletterman");
        stringCollection.put("github","You can find this project on GitHub");

        // controller strings
        stringCollection.put("insertSteps", "Insert steps: ");
        stringCollection.put("input", "1");
        stringCollection.put("startLimit", "Start");
        stringCollection.put("speedLabel", "Size of minute in milliseconds: ");
        stringCollection.put("speedField", "1000");
        stringCollection.put("speedButton", "Set");
        stringCollection.put("startButton", "Start");
        stringCollection.put("stopButton", "Stop");

        // day view strings
        stringCollection.put("runTime", "Time Info");
        stringCollection.put("runningTime", "Simulation is running:");
        stringCollection.put("startTime", "Simulating since:");
        stringCollection.put("currentTime", "Current time in simulation:");
        stringCollection.put("mins", "In minutes: ");
        stringCollection.put("hrs", "In hours: ");
        stringCollection.put("days", "In days: ");
        stringCollection.put("wks", "In Weeks: ");
        stringCollection.put("weekend", "Weekend?");

        // simulation strings
        stringCollection.put("title", "Car Park Simulation");

        // car park view strings
        stringCollection.put("birdView", "Car Park Bird View");
        stringCollection.put("cars0", "Total cars: 0");
        stringCollection.put("pass0", "Total passholds: 0");
        stringCollection.put("reservation0", "Total reservations: 0");
        stringCollection.put("cars", "Total cars: ");
        stringCollection.put("pass", "Total Passholders: ");
        stringCollection.put("reservations", "Total reservations: ");

        // queue view strings
        stringCollection.put("info", "Carpark info");
        stringCollection.put("used", "Used the entrance: ");
        stringCollection.put("left", "Left the carpark: ");
        stringCollection.put("payCash", "Paid with cash: ");
        stringCollection.put("payPass", "Paid with pass: ");
        stringCollection.put("payReservations", "Paid by reservation: ");

        // statistics view strings
        stringCollection.put("stats", "Statistics");
        stringCollection.put("expected", "Expected revenue: ");
        stringCollection.put("current", "Current rev: ");
        stringCollection.put("yesterday", "Yesterday's rev: ");
        stringCollection.put("total", "Total revenue: ");
        stringCollection.put("bar", "Occupation: ");
        stringCollection.put("regbar", "Regular customers: ");
        stringCollection.put("pasbar", "Pass Holders: ");
        stringCollection.put("resbar", "Reservations: ");

        // legend strings
        stringCollection.put("legTit","Legend: ");
        stringCollection.put("legReg","Regular customers: ");
        stringCollection.put("legPas","Pass Holders: ");
        stringCollection.put("legAh","Reserved AH: ");
        stringCollection.put("legOp","Reserved Opera: ");
    }

    /**
     * This function will return the string that belongs to the given code.
     *
     * @param code The code to retrieve the string from
     * @return String that belongs to the given code
     */
    public static String get(String code) {
        return stringCollection.get(code);
    }
}
