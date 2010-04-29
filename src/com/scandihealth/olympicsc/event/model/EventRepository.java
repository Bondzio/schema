package com.scandihealth.olympicsc.event.model;


import com.scandihealth.olympicsc.data.DataManager;

import java.util.ArrayList;
import java.util.List;

public class EventRepository {

    private List<Event> events;
    private DataManager dataManager;

    public EventRepository() {
        System.out.println("Init");
        dataManager = new DataManager();
        events = dataManager.getEvents();
        System.out.println("Got " + events.size() + " events");
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
