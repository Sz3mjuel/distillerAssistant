package model;

public class Customer {

    private String Name = null;
    private int VATNumber;
    private String City = null;
    private String Address = null;
    private String Phone = null;
    private String IDCard = null;
    private String EmailAddress = null;

    public Customer(String name, int VATNumber, String city, String address, String phone) {
        Name = name;
        this.VATNumber = VATNumber;
        City = city;
        Address = address;
        this.Phone = phone;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getVATNumber() {
        return VATNumber;
    }

    public void setVATNumber(int VATNumber) {
        this.VATNumber = VATNumber;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Name='" + Name + '\'' +
                ", VATNumber=" + VATNumber +
                ", City='" + City + '\'' +
                ", Address='" + Address + '\'' +
                ", Phone='" + Phone + '\'' +
                ", IDCard='" + IDCard + '\'' +
                ", EmailAddress='" + EmailAddress + '\'' +
                '}';
    }
}
