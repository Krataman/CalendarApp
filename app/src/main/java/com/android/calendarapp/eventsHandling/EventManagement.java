package com.android.calendarapp.eventsHandling;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventManagement implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "event_management.ser";
    private static final String TAG = "EventManagement";
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
    public void updateList(HashMap hMAP){
        daysAndEvents.clear();
        daysAndEvents.putAll(hMAP);
    }


    public HashMap<String, List<Event>> getDaysAndEvents() {
        return daysAndEvents;
    }
    // Serialize the EventManagement instance
    public void serialize(Context context) {
        try (FileOutputStream fileOut = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(instance);
            Log.d(TAG, "Data byla úspěšně serializována do " + FILE_NAME);
        } catch (IOException i) {
            Log.e(TAG, "Chyba při serializaci dat", i);
        }
    }

    // Deserialize the EventManagement instance
    public void deserialize(Context context) {
        try (FileInputStream fileIn = context.openFileInput(FILE_NAME);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            EventManagement em = (EventManagement) in.readObject();
            updateList(em.getDaysAndEvents());
        } catch (IOException | ClassNotFoundException e) {
            Log.e(TAG, "Chyba při deserializaci dat", e);
        }
    }

}