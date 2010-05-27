package com.scandihealth.olympicsc;


import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.event.model.Event;
import com.scandihealth.olympicsc.user.User;
import junit.framework.TestCase;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class UserTest extends TestCase {
    private DataManager dataManager;
    private User user;

    public void setUp() {
        dataManager = new DataManager();
    }

    public void tearDown() {
        List<User> userList = dataManager.getUsers();
        for (User user1 : userList) {
            if (user1.getUserName().equals("test")) {
                dataManager.deleteObject(user1);
            }
        }

        List<Event> eventList1 = dataManager.getEvent("TestEvent");
        if (eventList1 != null) {
            for (Event eventtmp : eventList1) {
                Event eventtmp1 = dataManager.getEvent(eventtmp);
                if (eventtmp1 != null) {
                    dataManager.deleteEvent(eventtmp1);
                }
            }
        }
        List<Activity> activityList = dataManager.getActivity("TestActivity");
        for (Activity activity : activityList) {
            dataManager.deleteActivity(activity);
        }
        dataManager = null;
    }

    public void testCreateUser() throws Exception {
        user = new User();
        user.setUserName("test");
        user.setAdmin(true);
        user.setDepartment("ccs");
        user.setEmployeeId("123456");
        user.setFirstname("test");
        user.setLastname("testerson");
        user.setShirtsize("");

        DataManager dataManager = new DataManager();
        dataManager.saveUser(user);

        User user1 = dataManager.getUser("test");
        assertEquals(user, user1);
    }

    public void testDeleteUser() throws Exception {
        testCreateUser();
        User user1 = dataManager.getUser("test");
        assertEquals(user, user1);
        dataManager.deleteObject(user1);

        User user2 = dataManager.getUser("test");
        assertNull(user2);
    }

    public void testUpdateUser() throws Exception {
        testCreateUser();

        User user1 = dataManager.getUser("test");
        assertEquals(user, user1);
        assertEquals(null, user1.getPhone());

        user1.setPhone("347457");

        dataManager.updateObject(user1);
        User user2 = dataManager.getUser("test");

        assertEquals("347457", user2.getPhone());
    }

    public void testJoinEvent() throws Exception {
        testCreateUser();
        Event event = new Event();
        event.setName("TestEvent");
        event.setStart(new Date(2010, 04, 17, 20, 15));
        event.setEnd(new Date(2010, 04, 17, 21, 15));


        dataManager.saveEvent(event);

        Activity activity = new Activity();
        activity.setName("TestActivity");
        activity.setStart(new Date(2010, 04, 17, 20, 15));
        activity.setEnd(new Date(2010, 04, 17, 21, 15));


        event.addActivity(activity);
        dataManager.updateObject(event);

        user.addEvent(event);
        dataManager.updateObject(user);

        User user1 = dataManager.getUser("test");

        assertNotNull(user1);

        Collection<Event> set1 = user1.getEvents();
        assertNotNull(set1);
        assertFalse(set1.isEmpty());

        for (Event event2 : set1) {
            assertEquals(event, event2);
        }

        for (Event event1 : set1) {
            user.removeEvent(event1);
        }

        dataManager.updateObject(user);

        User user11 = dataManager.getUser("test");
        Collection<Event> set = user11.getEvents();
        assertNotNull(set);
        assertTrue("User had events when user should not have any.", set.isEmpty());


    }

    public void testJoinActivity() throws Exception {
        testCreateUser();
        Event event = new Event();
        event.setName("TestEvent");
        event.setStart(new Date(2010, 04, 17, 20, 15));
        event.setEnd(new Date(2010, 04, 17, 21, 15));


        dataManager.saveEvent(event);

        Activity activity = new Activity();
        activity.setName("TestActivity");
        activity.setStart(new Date(2010, 04, 17, 20, 15));
        activity.setEnd(new Date(2010, 04, 17, 21, 15));


        event.addActivity(activity);
        dataManager.updateObject(event);


        user.addActivity(activity);
        dataManager.updateObject(user);


        User user1 = dataManager.getUser("test");

        assertNotNull(user1);

        Collection<Activity> set1 = user1.getActivities();
        assertNotNull(set1);
        assertFalse(set1.isEmpty());

        for (Activity activity1 : set1) {
            assertEquals(activity, activity1);
        }

        for (Activity activity1 : set1) {
            user.removeActivity(activity1);
        }

        dataManager.updateObject(user);

        User user11 = dataManager.getUser("test");
        Collection<Activity> set = user11.getActivities();
        assertNotNull(set);
        assertTrue("User had activities when user should not have any.", set.isEmpty());


    }
}