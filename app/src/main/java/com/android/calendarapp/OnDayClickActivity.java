package com.android.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.calendarapp.eventsHandling.CreateEvent;
import com.android.calendarapp.eventsHandling.Event;
import com.android.calendarapp.eventsHandling.EventAdapter;

import java.util.ArrayList;
import java.util.List;

public class OnDayClickActivity extends AppCompatActivity {
    private Intent passedInIntent;
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odcactivity);

        eventList = new ArrayList<>();
        passedInIntent = getIntent();

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


        eventList.add(new Event(name, location, descritpion, time));
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
    //region startMainActivity
    public void startMainActivity(View view) {
        GeneralActivityCommands.startActivity(this, MainActivity.class);
        finish();
    }
    //endregion
    //region startCreateEvent
    public void startCreateEvent(View view){
        GeneralActivityCommands.startActivity(this, CreateEvent.class);
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

        adapter = new EventAdapter(eventList, this);
        recyclerView.setAdapter(adapter);
    }
    //endregion
}