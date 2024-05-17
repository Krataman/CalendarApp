package com.android.calendarapp.eventsHandling;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.ICUUncheckedIOException;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.calendarapp.R;

import java.io.Serializable;
import java.util.Date;

public class Event extends AppCompatActivity implements Serializable {
    private String nameOfEvent;
    private String descriptionOfTheEvent;
    private String locationOfTheEvent;
    private String timeOfTheEvent;
    private int color;

    public Event(String name, String description, String location, String time, int eventColor) {
        nameOfEvent = name;
        descriptionOfTheEvent = description;
        locationOfTheEvent = location;
        timeOfTheEvent = time;
        color = eventColor;

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

    public int getColor() {
        return color;
    }
    //endregion
}
