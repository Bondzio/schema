package com.scandihealth.olympicsc.commandsystem.activities;

import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.activities.model.ActivityRepository;
import com.scandihealth.olympicsc.commandsystem.Command;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.event.model.Event;

public class CreateActivityCommand implements Command {

    private ActivityRepository activityRepository;
    private Activity activity;
    private Event parent;

    public CreateActivityCommand(ActivityRepository activityRepository, Activity activity, Event parent) {
        this.activityRepository = activityRepository;
        this.activity = activity;
        this.parent = parent;
    }

    public boolean execute() {
        if (parent != null) {
            parent.addActivity(activity);
        }
        boolean success = activityRepository.addActivity(activity);
        if (success) {
            DataManager dataManager = new DataManager();
            dataManager.saveEvent(parent);
        }
        return success;
    }

    public void undo() {
        activityRepository.removeActivity(activity);
    }

    public void redo() {
        execute();
    }
}
