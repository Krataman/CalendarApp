package com.android.calendarapp.eventsHandling;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.calendarapp.OnDayClickActivity;
import com.android.calendarapp.R;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CreateEvent extends AppCompatActivity implements EventClockFragment.OnTimeSelectedListener, ColorPickerFragment.OnColorSelectedListener{

    private View colorView;
    private int currentColor = Color.WHITE;

    //region onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        createToolBar();
        setOpenClockFragmentAction();
        setOpenColorPickerFragmentAction();
        setConfirmEventCreationAction();
    }
    //endregion
    //region createToolbar
    public void createToolBar(){
        Toolbar toolBar = findViewById(R.id.CreateEventToolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle(getString(R.string.new_event));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //endregion
    //region start ODC
    public void startODC(){

        TextView time = findViewById(R.id.eventTextClock);

        Intent intent = new Intent(this, OnDayClickActivity.class);
        intent.putExtra("nameOfEvent", ((EditText) findViewById(R.id.nameOfTheEvent)).getText().toString());
        intent.putExtra("descriptionOfTheEvent", ((EditText) findViewById(R.id.descriptionOfTheEvent)).getText().toString());
        intent.putExtra("locationOfTheEvent", ((EditText) findViewById(R.id.locationOfTheEvent)).getText().toString());
        intent.putExtra("timeOfTheEvent", time.getText());
        intent.putExtra("isEventCreated", true);
        intent.putExtra("sC", currentColor);

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

    public void setOpenColorPickerFragmentAction(){
        ImageButton b = findViewById(R.id.openColorPickerButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerFragment fragment = new ColorPickerFragment();
                fragment.show(getSupportFragmentManager(), "ColorPickerFragment");
            }
        });
    }

    //endregion
    //region onTimeSelected (LISTENER pro ziskani casu, ktery uzivatel vybere)
    @Override
    public void onTimeSelected(int hourOfDay, int minute) {
        String selectedTime = String.format("%02d:%02d", hourOfDay, minute);

        TextView textView = findViewById(R.id.eventTextClock);
        textView.setText(selectedTime);
    }
    //endregion
    //region color LISTENER
    @Override
    public void onColorSelected(int color) {
        currentColor = color;
    }
    //endregion
}