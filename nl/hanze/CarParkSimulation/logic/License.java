package nl.hanze.CarParkSimulation.logic;

/**
 * Class License
 *
 * @author Ruben Buisman
 * @version 0.1 (05-04-2016)
 */

import java.util.Random;

/**
 * This class creates a license plate.
 */

public class License extends AbstractModel {
    Random random;

    // Different elements of a plate number
    private String left;
    private String middle;
    private String right;

    public License()
    {
        random = new Random();
        left = generateNumbers();
        middle = generateString();
        right = generateNumbers();
        generateLicenseNumber();
    }

    /**
     * Generate a number for the license plate
     *
     * @todo Should this be a String method? Probably could use Integers here.
     * @return String with random 2 numbers
     */
    public String generateNumbers()
    {
        // generate number
        int rand = random.nextInt(99);

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

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 2; i++) {
            string += alphabet.charAt(random.nextInt(alphabet.length()));
        }

        return "-" + string + "-";
    }

    /**
     * Generate a valid license plate number in the format 99-XX-99
     *
     * @return String with a license plate number
     */
    public String generateLicenseNumber()
    {
        return left + middle + right;
    }
}
