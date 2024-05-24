package com.android.calendarapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
    private boolean deser = true;

    //region onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odcactivity);

        management = EventManagement.getInstance();

        if(deser){
            management.deserialize(getApplicationContext());
        }

        passedInIntent = getIntent();

        dayClicked = passedInIntent.getStringExtra("dayClicked");

        createEvent();
        incialization();
        createToolBar();
    }
    //endregion
    //region createEvent
    public void createEvent(){

        if(passedInIntent != null && passedInIntent.getBooleanExtra("isEventCreated", false)){
            String name = passedInIntent.getStringExtra("nameOfEvent");
            String location = passedInIntent.getStringExtra("descriptionOfTheEvent");
            String descritpion = passedInIntent.getStringExtra("locationOfTheEvent");
            String time = passedInIntent.getStringExtra("timeOfTheEvent");
            int color = passedInIntent.getIntExtra("sC", Color.WHITE);


            management.addEvents(dayClicked, new Event(name, location, descritpion, time, color));
        }
    }
    //endregion
    //region createToolbar
    public void createToolBar(){
        Toolbar toolBar = findViewById(R.id.ODCToolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle(getString(R.string.go_back));
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

    //region RecyclerView a Adapter
    public void incialization(){
        recyclerView = findViewById(R.id.eventsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EventAdapter(management.getEvents(passedInIntent.getStringExtra("dayClicked")), this);
        recyclerView.setAdapter(adapter);
    }
    //endregion
}