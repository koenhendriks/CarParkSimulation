/**
 * Class for Pass Holders, which are Car Park customers
 * with advanced privileges.
 * @version 0.1 (2/4/2016)
 * @author Joey Boum Bletterman
 */
public class PassHolder extends Customer
{
    // contact information of the customer
    private String name;
    private String address;
    private String city;
    private boolean passHolder;

    /**
     * Constructor of the PassHolder
     *
     * @param name String with the customer
     * @param address String with the customers address
     * @param city String with the city of the customer
     */
    public PassHolder(String name, String address, String city)
    {
        // The customer receives his unique license plate
        super();
        this.name = name;
        this.address = address;
        this.city = city;

        // The customer is now a pass holder
        this.passHolder = true;
    }

    /**
     * Get the customers name
     *
     * @return String with the name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Set the customers name
     *
     * @param name String with the customers name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the customers address
     *
     * @return String with the address of the customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the customers address
     *
     * @param address String with the address of the customer
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the customers city
     *
     * @return String with the customers city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the customers city
     *
     * @param city String with the city of the customer
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Check if the customer is a pass holder,
     *
     * @return passHolder whether the customer is a pass holder
     */
    public boolean isPassHolder() {
        return passHolder;
    }

    /**
     * Method for setting Pass Holder status.
     *
     * @todo Use this to implement expiration method
     * @param passHolder Is this customer a Pass Holder?
     */
    public void setPassHolder(boolean passHolder) {
        this.passHolder = passHolder;
    }
}
