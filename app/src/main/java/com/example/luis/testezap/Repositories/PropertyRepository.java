package com.example.luis.testezap.Repositories;

import com.google.gson.JsonObject;

import com.example.luis.testezap.Entities.Address;
import com.example.luis.testezap.Entities.Client;
import com.example.luis.testezap.Entities.Property;
import com.example.luis.testezap.Repositories.RemoteRepository;

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
        Address address = new Address();
        Client client = new Client();

        address.setCEP(jsonObject.get("Endereco").getAsJsonObject().get("CEP").getAsString());
        address.setNumber(jsonObject.get("Endereco").getAsJsonObject().get("Numero").getAsString());
        address.setCity(jsonObject.get("Endereco").getAsJsonObject().get("Cidade").getAsString());
        address.setNeighbourhood(jsonObject.get("Endereco").getAsJsonObject().get("Bairro").getAsString());
        address.setDistrict(jsonObject.get("Endereco").getAsJsonObject().get("Zona").getAsString());
        address.setState(jsonObject.get("Endereco").getAsJsonObject().get("Estado").getAsString());

        client.setCodClient(jsonObject.get("Cliente").getAsJsonObject().get("CodCliente").getAsInt());
        client.setFancyName(jsonObject.get("Cliente").getAsJsonObject().get("NomeFantasia").getAsString());


        property.setCodProperty(jsonObject.get("CodImovel").getAsInt());
        property.setTypeProprety(jsonObject.get("TipoImovel").getAsString());
        property.setPrice(jsonObject.get("PrecoVenda").getAsString());
        property.setPriceCondominium(jsonObject.get("PrecoCondominio").getAsString());
        property.setRooms(jsonObject.get("Dormitorios").getAsString());
        property.setSuites(jsonObject.get("Suites").getAsString());
        property.setVacancies(jsonObject.get("Vagas").getAsString());
        property.setUsefulArea(jsonObject.get("AreaUtil").getAsString());
        property.setTotalArea(jsonObject.get("AreaTotal").getAsString());
        property.setDateUpdate(jsonObject.get("DataAtualizacao").getAsString());

        property.setSubtypeoffer(jsonObject.get("SubTipoOferta").getAsString());
        property.setObservation(jsonObject.get("Observacao").getAsString());

        property.setAddress(address);



        List<String> stringList = new ArrayList<String>();
        for(int i = 0; i < jsonObject.get("Fotos").getAsJsonArray().size(); i++ ){
            stringList.add(jsonObject.get("Fotos").getAsJsonArray().get(i).getAsString());
        }
        property.setUrlImageGalery(stringList);

        List<String> characteristics = new ArrayList<String>();
        for(int i = 0; i < jsonObject.get("Caracteristicas").getAsJsonArray().size(); i++ ){
            characteristics.add(jsonObject.get("Caracteristicas").getAsJsonArray().get(i).getAsString());
        }
        property.setCharacteristics(characteristics);

        List<String> characteristicsFeatures = new ArrayList<String>();
        for(int i = 0; i < jsonObject.get("CaracteristicasComum").getAsJsonArray().size(); i++ ){
            characteristicsFeatures.add(jsonObject.get("CaracteristicasComum").getAsJsonArray().get(i).getAsString());
        }
        property.setCharacteristicsFeatures(characteristicsFeatures);


        return property;
    }



}
