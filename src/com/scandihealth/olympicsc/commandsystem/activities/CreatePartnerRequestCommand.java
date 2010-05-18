package com.scandihealth.olympicsc.commandsystem.activities;

import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.activities.model.ActivityPartnerRequest;
import com.scandihealth.olympicsc.commandsystem.Command;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.user.User;

public class CreatePartnerRequestCommand implements Command {
    private User user;
    private Activity activity;
    private String partnerRequest;
    private ActivityPartnerRequest activityPartnerRequest;

    public CreatePartnerRequestCommand(User user, Activity activity, String partnerRequest) {
        this.user = user;
        this.activity = activity;
        this.partnerRequest = partnerRequest;
    }

    public boolean execute() {
        activityPartnerRequest = new ActivityPartnerRequest();
        activityPartnerRequest.setIdactivity(activity.getIdactivity());
        activityPartnerRequest.setIduser(user.getIduser());
        activityPartnerRequest.setPartnernames(partnerRequest);
        DataManager dataManager = new DataManager();
        dataManager.savePartnerRequest(activityPartnerRequest);
        return true;
    }

    public void undo() {
        DataManager dataManager = new DataManager();
        dataManager.deleteObject(activityPartnerRequest);
    }

    public void redo() {
        execute();
    }
}
