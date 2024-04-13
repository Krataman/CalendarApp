package com.android.calendarapp.eventsHandling;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.ICUUncheckedIOException;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class Event extends AppCompatActivity {
    private String nameOfEvent;
    private SimpleDateFormat timeOfTheEvent;
    private String formattedTime;
    private Date currentDate;

    public Event(String nameOfEvent) {
        this.nameOfEvent = nameOfEvent;

        currentDate = new Date();
        timeOfTheEvent = new SimpleDateFormat("HH:mm");

        formatTime(currentDate);
    }

    public void formatTime(Date date){
        formattedTime = timeOfTheEvent.format(date);
    }
}
