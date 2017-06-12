package com.example.luis.testeIlegra.Entities;

import com.orm.SugarRecord;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by luis on 27/04/17.
 */

public class Property extends SugarRecord<Property>{


    private int codProperty;
    private String typeProprety;
    private String number;
    private String CEP;
    private String state;
    private String city;
    private String district;
    private String neighbourhood;
    private int price;
    private int priceCondominium;
    private String rooms;
    private String suites;
    private String vacancies;
    private String usefulArea;
    private String totalArea;
    private String dateUpdate;
    private Client client;
    private String urlImage;
    private String characteristics;
    private String characteristicsFeatures;
    private String urlImageGalery;
    private String subtypeoffer;
    private String getSubtypeProperty;
    private String observation;


    public int getCodProperty() {
        return codProperty;
    }

    public void setCodProperty(int codProperty)
    {

        this.codProperty = codProperty;
    }

    public String getTypeProprety() {
        return typeProprety;
    }

    public void setTypeProprety(String typeProprety) {
        this.typeProprety = typeProprety;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public int getPriceCondominium() {
        return priceCondominium;
    }

    public void setPriceCondominium(int priceCondominium) {
        this.priceCondominium = priceCondominium;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public void setCharacteristicsFeatures(String characteristicsFeatures) {
        this.characteristicsFeatures = characteristicsFeatures;
    }

    public JSONArray getUrlImageGalery()
    {
        try {
            return new JSONArray(urlImageGalery);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setUrlImageGalery(String urlImageGalery) {
        this.urlImageGalery = urlImageGalery;
    }

    public String getCharacteristicsValue()
    {
        String value = "";
        try {
             JSONArray jsonArray = new JSONArray(characteristics);
            for (int i = 0; i < jsonArray.length(); i++ ) {
               value = value + "  , " + jsonArray.getString(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }



    public String getcharacteristicsFeaturesValue()
    {
        String value = "";
        try {
            JSONArray jsonArray = new JSONArray(characteristicsFeatures);
            for (int i = 0; i < jsonArray.length(); i++ ) {
                value = value + "  , " + jsonArray.getString(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;

    }
    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getAddressTxt(){
        return this.district+" , "+this.neighbourhood+" , "+this.city;
    }
}
