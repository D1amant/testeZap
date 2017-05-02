package com.example.luis.testezap.Activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.luis.testezap.R;
import com.example.luis.testezap.Utils.MyConnection;


/**
 * Created by luis on 31/10/16.
 */

abstract public class  BaseActivity extends AppCompatActivity {




    public BaseActivity() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {

        super.onCreate(savedInstanceState, persistentState);
    }


    @Override
    protected void onStart() {
        super.onStart();
        upView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(!MyConnection.isConnection(getApplicationContext()))
        {
            new MaterialDialog.Builder(this)
                .title(R.string.error_title)
                .content(R.string.error_connection)
                .positiveText(R.string.ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                        finish();
                    }
                })
                .show();

        }
    }

    public void upView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }




}
