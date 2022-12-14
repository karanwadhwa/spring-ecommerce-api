package edu.neu.karanwadhwa.springecommerceapi.model;

import javax.persistence.*;

//@Entity
//@Table(name="ADDRESSES")
@Embeddable
public class Address {
    private String street;
    private String apt;
    private String city;
    private String country;
    private String pin;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return street+", "+apt+", "+city+", "+country+" - "+pin;
    }
}
