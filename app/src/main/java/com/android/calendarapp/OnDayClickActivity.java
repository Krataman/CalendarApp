package com.android.calendarapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.calendarapp.eventsHandling.CreateEvent;
import com.android.calendarapp.eventsHandling.Event;
import com.android.calendarapp.eventsHandling.EventAdapter;
import com.android.calendarapp.eventsHandling.EventManagement;

import java.util.ArrayList;
import java.util.List;

public class OnDayClickActivity extends AppCompatActivity {
    private Intent passedInIntent;
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private EventManagement management;
    private String dayClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odcactivity);

        management = EventManagement.getInstance();
        passedInIntent = getIntent();

        /** TESTING
        if (passedInIntent == null) {
            Log.e("OnDayClickActivity", "Intent je null");
        } else {
            Log.d("OnDayClickActivity", "Intent obsahuje data");
        }

        dayClicked = passedInIntent.getStringExtra("dayClicked");
        Log.d("OnDayClickActivity", "Hodnota dayClicked: " + dayClicked);
         **/
        dayClicked = passedInIntent.getStringExtra("dayClicked");
        // PROBLEM !!! intetn je vzdy null??? => vyreseno vubec nechapu jak jsem to fixnul ale zrejme to souviselo s problemem,
        // ze datum ktere jsem pasoval nebylo datum dne na ktery uzivatel klika ale aktualni/dnesni datum :)


        check();
        incialization();
        createToolBar();
    }

    //region createEvent
    public void createEvent(){

        String name = passedInIntent.getStringExtra("nameOfEvent");
        String location = passedInIntent.getStringExtra("descriptionOfTheEvent");
        String descritpion = passedInIntent.getStringExtra("locationOfTheEvent");
        String time = passedInIntent.getStringExtra("timeOfTheEvent");


        management.addEvents(dayClicked, new Event(name, location, descritpion, time));
    }
    //endregion
    //region createToolbar
    public void createToolBar(){
        Toolbar toolBar = findViewById(R.id.ODCToolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Go Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //endregion
    //region startCreateEvent
    public void startCreateEvent(View view){
        Intent intent = new Intent(this, CreateEvent.class);
        intent.putExtra("dayClicked", dayClicked);
        startActivity(intent);
        finish();
    }
    //endregion
    //region eventCreationCheck
    public void check(){
        if(passedInIntent != null) {
            if (passedInIntent.getBooleanExtra("isEventCreated", false)) {
                createEvent();
            }
        }
    }
    //endregion
    //region RecyclerView a Adapter
    public void incialization(){
        recyclerView = findViewById(R.id.eventsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EventAdapter(management.getEvents(passedInIntent.getStringExtra("dayClicked")), this);
        recyclerView.setAdapter(adapter);
    }
    //endregion
}