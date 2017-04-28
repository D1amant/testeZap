package com.example.luis.testezap.Activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.luis.testezap.Adapters.ListAdapter;
import com.example.luis.testezap.Constants.ServicesConstants;
import com.example.luis.testezap.Entities.Property;
import com.example.luis.testezap.R;
import com.example.luis.testezap.Repositories.PropertyListRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity  {

    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private List<Property> propertyList = new ArrayList<Property>();
    private MaterialDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        listAdapter = new ListAdapter(this ,propertyList) ;
        recyclerView.setAdapter(listAdapter);

        progress = new MaterialDialog.Builder(this)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .progress(true, 0)
                .build();
        progress.show();


        PropertyListRepository propertyListRepository = new PropertyListRepository() {
            @Override
            protected void getPropertyListObject(List<Property> property) {
                propertyList.addAll(property);
                listAdapter.notifyDataSetChanged();
                progress.dismiss();

            }


            @Override
            protected String getError(String e) {
                return null;
            }
        };

        propertyListRepository.setContext(getApplicationContext());
        propertyListRepository.setUrl(ServicesConstants.url);
        propertyListRepository.onGetExecute();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
