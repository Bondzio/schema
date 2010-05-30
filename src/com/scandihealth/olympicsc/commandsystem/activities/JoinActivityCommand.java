package com.scandihealth.olympicsc.commandsystem.activities;

import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.commandsystem.Command;
import com.scandihealth.olympicsc.user.model.User;

public class JoinActivityCommand implements Command {
    private Activity activity;
    private User user;

    public JoinActivityCommand(Activity activity, User user) {
        this.activity = activity;
        this.user = user;
    }

    public boolean execute() {
        user.addActivity(activity);
        return true;
    }

    public void undo() {
        user.removeActivity(activity);
    }

    public void redo() {
        execute();
    }
}
