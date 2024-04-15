package com.android.calendarapp.eventsHandling;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.ICUUncheckedIOException;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class Event extends AppCompatActivity {
    private String nameOfEvent;
    private String descriptionOfTheEvent;
    private String locationOfTheEvent;
    private String timeOfTheEvent;

    public Event(String name, String description, String location, String time) {
        nameOfEvent = name;
        descriptionOfTheEvent = description;
        locationOfTheEvent = location;
        timeOfTheEvent = time;

    }

    //region gts
    public String getNameOfEvent() {
        return nameOfEvent;
    }

    public String getDescriptionOfTheEvent() {
        return descriptionOfTheEvent;
    }

    public String getLocationOfTheEvent() {
        return locationOfTheEvent;
    }

    public String getTimeOfTheEvent() {
        return timeOfTheEvent;
    }
    //endregion
}
