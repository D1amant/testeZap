package com.example.luis.testezap.Repositories.Remote;

import com.google.gson.JsonObject;

import com.example.luis.testezap.Entities.Address;
import com.example.luis.testezap.Entities.Client;
import com.example.luis.testezap.Entities.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis on 27/04/17.
 */

abstract public class PropertyListRepository extends RemoteRepository
{




    public void onGetExecute (){
        restGetExecute();
    }

    @Override
    protected void respose(JsonObject result)
    {
        List<Property> list =  new ArrayList<Property>();
        Property.deleteAll(Property.class);
        for(int i = 0; i < result.get("Imoveis").getAsJsonArray().size(); i++ ){
            list.add(getObject(result.get("Imoveis").getAsJsonArray().get(i).getAsJsonObject()));
        }

        getPropertyListObject(list);
    }

    @Override
    protected void resposeError(String e)
    {
        getError(e);
    }

    protected abstract void getPropertyListObject(List<Property> property);


    protected abstract void getError(String e);


    private Property getObject(JsonObject jsonObject){

        Property property =  new Property();
        Client client = new Client();

        property.setCEP(jsonObject.get("Endereco").getAsJsonObject().get("CEP").getAsString());
        property.setNumber(jsonObject.get("Endereco").getAsJsonObject().get("Numero").getAsString());
        property.setCity(jsonObject.get("Endereco").getAsJsonObject().get("Cidade").getAsString());
        property.setNeighbourhood(jsonObject.get("Endereco").getAsJsonObject().get("Bairro").getAsString());
        property.setDistrict(jsonObject.get("Endereco").getAsJsonObject().get("Zona").getAsString());
        property.setState(jsonObject.get("Endereco").getAsJsonObject().get("Estado").getAsString());

        client.setCodClient(jsonObject.get("Cliente").getAsJsonObject().get("CodCliente").getAsInt());
        client.setFancyName(jsonObject.get("Cliente").getAsJsonObject().get("NomeFantasia").getAsString());



        property.setCodProperty(jsonObject.get("CodImovel").getAsInt());
        property.setTypeProprety(jsonObject.get("TipoImovel").getAsString());
        property.setPrice(jsonObject.get("PrecoVenda").getAsInt());
        property.setRooms(jsonObject.get("Dormitorios").getAsString());
        property.setSuites(jsonObject.get("Suites").getAsString());
        property.setVacancies(jsonObject.get("Vagas").getAsString());
        property.setUsefulArea(jsonObject.get("AreaUtil").getAsString());
        property.setTotalArea(jsonObject.get("AreaTotal").getAsString());
        property.setDateUpdate(jsonObject.get("DataAtualizacao").getAsString());
        property.setUrlImage(jsonObject.get("UrlImagem").getAsString());
        property.setSubtypeoffer(jsonObject.get("SubTipoOferta").getAsString());
        property.setGetSubtypeProperty(jsonObject.get("SubtipoImovel").getAsString());

        property.save();
        return property;
    }



}
