package com.android.calendarapp.eventsHandling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventManagement {
    private HashMap<String, List<Event>> daysAndEvents; // string - datum dne na ktery uzivatel klikne v main activity
    // || List je list eventu ktere majhi byt obsazene v konkretnim dni na ktery uzivatel klinul

    public void addEvents(String dayText, Event event){
        List<Event> temp = daysAndEvents.get(dayText);

        if(temp.isEmpty()){
            temp = new ArrayList<>();
        }
        temp.add(event);
        daysAndEvents.put(dayText, temp);
    }
}
