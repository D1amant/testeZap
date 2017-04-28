package com.example.luis.testezap.Entities;

/**
 * Created by luis on 27/04/17.
 */

public class Address {
    private String number;
    private String CEP;
    private String state;
    private String city;
    private String district;
    private String neighbourhood;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getAddressTxt(){
        return this.district+" , "+this.neighbourhood+" , "+this.city;
    }
}
