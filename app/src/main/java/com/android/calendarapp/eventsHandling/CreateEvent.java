package com.android.calendarapp.eventsHandling;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.calendarapp.R;
import com.android.calendarapp.eventsHandling.EventClockFragment;

public class CreateEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        createToolBar();

        //region vytvoreni fragmentu s hodinami
        ImageButton b = findViewById(R.id.openClockFragment);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventClockFragment fragment = new EventClockFragment();
                fragment.show(getSupportFragmentManager(), "EvenClockFragment");
            }
        });
        //endregion

    }

    public void createToolBar(){
        Toolbar toolBar = findViewById(R.id.CreateEventToolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}