package com.example.luis.testeIlegra.Repositories.Remote;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.example.luis.testeIlegra.Entities.Characters;
import com.example.luis.testeIlegra.Entities.Client;
import com.example.luis.testeIlegra.Entities.Property;

/**
 * Created by luis on 27/04/17.
 */

abstract public class CharacterRepository extends RemoteRepository
{

    public void onGetExecute (){
        restGetExecute();
    }

    @Override
    protected void respose(JsonObject result)
    {
        //getPropertyObject(getObject(result.get("Imovel").getAsJsonObject()));
    }

    @Override
    protected void resposeError(String e)
    {
        getError(e);
    }

    protected abstract void getPropertyObject(Characters characters);


    protected abstract void getError(String e);


    private Property getObject(JsonObject jsonObject){
        Property property =  new Property();
        Client client = new Client();
        JsonArray results =  jsonObject.getAsJsonArray("results");

        property.setCEP(jsonObject.get("results").getAsJsonObject().get("CEP").getAsString());

        return property;
    }



}
