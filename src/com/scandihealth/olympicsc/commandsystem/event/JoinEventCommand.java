package com.scandihealth.olympicsc.commandsystem.event;

import com.scandihealth.olympicsc.commandsystem.Command;
import com.scandihealth.olympicsc.event.model.Event;
import com.scandihealth.olympicsc.user.model.User;

import java.io.Serializable;

public class JoinEventCommand implements Command, Serializable {
    private User user;
    private Event event;

    public JoinEventCommand(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public boolean execute() {
        user.addEvent(event);
        return true;
    }

    public void undo() {
        user.removeEvent(event);
    }

    public void redo() {
        execute();
    }
}
