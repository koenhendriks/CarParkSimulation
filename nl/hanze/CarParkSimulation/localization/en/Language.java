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
        stringCollection = new HashMap<String, String>();
        stringCollection.put("confirmExit", "Are you sure you want to close the Car Park Simulation?");
        stringCollection.put("confirmExitTitle", "Exit Confirmation");
        stringCollection.put("stepOne", "One step");
        stringCollection.put("stepMany", "One hundred steps");

        // controller
        stringCollection.put("insertSteps","Insert steps: ");
        stringCollection.put("input","1");
        stringCollection.put("startLimit","Start");
        stringCollection.put("speedLabel","Size of minute in milliseconds: ");
        stringCollection.put("speedField","1000");
        stringCollection.put("speedButton","Set");
        stringCollection.put("startButton","Start");
        stringCollection.put("stopButton","Stop");
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
