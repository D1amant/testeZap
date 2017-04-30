package com.example.luis.testezap.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.luis.testezap.Adapters.ListAdapter;
import com.example.luis.testezap.Constants.ServicesConstants;
import com.example.luis.testezap.Entities.Property;
import com.example.luis.testezap.FilterActivity;
import com.example.luis.testezap.R;
import com.example.luis.testezap.Repositories.Remote.PropertyListRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final int REQUEST_FILTER = 1;
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private List<Property> propertyList = new ArrayList<Property>();
    private MaterialDialog progress;
    private Context activityContext = this;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        listAdapter = new ListAdapter(this, propertyList);
        recyclerView.setAdapter(listAdapter);
        swipeRefreshLayout =  (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.refresh_progress_1), getResources().getColor(R.color.refresh_progress_2), getResources().getColor(R.color.refresh_progress_3));

        progress = new MaterialDialog.Builder(this)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .progress(true, 0)
                .build();
        progress.show();


        final PropertyListRepository propertyListRepository = new PropertyListRepository() {
            @Override
            protected void getPropertyListObject(List<Property> property) {
                if(propertyList.size() > 0 ) propertyList.removeAll(propertyList);
                propertyList.addAll(property);
                listAdapter.notifyDataSetChanged();
                progress.dismiss();
                swipeRefreshLayout.setRefreshing(false);
            }


            @Override
            protected void getError(String e) {
                new MaterialDialog.Builder(activityContext)
                        .title(R.string.error_title)
                        .content(R.string.error_data_connection)
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
        propertyListRepository.setUrl(ServicesConstants.url);
        propertyListRepository.onGetExecute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                propertyListRepository.onGetExecute();
            }
        });
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


        if (id == R.id.action_filter) {
            Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
            startActivityForResult(intent, REQUEST_FILTER);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_FILTER){
                int priceMin  = data.getIntExtra("priceMin" , 0);
                int priceMax  = data.getIntExtra("priceMax" , 0);
                String rooms  = data.getStringExtra("rooms");
                String suites  = data.getStringExtra("suites");
                String type  = data.getStringExtra("type");
                List<Property> result = null;
                if(!type.equals("")) {
                 result = Property.findWithQuery(Property.class, "Select * from Property where price >= ? and price <= ? and rooms <= ? and suites <= ? and type_proprety = ?", new String[]{priceMin + "", priceMax + "", rooms, suites, type});
                }else {
                  result = Property.findWithQuery(Property.class , "Select * from Property where price >= ? and price <= ? and rooms <= ? and suites <= ? " , new String[]{priceMin+"" , priceMax+"" , rooms , suites } );
                }
                propertyList.removeAll(propertyList);
                propertyList.addAll(result);
             listAdapter.notifyDataSetChanged();

            }
        }
    }
}