package com.scandihealth.olympicsc.activities.controller;

import com.scandihealth.olympicsc.activities.admin.controller.AdminActivitiesController;
import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.activities.model.ActivityPartnerRequest;
import com.scandihealth.olympicsc.activities.model.ActivityRepository;
import com.scandihealth.olympicsc.commandsystem.CommandController;
import com.scandihealth.olympicsc.commandsystem.activities.*;
import com.scandihealth.olympicsc.commandsystem.user.SaveUserCommand;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.event.model.Event;
import com.scandihealth.olympicsc.security.Authenticator;
import com.scandihealth.olympicsc.user.model.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;

import java.io.Serializable;
import java.util.*;

@Name("activitiesController")
@Scope(ScopeType.CONVERSATION)
@Restrict("#{identity.loggedIn}")
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
    private Object users;

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
            activityList = new ArrayList<Activity>(parent.getActivities());
        } else {
            System.out.println("no parent");
            activityList = activityRepository.getActivities();
        }

        Collections.sort(activityList, new Comparator<Activity>() {
            public int compare(Activity o1, Activity o2) {
                if (o1.getStart() != null && o2.getStart() != null) {
                    return o1.getStart().compareTo(o2.getStart());
                }

                return 0;
            }
        });
        if (activityList != null) {
            for (Activity activity1 : activityList) {
                long activityTime = calculateActivityTime(activity1);
                long offset = calculateActivityOffset(activity1);
                activity1.setPercentage(activityTime);
                activity1.setOffset(offset);
                addPartnerRequestToActivity(activity1);
            }
        }

    }

    private long calculateActivityOffset(Activity activity) {
        Date parentEndDate = parent.getEnd();
        Date parentStartDate = parent.getStart();
        if (parentEndDate == null || parentStartDate == null) {
            return 0;
        }
        long parentElapsed = parentEndDate.getTime() - parentStartDate.getTime();

        Date activityStartDate = activity.getStart();
        if (activityStartDate == null) {
            return 0;
        }
        long timeIntoEvent = activityStartDate.getTime() - parentStartDate.getTime();
        if (parentElapsed > 0) {
            return 250 * timeIntoEvent / parentElapsed;
        }

        return 0;
    }

    private long calculateActivityTime(Activity activity) {
        Date endDate = activity.getEnd();
        Date startDate = activity.getStart();
        if (endDate == null || startDate == null) {
            return 0;
        }
        long elapsed = endDate.getTime() - startDate.getTime();
        Date parentEndDate = parent.getEnd();
        Date parentStartDate = parent.getStart();
        long parentElapsed = parentEndDate.getTime() - parentStartDate.getTime();
        if (parentElapsed > 0) {
            return 250 * elapsed / parentElapsed;
        }
        return 0;
    }

    private void addPartnerRequestToActivity(Activity activity1) {
        User user = authenticator.getUser();
        DataManager dataManager = new DataManager();
        ActivityPartnerRequest activityPartnerRequest = dataManager.getPartnerRequest(user, activity1);
        if (activityPartnerRequest != null) {
            activity1.setPartnerRequest(activityPartnerRequest.getPartnernames());
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
        adminActivitiesController.createActivity(activityRepository, activity, parent);
        findActivities();
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
        User user = authenticator.getUser();
        System.out.println("User: " + user.getUserName() + "(" + user.getIduser() + ") tried joining " + selectedActivity.getName() + "(" + selectedActivity.getIdactivity() + ")");
        JoinActivityCommand joinActivityCommand = new JoinActivityCommand(selectedActivity, user);
        commandController.executeCommand(joinActivityCommand);
        SaveUserCommand saveUserCommand = new SaveUserCommand(user);
        commandController.executeCommand(saveUserCommand);

        String partnerRequest = selectedActivity.getPartnerRequest();
        if (partnerRequest != null && !"".equals(partnerRequest)) {
            CreatePartnerRequestCommand createPartnerRequestCommand = new CreatePartnerRequestCommand(user, selectedActivity, partnerRequest);
            commandController.executeCommand(createPartnerRequestCommand);
        }
        System.out.println("join successful");
        return "";
    }

    public String removeActivity() {
        User user = authenticator.getUser();
        System.out.println("User: " + user.getUserName() + "(" + user.getIduser() + ") tried cancelling " + selectedActivity.getName() + "(" + selectedActivity.getIdactivity() + ")");
        UnJoinActivityCommand unJoinActivityCommand = new UnJoinActivityCommand(selectedActivity, user);
        commandController.executeCommand(unJoinActivityCommand);
        SaveUserCommand saveUserCommand = new SaveUserCommand(user);
        commandController.executeCommand(saveUserCommand);

        if (selectedActivity.isCanRequestPartner()) {
            RemovePartnerRequestCommand removePartnerRequestCommand = new RemovePartnerRequestCommand(user, selectedActivity);
            commandController.executeCommand(removePartnerRequestCommand);
        }
        System.out.println("cancel successful");
        return "";
    }

    public boolean isHasJoined() {
        User user = authenticator.getUser();
        return user.hasJoinedActivity(selectedActivity);
    }

    public List<User> getUsers(Activity activity) {
        DataManager dataManager = new DataManager();
        return dataManager.getUserForActivity(activity);
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
