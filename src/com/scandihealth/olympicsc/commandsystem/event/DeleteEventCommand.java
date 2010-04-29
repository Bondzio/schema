package com.scandihealth.olympicsc.commandsystem.event;

import com.scandihealth.olympicsc.commandsystem.Command;
import com.scandihealth.olympicsc.event.model.Event;
import com.scandihealth.olympicsc.event.model.EventRepository;

public class DeleteEventCommand implements Command {
    private EventRepository eventRepository;
    private Event event;

    public DeleteEventCommand(EventRepository eventRepository, Event event) {
        this.eventRepository = eventRepository;
        this.event = event;
    }

    public boolean execute() {
        eventRepository.removeEvent(event);
        return true;
    }

    public void undo() {
        eventRepository.addEvent(event);
    }

    public void redo() {
        execute();
    }
}
