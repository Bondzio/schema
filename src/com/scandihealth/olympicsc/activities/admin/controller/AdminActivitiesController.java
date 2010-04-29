package com.scandihealth.olympicsc.activities.admin.controller;

import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.activities.model.ActivityRepository;
import com.scandihealth.olympicsc.commandsystem.CommandController;
import com.scandihealth.olympicsc.commandsystem.activities.CreateActivityCommand;
import com.scandihealth.olympicsc.commandsystem.activities.DeleteActivityCommand;
import com.scandihealth.olympicsc.event.model.Event;

public class AdminActivitiesController {
    private CommandController commandController;

    public AdminActivitiesController(CommandController commandController) {
        this.commandController = commandController;
    }

    public void createActivity(ActivityRepository activityRepository, Activity activity, Event parent) {
        CreateActivityCommand createActivityCommand = new CreateActivityCommand(activityRepository, activity, parent);
        commandController.executeCommand(createActivityCommand);
    }

    public void deleteActivity(ActivityRepository activityRepository, Activity activity) {
        DeleteActivityCommand deleteActivityCommand = new DeleteActivityCommand(activityRepository, activity);
        commandController.executeCommand(deleteActivityCommand);
    }


}
