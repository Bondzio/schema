package com.scandihealth.olympicsc.commandsystem.activities;

import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.commandsystem.Command;
import com.scandihealth.olympicsc.user.User;

public class UnJoinActivityCommand implements Command {
    private Activity activity;
    private User user;

    public UnJoinActivityCommand(Activity activity, User user) {
        this.activity = activity;
        this.user = user;
    }

    public boolean execute() {
        user.removeActivity(activity);
        return true;
    }

    public void undo() {
        user.addActivity(activity);
    }

    public void redo() {
        execute();
    }
}
