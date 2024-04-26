package com.android.calendarapp.eventsHandling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventManagement {
    // Singleton instance
    private static EventManagement instance;
    // HashMap to store events by date
    private HashMap<String, List<Event>> daysAndEvents;

    // Private constructor to prevent external instantiation
    private EventManagement() {
        daysAndEvents = new HashMap<>();
    }

    // Singleton access method
    public static EventManagement getInstance() {
        if (instance == null) {
            instance = new EventManagement();
        }
        return instance;
    }

    // Method to add events to a specific day
    public void addEvents(String dayText, Event event) {
        // Get existing events for the day
        List<Event> events = daysAndEvents.get(dayText);

        // Add the event to the list
        events.add(event);

        // Update the HashMap
        daysAndEvents.put(dayText, events);
    }

    // Method to get all events for a specific day
    public List<Event> getEvents(String dayClicked) {
        if(!daysAndEvents.containsKey(dayClicked)){
            daysAndEvents.put(dayClicked, new ArrayList<>());
            return daysAndEvents.get(dayClicked);
        }else{
            return daysAndEvents.get(dayClicked);
        }
    }
}
