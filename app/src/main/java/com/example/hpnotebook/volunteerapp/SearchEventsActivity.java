package com.example.hpnotebook.volunteerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class SearchEventsActivity extends AppCompatActivity {

    ArrayList<String> selectedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_events);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Search Events");

        ListView chl = (ListView) findViewById(R.id.lv_events);
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        String[] items = {"Education", "Social Work", "Healthcare", "Technology", "Cooking",
                            "Events", "Donation", "Marketing"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                        R.layout.event_category_list_layout, R.id.txt_lan, items);

        chl.setAdapter(adapter);

        chl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = ((TextView)view).getText().toString();

                if(selectedItems.contains(selectedItem)){
                    selectedItems.remove(selectedItems);
                }
                else {
                    selectedItems.add(selectedItem);
                }
            }
        });

    }

    public void showSelectedItems(View view){
        String items = "";
        for(String item : selectedItems){
            items = "-" + items + "\n";
        }
        Toast.makeText(this, "You have selected \n"+items, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(SearchEventsActivity.this, EventsNearbyActivity.class));
        return true;
    }
}
