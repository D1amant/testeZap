package com.example.luis.testeIlegra.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by diamante on 07/09/15.
 */
public class MyConnection {

    private Context context;


    public MyConnection(Context context) {
        this.context = context;
    }

    public static boolean isConnection(Context context) {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }

    public static boolean isConnectingToInternet(Context context) {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public String getTypeConnection(Context context) {
        String tipo = null;
        ConnectivityManager conectivtyManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);


        if (conectivtyManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()
                && !conectivtyManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()){
            tipo = "3G";
        }



        if (conectivtyManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()){
            tipo = "WIFI";
        }

        return tipo;
    }


    public static boolean isOnline (Context context) {
        boolean isOnline = true;
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING) {

            // notify user you are online

        } else if (conMgr.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {

            // notify user you are not online
            isOnline = false;
        }
        return isOnline;
    }

}