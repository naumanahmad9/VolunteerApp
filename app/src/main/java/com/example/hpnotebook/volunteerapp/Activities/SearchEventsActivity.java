package com.example.hpnotebook.volunteerapp.Activities;

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

import com.example.hpnotebook.volunteerapp.R;

import java.util.ArrayList;
import java.util.Objects;

public class SearchEventsActivity extends AppCompatActivity {

    // ArrayList<String> selectedItems = new ArrayList<>();

    String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_events);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Search Events by Category");

        ListView chl = (ListView) findViewById(R.id.lv_events);
        chl.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        String[] items = {"Education", "Social Work", "Healthcare", "Technology", "Cooking",
                            "Event Management", "Donation", "Marketing", "Transportation", "Other"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                        R.layout.event_category_list_layout, R.id.events_ctv, items);

        chl.setAdapter(adapter);

        chl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = ((TextView)view).getText().toString();

                if(selectedCategory == selectedItem){
                    selectedCategory = "";
                }
                else {
                    selectedCategory = selectedItem;
                }
                /*
                if(selectedItems.contains(selectedItem)){
                    selectedItems.remove(selectedItems);
                }
                else {
                    selectedItems.add(selectedItem);
                }
                */
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(!selectedCategory.equals("")) {
            Intent mIntent = new Intent(SearchEventsActivity.this, NearbyEventsActivity.class);
            // mIntent.putStringArrayListExtra("searchCategories", selectedItems);
            mIntent.putExtra("selectedCategory", selectedCategory);
            startActivity(mIntent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            finish();
        }
        else {
            Toast.makeText(SearchEventsActivity.this, "Please select a category", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
