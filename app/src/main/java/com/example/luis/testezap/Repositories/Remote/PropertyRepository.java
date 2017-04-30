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

abstract public class PropertyRepository extends RemoteRepository
{

    public void onGetExecute (){
        restGetExecute();
    }

    @Override
    protected void respose(JsonObject result)
    {
        getPropertyObject(getObject(result.get("Imovel").getAsJsonObject()));
    }

    @Override
    protected void resposeError(String e)
    {
        getError(e);
    }

    protected abstract void getPropertyObject(Property property);


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
        property.setPriceCondominium(jsonObject.get("PrecoCondominio").getAsInt());
        property.setRooms(jsonObject.get("Dormitorios").getAsString());
        property.setSuites(jsonObject.get("Suites").getAsString());
        property.setVacancies(jsonObject.get("Vagas").getAsString());
        property.setUsefulArea(jsonObject.get("AreaUtil").getAsString());
        property.setTotalArea(jsonObject.get("AreaTotal").getAsString());
        property.setDateUpdate(jsonObject.get("DataAtualizacao").getAsString());

        property.setSubtypeoffer(jsonObject.get("SubTipoOferta").getAsString());
        property.setObservation(jsonObject.get("Observacao").getAsString());


        property.setUrlImageGalery(jsonObject.get("Fotos").getAsJsonArray().toString());

        property.setCharacteristics(jsonObject.get("Caracteristicas").getAsJsonArray().toString());

        property.setCharacteristicsFeatures(jsonObject.get("CaracteristicasComum").getAsJsonArray().toString());

        return property;
    }



}
