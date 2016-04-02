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

    public PassHolder(String name, String address, String city)
    {
        // this customer receives his unique license plate
        super();
        this.name = name;
        this.address = address;
        this.city = city;

        // this customer is now a pass holder
        this.passHolder = true;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isPassHolder() {
        return passHolder;
    }

    /**
     * Method for setting Pass Holder status.
     * TODO Use this to implement expiration method
     * @param passHolder Is this customer a Pass Holder?
     */
    public void setPassHolder(boolean passHolder) {
        this.passHolder = passHolder;
    }
}
