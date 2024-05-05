package com.android.calendarapp.eventsHandling;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.google.gson.Gson;

public class EventManagement {
    // Singleton instance
    private static EventManagement instance;
    // HashMap to store events by date
    private HashMap<String, List<Event>> daysAndEvents;

    //region cons
    // Private constructor to prevent external instantiation
    private EventManagement() {
        daysAndEvents = new HashMap<>();
    }
    //endregion
    //region Singleton, neboli to aby se mi tahle trida uchovavala jakmile se otevre, aby se neprepisovala a aby byla furt otevrena
    public static EventManagement getInstance() {
        if (instance == null) {
            instance = new EventManagement();
        }
        return instance;
    }
    //endregion
    //region addEvents
    public void addEvents(String dayText, Event event) {
        // Get existing events for the day
        List<Event> events = daysAndEvents.get(dayText);

        // Add the event to the list
        events.add(event);

        // Update the HashMap
        daysAndEvents.put(dayText, events);
    }
    //endregion
    //region getEvents
    public List<Event> getEvents(String dayClicked) {
        if(!daysAndEvents.containsKey(dayClicked)){
            daysAndEvents.put(dayClicked, new ArrayList<>());
            return daysAndEvents.get(dayClicked);
        }else{
            return daysAndEvents.get(dayClicked);
        }
    }
    //endregion
    //region setHashmapy
    // Setter pro HashMap
    public void setDaysAndEvents(HashMap<String, List<Event>> newData) {
        if (newData != null) {
            this.daysAndEvents = newData;
        }
    }
    //endregion


    public HashMap<String, List<Event>> getDaysAndEvents() {
        return daysAndEvents;
    }

}