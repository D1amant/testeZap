package com.example.luis.testeIlegra.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.luis.testeIlegra.Activities.MainActivity;
import com.example.luis.testeIlegra.R;

import org.florescu.android.rangeseekbar.RangeSeekBar;

public class FilterActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private RangeSeekBar priceSeekBar;
    private RadioGroup radioGroupRooms;
    private RadioGroup radioGroupSuite;
    private Spinner typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        inflateFields();
        listeners ();
        setValues();
    }


    /**
     * Inflando Campos
     */
    private void inflateFields()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        priceSeekBar = (RangeSeekBar) findViewById(R.id.seekBar);
        radioGroupRooms = (RadioGroup) findViewById(R.id.roomsRaidoGroup);
        radioGroupSuite = (RadioGroup) findViewById(R.id.suitesRaido);
        typeSpinner = (Spinner) findViewById(R.id.type);
    }

    /**
     * Setando valores componentes
     * em tela
     * @param
     */
    private void setValues()
    {
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    private void listeners ()
    {
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                int roomsID = radioGroupRooms.getCheckedRadioButtonId();
                RadioButton roomsRadioButton = (RadioButton) findViewById(roomsID);

                int suiteID = radioGroupSuite.getCheckedRadioButtonId();
                RadioButton suiteRadioButton = (RadioButton) findViewById(suiteID);


                Intent resultData = new Intent();
                resultData.putExtra("priceMin", priceSeekBar.getSelectedMinValue());
                resultData.putExtra("priceMax", priceSeekBar.getSelectedMaxValue());
                resultData.putExtra("rooms", roomsRadioButton.getText());
                resultData.putExtra("suites", suiteRadioButton.getText());
                resultData.putExtra("type", typeSpinner.getSelectedItem().toString());
                setResult(MainActivity.RESULT_OK, resultData);
                finish();
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


        return super.onOptionsItemSelected(item);
    }


}
