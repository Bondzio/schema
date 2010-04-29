package com.scandihealth.olympicsc.activities.model;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.event.model.Event;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.util.ArrayList;
import java.util.List;

@Name("activityRepository")
@Scope(ScopeType.SESSION)
public class ActivityRepository {
    List<Activity> activities;

    public ActivityRepository() {
        DataManager dataManager = new DataManager();
        activities = dataManager.getActivities();
        System.out.println("Got " + activities.size() + " activities");
        if (activities == null) {
            activities = new ArrayList<Activity>();
        }
    }


    public boolean addActivity(Activity activity) {
        boolean result;
        for (Activity activity1 : activities) {
            System.out.println("activity1 = " + activity1);
        }
        if (!activities.contains(activity)) {
            System.out.println("did not contain activity");
            activities.add(activity);
            result = true;
        } else {
            System.out.println("contained activity");
            result = false;
        }
        for (Activity activity1 : activities) {
            System.out.println("activity1 = " + activity1);
        }
        return result;
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public List<Activity> getActivities(Event parent) {
        DataManager dataManager = new DataManager();
        return dataManager.getActivities(parent);
    }
}
