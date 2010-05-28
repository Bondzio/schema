package com.scandihealth.olympicsc.event.model;


import com.scandihealth.olympicsc.data.DataManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventRepository implements Serializable {

    private List<Event> events;
    private DataManager dataManager;

    public EventRepository() {
        dataManager = new DataManager();
        events = dataManager.getEvents();
        if (events == null) {
            events = new ArrayList<Event>();
        }
    }

    public boolean addEvent(Event event) {
        return !events.contains(event) && events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
        Event event1 = dataManager.getEvent(event);
        if (event1 != null) {
            dataManager.deleteEvent(event1);
        }
    }

    public List<Event> getEvents() {
        return events;
    }
}
