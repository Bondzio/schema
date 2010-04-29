package com.scandihealth.olympicsc;

import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.data.DataManager;
import junit.framework.TestCase;

import java.util.Date;
import java.util.List;

public class ActivityTest extends TestCase {
    private DataManager dataManager;

    public void setUp() {
        dataManager = new DataManager();
    }

    public void tearDown() {

        List<Activity> list = dataManager.getActivity("TestActivity");
        if (list != null) {
            for (Activity activity : list) {
                Activity activity1 = dataManager.getActivity(activity);
                if (activity1 != null) {
                    dataManager.deleteActivity(activity1);
                }
            }
        }
        dataManager = null;
    }


    public void testCreateActivity() throws Exception {
        Activity activity = new Activity();
        activity.setName("TestActivity");
        activity.setStart(new Date(2010, 04, 17, 20, 15));
        activity.setEnd(new Date(2010, 04, 17, 21, 15));

        dataManager.saveObject(activity);

        List<Activity> activityList = dataManager.getActivity("TestActivity");
        assertNotNull(activityList);

        Activity activity1 = activityList.get(0);
        assertNotNull(activity1);
        assertEquals(activity, activity1);
    }

    public void testDeleteActivity() throws Exception {
        Activity activity = new Activity();
        activity.setName("TestActivity");
        activity.setStart(new Date(2010, 04, 17, 20, 15));
        activity.setEnd(new Date(2010, 04, 17, 21, 15));


        dataManager.saveObject(activity);

        List<Activity> list = dataManager.getActivity("TestActivity");
        assertNotNull(list);

        Activity activity1 = list.get(0);
        assertNotNull(activity1);

        dataManager.deleteActivity(activity1);

        List<Activity> list1 = dataManager.getActivity("TestActivity");
        assertEquals(0, list1.size());
    }

    public void testGetActivity() {
        Activity activity = new Activity();
        activity.setName("TestActivity");

        Activity activity1 = dataManager.getActivity(activity);
        assertNull(activity1);

        dataManager.saveObject(activity);

        Activity activity2 = dataManager.getActivity(activity);
        assertNotNull(activity2);
        assertEquals("TestActivity", activity2.getName());

        List<Activity> activityList = dataManager.getActivity("Non existing");
        assertNotNull(activityList);
        assertEquals(0, activityList.size());

        List<Activity> list = dataManager.getActivity("TestActivity");
        assertNotNull(list);
        assertEquals(1, list.size());


    }
}
