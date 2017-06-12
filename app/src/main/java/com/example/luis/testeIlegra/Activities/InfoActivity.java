package com.example.luis.testeIlegra.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.luis.testeIlegra.Adapters.SectionsPagerAdapter;
import com.example.luis.testeIlegra.Constants.ServicesConstants;
import com.example.luis.testeIlegra.Entities.Property;
import com.example.luis.testeIlegra.R;
import com.example.luis.testeIlegra.Repositories.Remote.CharacterRepository;
import com.example.luis.testeIlegra.Utils.Money;

import java.util.List;

public class InfoActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private MaterialDialog progress;
    private List<String> ulrGaleryList;
    private TextView title;
    private TextView observation;
    private TextView characteristics;
    private TextView characteristicsFeatures;
    private TextView address;
    private TextView usefullArea;
    private TextView totalArea;
    private TextView rooms;
    private TextView suites;
    private TextView price;
    private TextView priceCondominium;
    private FloatingActionButton floatingActionButton;
    private   Toolbar toolbar;
    private int codProperty;
    private Context contextActivity= this;
    private TextView countTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        inflateFields();
        listeners ();

        // Set up the ViewPager with the sections adapter.


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        codProperty = bundle.getInt("codProperty");


        progress = new MaterialDialog.Builder(this)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .progress(true, 0)
                .build();
        progress.show();


        CharacterRepository propertyListRepository = new CharacterRepository() {


            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            protected void getPropertyObject(Property property) {
                setValues(property);
                mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), property);
              // Do nothing
                mViewPager.setAdapter(mSectionsPagerAdapter);
                mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        countTextView.setText((position+1)+"/"+mSectionsPagerAdapter.getCount());
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                progress.dismiss();

            }

            @Override
            protected void getError(String e) {
                new MaterialDialog.Builder(contextActivity)
                        .title(getString(R.string.error_title))
                        .content(getString(R.string.error_data_message))
                        .positiveText(R.string.ok)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                                finish();
                            }
                        })
                        .show();

            }
        };

        propertyListRepository.setContext(getApplicationContext());
        propertyListRepository.setUrl(ServicesConstants.url+"/"+codProperty);
        propertyListRepository.onGetExecute();
    }

    /**
     * Inflando Campos
     */
    private void inflateFields()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

         title = (TextView) findViewById(R.id.title);
         observation = (TextView) findViewById(R.id.observation);
         characteristics = (TextView) findViewById(R.id.characteristics);
         characteristicsFeatures = (TextView) findViewById(R.id.characteristicsFeatures);
         address = (TextView) findViewById(R.id.address);
         usefullArea = (TextView) findViewById(R.id.usefullArea);
         totalArea = (TextView) findViewById(R.id.totalArea);
         rooms = (TextView) findViewById(R.id.rooms);
         suites = (TextView) findViewById(R.id.suite);
         price = (TextView) findViewById(R.id.price);
         priceCondominium = (TextView) findViewById(R.id.priceCondominium);
        mViewPager = (ViewPager) findViewById(R.id.container);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        countTextView = (TextView) findViewById(R.id.count);
    }

    /**
     * Setando valores componentes
     * em tela
      * @param property
     */
    private void setValues(Property property)
    {
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        ActionBar ab = getSupportActionBar();
        ab.setTitle(property.getTypeProprety());
        ab.setDisplayHomeAsUpEnabled(true);
        observation.setText(property.getObservation());
        characteristics.setText(property.getCharacteristicsValue());
        characteristicsFeatures.setText(property.getcharacteristicsFeaturesValue());
        address.setText(property.getAddressTxt());
        usefullArea.setText(getApplicationContext().getString(R.string.userFulArea)+": "+property.getUsefulArea());
        totalArea.setText(getApplicationContext().getString(R.string.userFulArea)+": "+property.getTotalArea());
        rooms.setText(getApplicationContext().getString(R.string.rooms)+": "+property.getRooms());
        suites.setText(getApplicationContext().getString(R.string.suites)+": "+property.getSuites());
        price.setText(getApplicationContext().getString(R.string.price)+": "+Money.formatMoney(property.getPrice()));
        priceCondominium.setText(getApplicationContext().getString(R.string.priceCondominium)+": "+ Money.formatMoney(property.getPriceCondominium()));

    }


    private void listeners ()
    {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                intent.putExtra("codProperty" ,codProperty);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return true;
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
