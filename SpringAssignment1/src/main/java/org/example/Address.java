package org.example;

public class Address {
    private String city;
    private String state;
    private String country;
    private String zipcode;

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    @Override
    public String toString(){
        return String.format("%s, %s, %s, %s", city, state, country, zipcode);
    }
}
