package com.scandihealth.olympicsc.commandsystem.event;

import com.scandihealth.olympicsc.commandsystem.Command;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.event.model.Event;
import com.scandihealth.olympicsc.event.model.EventRepository;

public class CreateEventCommand implements Command {
    private EventRepository eventRepository;
    private Event event;

    public CreateEventCommand(EventRepository eventRepository, Event event) {
        this.eventRepository = eventRepository;
        this.event = event;
    }

    public boolean execute() {
        DataManager dataManager = new DataManager();
        dataManager.saveObject(event);
        return eventRepository.addEvent(event);
    }

    public void undo() {
        eventRepository.removeEvent(event);
    }

    public void redo() {
        execute();
    }
}
