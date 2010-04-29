package com.scandihealth.olympicsc.activities.controller;

import com.scandihealth.olympicsc.activities.admin.controller.AdminActivitiesController;
import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.activities.model.ActivityRepository;
import com.scandihealth.olympicsc.commandsystem.CommandController;
import com.scandihealth.olympicsc.commandsystem.activities.*;
import com.scandihealth.olympicsc.commandsystem.user.SaveUserCommand;
import com.scandihealth.olympicsc.event.model.Event;
import com.scandihealth.olympicsc.security.Authenticator;
import com.scandihealth.olympicsc.user.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Name("activitiesController")
@Scope(ScopeType.CONVERSATION)
public class ActivitiesController implements Serializable {

    private AdminActivitiesController adminActivitiesController;
    private ActivityRepository activityRepository;

    @In(create = true)
    private CommandController commandController;

    @DataModel
    private List<Activity> activityList;

    @In(value = "activity", create = true)
    @Out(value = "selectedActivity")
    private Activity activity;

    @DataModelSelection
    @Out(required = false)
    private Activity selectedActivity;

    @In(value = "selectedEvent", required = true)
    private Event parent;

    @In
    Authenticator authenticator;

    @Create
    public void init() {
        activityRepository = new ActivityRepository();
        adminActivitiesController = new AdminActivitiesController(commandController);
    }

    @Begin(join = true)
    @Factory("activityList")
    public void findActivities() {
        if (activityRepository == null) {
            activityRepository = new ActivityRepository();
        }
        if (parent != null) {
            activityList = parent.getActivityList();
        } else {
            System.out.println("no parent");
            activityList = activityRepository.getActivities();
        }
        if (activityList != null) {
            Collections.sort(activityList, new Comparator<Activity>() {
                public int compare(Activity o1, Activity o2) {
                    if (o1.getStart() != null && o2.getStart() != null) {
                        return o1.getStart().compareTo(o2.getStart());
                    }

                    return 0;
                }
            });
        }
    }

    public Event getParent() {
        return parent;
    }

    public void setParent(Event parent) {
        this.parent = parent;
    }

    public Activity getSelectedActivity() {
        return selectedActivity;
    }

    public void setSelectedActivity(Activity selectedActivity) {
        this.selectedActivity = selectedActivity;
    }

    @Restrict("#{s:hasRole('admin')}")
    public void createActivity() {
        if (parent == null) {
            System.out.println("parent was null");
        } else {
            System.out.println("got a parent " + parent);
        }
        List<Activity> set = activityRepository.getActivities(parent);
        for (Activity activity1 : set) {
            System.out.println("activity1 = " + activity1);
        }
        System.out.println("activity = " + activity);
        adminActivitiesController.createActivity(activityRepository, activity, parent);
    }

    @Restrict("#{s:hasRole('admin')}")
    public void deleteActivity() {
        adminActivitiesController.deleteActivity(activityRepository, activity);
    }

    public String showActivities() {
        return "showActivities";
    }

    @Restrict("#{s:hasRole('admin')}")
    public String createActivities() {
        return "createActivities";
    }

    public String joinActivity() {
        JoinActivityCommand joinActivityCommand = new JoinActivityCommand(selectedActivity, authenticator.getUser());
        commandController.executeCommand(joinActivityCommand);
        SaveUserCommand saveUserCommand = new SaveUserCommand(authenticator.getUser());
        commandController.executeCommand(saveUserCommand);

        String partnerRequest = selectedActivity.getPartnerRequest();
        if (partnerRequest != null && !"".equals(partnerRequest)) {
            CreatePartnerRequestCommand createPartnerRequestCommand = new CreatePartnerRequestCommand(authenticator.getUser(), selectedActivity, partnerRequest);
            commandController.executeCommand(createPartnerRequestCommand);
        }
        return "";
    }

    public String removeActivity() {
        UnJoinActivityCommand unJoinActivityCommand = new UnJoinActivityCommand(selectedActivity, authenticator.getUser());
        commandController.executeCommand(unJoinActivityCommand);
        SaveUserCommand saveUserCommand = new SaveUserCommand(authenticator.getUser());
        commandController.executeCommand(saveUserCommand);

        if (selectedActivity.isCanRequestPartner()) {
            RemovePartnerRequestCommand removePartnerRequestCommand = new RemovePartnerRequestCommand(authenticator.getUser(), selectedActivity);
            commandController.executeCommand(removePartnerRequestCommand);
        }
        return "";
    }

    public boolean isHasJoined() {
        User user = authenticator.getUser();
        return user.hasJoinedActivity(selectedActivity);
    }

    public String updateActivity() {
        UpdateActivityCommand updateActivityCommand = new UpdateActivityCommand(selectedActivity);
        commandController.executeCommand(updateActivityCommand);
        return "";
    }

    public String doUpdateActivity() {
        return "updateActivity";
    }


}
