package com.android.calendarapp.eventsHandling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.calendarapp.GeneralActivityCommands;
import com.android.calendarapp.OnDayClickActivity;
import com.android.calendarapp.R;

import java.util.ArrayList;

public class CreateEvent extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        createToolBar();
        setOpenClockFragmentAction();
        setConfirmEventCreationAction();

        ((TextClock) findViewById(R.id.eventTextClock)).setFormat12Hour("HH:mm:ss a"); // nastavi format hodin
    }


    //region createToolbar
    public void createToolBar(){
        Toolbar toolBar = findViewById(R.id.CreateEventToolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("New Event");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //endregion
    //region start ODC
    public void startODC(){

        TextClock clock = findViewById(R.id.eventTextClock);
        String time = clock.getText().toString();


        Intent intent = new Intent(this, OnDayClickActivity.class);
        intent.putExtra("nameOfEvent", ((EditText) findViewById(R.id.nameOfTheEvent)).getText().toString());
        intent.putExtra("descriptionOfTheEvent", ((EditText) findViewById(R.id.descriptionOfTheEvent)).getText().toString());
        intent.putExtra("locationOfTheEvent", ((EditText) findViewById(R.id.locationOfTheEvent)).getText().toString());
        intent.putExtra("timeOfTheEvent", time);
        intent.putExtra("isEventCreated", true);

        Intent temp = getIntent(); // cast docasneho fixu popsano nize
        intent.putExtra("dayClicked", temp.getStringExtra("dayClicked")); // docasne pasovani z ODC sem a pak zase zpet jiank se mi ztraci promenna dayClicked => potrebuji nejak zmenit aby to nebylo takhle goofy :)

        startActivity(intent);
        finish();
    }
    //endregion
    //region setConfirmEventCreationAction
    public void setConfirmEventCreationAction(){
        ImageButton button = findViewById(R.id.confirmEventCreationButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startODC();
            }
        });
    }
    //endregion
    //region nastaveni buttonu pro vytvoreni fragmentu s hodinami
    public void setOpenClockFragmentAction(){
        ImageButton b = findViewById(R.id.openClockFragment);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventClockFragment fragment = new EventClockFragment();
                fragment.show(getSupportFragmentManager(), "EvenClockFragment");
            }
        });
    }
    //endregion

}