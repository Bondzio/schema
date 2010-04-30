package com.scandihealth.olympicsc.commandsystem.activities;

import com.scandihealth.olympicsc.activities.model.ActivityPartnerRequest;
import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.commandsystem.Command;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.user.User;

public class RemovePartnerRequestCommand implements Command {
    private User user;
    private Activity activity;
    private ActivityPartnerRequest old_activityPartnerRequest;

    public RemovePartnerRequestCommand(User user, Activity activity) {
        this.user = user;
        this.activity = activity;
    }

    public boolean execute() {
        DataManager dataManager = new DataManager();
        old_activityPartnerRequest = dataManager.getPartnerRequest(user, activity);
        if (old_activityPartnerRequest != null) {
            dataManager.deleteObject(old_activityPartnerRequest);
        }

        return true;
    }

    public void undo() {
        DataManager dataManager = new DataManager();
        dataManager.saveObject(old_activityPartnerRequest);
    }

    public void redo() {
        execute();
    }
}
