package com.scandihealth.olympicsc.commandsystem.event;

import com.scandihealth.olympicsc.commandsystem.Command;
import com.scandihealth.olympicsc.event.model.Event;
import com.scandihealth.olympicsc.user.User;

public class UnJoinEventCommand implements Command {
    private User user;
    private Event event;

    public UnJoinEventCommand(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public boolean execute() {
        user.removeEvent(event);
        return true;
    }

    public void undo() {
        user.addEvent(event);
    }

    public void redo() {
        execute();
    }
}