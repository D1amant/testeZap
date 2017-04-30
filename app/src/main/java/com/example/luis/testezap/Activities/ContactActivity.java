package com.example.luis.testezap.Activities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.luis.testezap.Constants.ServicesConstants;
import com.example.luis.testezap.R;
import com.example.luis.testezap.Repositories.Remote.ContactRepository;
import com.example.luis.testezap.Utils.ValidatorForm;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private Toolbar toolbar;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private int codProperty;
    private List<EditText> validationList = new ArrayList<EditText>();
    private Context contextActivity = this;
    private EditText mensagemEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contact);
        infalFields();

        listeners();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        codProperty = bundle.getInt("codProperty");
        setValues();
    }

    private void infalFields()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        nameEditText = (EditText) findViewById(R.id.name);
        emailEditText = (EditText) findViewById(R.id.email);
        phoneEditText = (EditText) findViewById(R.id.phone);
        mensagemEditText= (EditText) findViewById(R.id.mensagem);

    }

    private void  setValues()
    {
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        validationList = new ArrayList<EditText>();
        validationList.add(nameEditText);
        validationList.add(emailEditText);
        validationList.add(phoneEditText);
        mensagemEditText.setText(getString(R.string.message_default)+" "+ codProperty);
    }


    private void listeners()
    {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(!ValidatorForm.validateEditTextNotNull(validationList , getString(R.string.required)))
            {
                Snackbar.make(view, getString(R.string.mssage_reuquired), Snackbar.LENGTH_LONG)
                        .show();

            }else {
                JsonObject jsonObject = new JsonObject();
                Gson gson = new Gson();
                jsonObject.add("Nome" , gson.toJsonTree(nameEditText.getText().toString()));
                jsonObject.add("e-mail" , gson.toJsonTree(emailEditText.getText().toString()));
                jsonObject.add("telefone" , gson.toJsonTree(phoneEditText.getText().toString()));
                jsonObject.add("CodImovel" , gson.toJsonTree(phoneEditText.getText().toString()));


                final ContactRepository contactRepository = new ContactRepository() {
                    @Override
                    protected void getRespose(JsonObject result) {
                        String res = result.toString();
                        if(result.get("msg").getAsString().equals("OK"))
                        {
                            new MaterialDialog.Builder(contextActivity)
                                    .title(getString(R.string.success_title))
                                    .content(getString(R.string.sucess_message))
                                    .positiveText(R.string.ok)
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                                            finish();
                                        }
                                    })
                                    .show();

                        }else
                        {
                            new MaterialDialog.Builder(contextActivity)
                                    .title(getString(R.string.error_title))
                                    .content(getString(R.string.error_message))
                                    .positiveText(R.string.ok)
                                    .show();
                        }

                    }

                    @Override
                    protected void getErroRespose(String e) {
                        new MaterialDialog.Builder(contextActivity)
                                .title(getString(R.string.error_title))
                                .content(getString(R.string.error_message))
                                .positiveText(R.string.ok)
                                .show();
                    }
                };
                contactRepository.setUrl(ServicesConstants.postUrl);
                contactRepository.setContext(getApplicationContext());
                contactRepository.OnPostExecute(jsonObject);
            }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home){
            finish();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
