/*
 * Shuwen Wang
 * 991583096
 * Final Project
 */
package content;

public class Customer {

    private String customerID;
    private String name;
    private String StreetAddress;
    private String city;

    public Customer(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return StreetAddress;
    }

    public void setStreetAddress(String StreetAddress) {
        this.StreetAddress = StreetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString() {
        return customerID + "," + name + "," + StreetAddress + "," + city;
    }

}
