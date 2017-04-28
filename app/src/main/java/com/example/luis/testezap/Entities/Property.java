package com.example.luis.testezap.Entities;

import java.util.List;

/**
 * Created by luis on 27/04/17.
 */

public class Property {


    private int codProperty;
    private String typeProprety;
    private Address address;
    private String price;
    private String priceCondominium;
    private String rooms;
    private String suites;
    private String vacancies;
    private String usefulArea;
    private String totalArea;
    private String dateUpdate;
    private Client client;
    private String urlImage;
    private List<String> characteristics;
    private List<String> characteristicsFeatures;
    private List<String> urlImageGalery;
    private  String subtypeoffer;
    private String getSubtypeProperty;
    private String observation;

    public int getCodProperty() {
        return codProperty;
    }

    public void setCodProperty(int codProperty) {
        this.codProperty = codProperty;
    }

    public String getTypeProprety() {
        return typeProprety;
    }

    public void setTypeProprety(String typeProprety) {
        this.typeProprety = typeProprety;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getSuites() {
        return suites;
    }

    public void setSuites(String suites) {
        this.suites = suites;
    }

    public String getVacancies() {
        return vacancies;
    }

    public void setVacancies(String vacancies) {
        this.vacancies = vacancies;
    }

    public String getUsefulArea() {
        return usefulArea;
    }

    public void setUsefulArea(String usefulArea) {
        this.usefulArea = usefulArea;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(String totalArea) {
        this.totalArea = totalArea;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }


    public String getSubtypeoffer() {
        return subtypeoffer;
    }

    public void setSubtypeoffer(String subtypeoffer) {
        this.subtypeoffer = subtypeoffer;
    }

    public String getGetSubtypeProperty() {
        return getSubtypeProperty;
    }

    public void setGetSubtypeProperty(String getSubtypeProperty) {
        this.getSubtypeProperty = getSubtypeProperty;
    }

    public List<String> getUrlImageGalery() {
        return urlImageGalery;
    }

    public void setUrlImageGalery(List<String> urlImageGalery) {
        this.urlImageGalery = urlImageGalery;
    }

    public String getPriceCondominium() {
        return priceCondominium;
    }

    public void setPriceCondominium(String priceCondominium) {
        this.priceCondominium = priceCondominium;
    }

    public List<String> getCharacteristics() {
        return characteristics;
    }

    public String getCharacteristicsValue()
    {
        String value = "";
        for (String val : characteristics ) {

            value = value + "  , " + val;
        }
        return value;
    }

    public void setCharacteristics(List<String> characteristics) {
        this.characteristics = characteristics;
    }

    public List<String> getCharacteristicsFeatures() {
        return characteristicsFeatures;
    }

    public void setCharacteristicsFeatures(List<String> characteristicsFeatures) {
        this.characteristicsFeatures = characteristicsFeatures;
    }

    public String getcharacteristicsFeaturesValue()
    {
        String value = "";
        for (String val : characteristicsFeatures ) {

            value = value + "  , " + val;
        }
        return value;
    }
    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }


}
