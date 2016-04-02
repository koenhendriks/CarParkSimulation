/**
 * Class for creating the customers that own the cars
 * parked in the car park.
 *
 * @author Joey Boum Bletterman
 * @version 0.1 (1/4/16).
 *
 */
public class Customer {

    // Customers can be identified with a license plate registration.
    private License plate;

    /**
     * Constructor for the Customer.
     */
    public Customer()
    {
        plate = new License();
    }

    public License getPlate()
    {
        return plate;
    }
    public static void main(String[] args) {
        Customer john = new Customer();
        String test = john.getPlate().generateLicenseNumber();
        for(int i = 0; i < 20; i++) {
            System.out.println(new Customer().getPlate().generateLicenseNumber());
        }
    }
}
