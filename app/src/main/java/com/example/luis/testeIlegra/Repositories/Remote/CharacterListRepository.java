package com.example.luis.testeIlegra.Repositories.Remote;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.example.luis.testeIlegra.Entities.Characters;
import com.example.luis.testeIlegra.Entities.Client;
import com.example.luis.testeIlegra.Entities.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis on 27/04/17.
 */

abstract public class CharacterListRepository extends RemoteRepository
{




    public void onGetExecute (){
        restGetExecute();
    }

    @Override
    protected void respose(JsonObject result)
    {
        List<Characters> list =  new ArrayList<Characters>();
        Property.deleteAll(Property.class);
        JsonArray jsonArray = result.getAsJsonObject("data").getAsJsonArray("result");
        for(int i = 0; i < jsonArray.size(); i++ ){
            list.add(getObject(jsonArray.get(i).getAsJsonObject()));
        }

        getPropertyListObject(list);
    }

    @Override
    protected void resposeError(String e)
    {
        getError(e);
    }

    protected abstract void getPropertyListObject(List<Characters> character);


    protected abstract void getError(String e);


    private Characters getObject(JsonObject jsonObject){

        Characters characters =  new Characters();
        Client client = new Client();

        characters.setName(jsonObject.get("name").getAsString());
        characters.setName(jsonObject.get("desctiption").getAsString());
        characters.setName(jsonObject.get("thumbnail").getAsJsonObject().get("path").getAsString()
                           + jsonObject.get("thumbnail").getAsJsonObject().get("extension").getAsString()
        );

        return characters;
    }



}
