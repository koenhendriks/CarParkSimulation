package nl.hanze.CarParkSimulation.logic;

import java.util.Random;

/**
 * Class License
 *
 * Class for creating license plates.
 * These plates can then be assigned to cars.
 *
 * @author Joey Boum Bletterman, Ruben Buisman
 * @version 0.2 (11-04-2016)
 */
public class License extends AbstractModel
{
    // local random generator
    private Random random;

    // different elements of a plate number
    private String left;
    private String middle;
    private String right;

    /**
     * Constructor for generating license instances.
     */
    public License() {
        random = new Random();
        left = generateNumbers();
        middle = generateString();
        right = generateNumbers();
    }

    /**
     * Generate a string of numbers for the license plate.
     *
     * @return String with random 2 numbers.
     */
    public String generateNumbers() {
        // generate number
        int rand = random.nextInt(100);

        // add a "0" if it is lower than 10
        if(rand < 10) {
            return "0" + rand;
        }

        return "" + rand;
    }

    /**
     * Generate a string with two random letters for the license plate
     *
     * @return String with two random letters
     */
    public String generateString()
    {
        // generate string
        String string = "";

        // loop through the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 2; i++) {
            string += alphabet.charAt(random.nextInt(alphabet.length()));
        }

        return "-" + string + "-";
    }

    /**
     * Generate a valid license plate number in the format 99-XX-99.
     *
     * @return String with a license plate number.
     */
    public String generateLicenseNumber()
    {
        return left + middle + right;
    }
}
