package com.scandihealth.olympicsc.commandsystem.activities;

import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.activities.model.ActivityRepository;
import com.scandihealth.olympicsc.commandsystem.Command;

/**
 * Created by IntelliJ IDEA.
 * User: Martin Hylleberg
 * Date: 05-03-2010
 * Time: 23:37:48
 * To change this template use File | Settings | File Templates.
 */
public class DeleteActivityCommand implements Command {

    private ActivityRepository activityRepository;
    private Activity activity;

    public DeleteActivityCommand(ActivityRepository activityRepository, Activity activity) {
        this.activityRepository = activityRepository;
        this.activity = activity;
    }

    public boolean execute() {
        activityRepository.removeActivity(activity);
        return true;
    }

    public void undo() {
        activityRepository.addActivity(activity);
    }

    public void redo() {
        execute();
    }
}