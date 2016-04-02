import java.util.Random;

/**
 * Class for generating License plate objects.
 * @author Joey Boum Bletterman
 * @version 0.2 (2/4/2016)
 */
public class License {

    Random random;

    // different elements of a plate number
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

    public String generateNumbers()
    {
        // generate number
        int rand = random.nextInt(99);

        if(rand < 10) {
            return "0" + rand;
        }

        return "" + rand;
    }

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

    public String generateLicenseNumber()
    {
        return left + middle + right;
    }
}
