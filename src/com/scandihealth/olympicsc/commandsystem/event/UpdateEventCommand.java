package com.scandihealth.olympicsc.commandsystem.event;

import com.scandihealth.olympicsc.commandsystem.Command;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.event.model.Event;
import com.scandihealth.olympicsc.event.model.EventRepository;

public class UpdateEventCommand implements Command {
    private EventRepository eventRepository;
    private Event event;
    private Event oldEvent;
    private DataManager dataManager;

    public UpdateEventCommand(EventRepository eventRepository, Event event) {
        this.eventRepository = eventRepository;
        this.event = event;
        dataManager = new DataManager();
    }

    public boolean execute() {
        oldEvent = dataManager.getEvent(event);
        dataManager.updateObject(event);
        return true;
    }

    public void undo() {
        dataManager.updateObject(oldEvent);
    }

    public void redo() {
        execute();
    }
}