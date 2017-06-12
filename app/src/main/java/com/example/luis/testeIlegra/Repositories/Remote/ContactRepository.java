package com.example.luis.testeIlegra.Repositories.Remote;

import com.google.gson.JsonObject;

/**
 * Created by luis on 28/04/17.
 */

abstract  public class ContactRepository extends RemoteRepository {



    public void OnPostExecute(JsonObject postJsonObject){
        restPostExecute(postJsonObject);
    }


    @Override
    protected void respose(JsonObject result) {
        getRespose(result);
    }

    @Override
    protected void resposeError(String e) {
        getErroRespose(e);
    }

    protected  abstract void getRespose(JsonObject result);

    protected  abstract void getErroRespose(String e);

}
