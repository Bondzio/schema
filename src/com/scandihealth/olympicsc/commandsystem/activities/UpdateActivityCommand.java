package com.scandihealth.olympicsc.commandsystem.activities;

import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.commandsystem.Command;
import com.scandihealth.olympicsc.data.DataManager;

public class UpdateActivityCommand implements Command {

    private Activity activity;

    public UpdateActivityCommand(Activity activity) {
        this.activity = activity;

    }

    public boolean execute() {
        DataManager dataManager = new DataManager();
        dataManager.updateObject(activity);
        return true;
    }

    public void undo() {

    }

    public void redo() {
        execute();
    }
}