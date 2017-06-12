package com.example.luis.testeIlegra.Repositories.Remote;

import com.google.gson.JsonObject;

import android.content.Context;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by luis on 27/04/17.
 */

abstract public class RemoteRepository {

    private String url;
    private Context context;


    protected void restGetExecute(){
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result)
                    {
                        if(e == null)
                        {
                            respose(result);
                        }else
                        {
                            resposeError(e.getMessage());
                        }
                   }
                });
    }


    protected void restPostExecute(JsonObject json){
        Ion.with(context)
                .load(url)
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e == null)
                        {
                            respose(result);
                        }else
                        {
                            resposeError(e.getMessage());
                        }
                    }
                });
    }

    protected abstract void respose(JsonObject result);

    protected abstract void resposeError(String e);


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
