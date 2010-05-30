package com.scandihealth.olympicsc;

import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.event.model.Event;
import com.scandihealth.olympicsc.location.model.Location;
import com.scandihealth.olympicsc.location.model.LocationRepository;
import com.scandihealth.olympicsc.user.model.User;
import junit.framework.TestCase;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class EventTest extends TestCase {
    private DataManager dataManager;

    public void setUp() {
        dataManager = new DataManager();
    }

    public void tearDown() {
        List<Event> list = dataManager.getEvent("TestEvent");
        if (list != null) {
            for (Event event : list) {
                Event event1 = dataManager.getEvent(event);
                if (event1 != null) {
                    dataManager.deleteEvent(event1);
                }
            }
        }
        dataManager = null;
    }


    public void testCreateEvent() throws Exception {
        Event event = new Event();
        event.setName("TestEvent");
        event.setStart(new Date(2010, 4, 17, 20, 15));
        event.setEnd(new Date(2010, 4, 17, 21, 15));


        dataManager.saveEvent(event);

        List<Event> list = dataManager.getEvent("TestEvent");
        assertNotNull(list);

        Event event1 = list.get(0);
        assertEquals(event, event1);
    }

    public void testDeleteEvent() throws Exception {
        Event event = new Event();
        event.setName("TestEvent");
        event.setStart(new Date(2010, 4, 17, 20, 15));
        event.setEnd(new Date(2010, 4, 17, 21, 15));


        dataManager.saveEvent(event);

        List<Event> list = dataManager.getEvent("TestEvent");
        assertNotNull(list);

        Event event1 = list.get(0);
        assertNotNull(event1);

        dataManager.deleteEvent(event1);

        List<Event> list1 = dataManager.getEvent("TestEvent");
        assertEquals(0, list1.size());
    }

    public void testAddActivityToEvent() throws Exception {
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

        List<Event> list = dataManager.getEvent("TestEvent");
        assertNotNull(list);

        Event event1 = list.get(0);
        assertNotNull(event1);

        Collection<Activity> set = event1.getActivities();
        assertNotNull(set);

        assertFalse(set.isEmpty());

        for (Activity activity1 : set) {
            assertEquals(activity, activity1);
        }
    }

    public void testAddLocationToEvent() throws Exception {

        Event event = new Event();
        event.setName("TestEvent");
        event.setStart(new Date(2010, 04, 17, 20, 15));
        event.setEnd(new Date(2010, 04, 17, 21, 15));

        LocationRepository locationRepository = new LocationRepository();
        List<Location> locationList = locationRepository.getLocations();

        Location location = null;
        for (Location location1 : locationList) {
            if (location1.getName().equals("Kantinen")) {
                location = location1;
            }
        }
        assertNotNull("Found no location named Kantinen", location);

        event.setLocation(location);

        dataManager.saveEvent(event);

        List<Event> list = dataManager.getEvent("TestEvent");
        assertNotNull(list);
        Event event1 = list.get(0);
        assertNotNull(event1);

        Location location1 = event1.getLocation();
        assertEquals("Location did not match", location, location1);
    }

    public void testUsersForEvent() {
        List<Event> list = dataManager.getEvent("Olympicsc");
        assertEquals(1, list.size());

        Event event = list.get(0);
        List<User> userList = dataManager.getUserForEvent(event);
        assertEquals(2, userList.size());
        int counter = 0;
        for (User user : userList) {
            if (counter == 0) {
                assertEquals("mah", user.getUserName());
            }
            if (counter == 1) {
                assertEquals("sxh", user.getUserName());
            }
            counter++;
        }
        System.out.println("user joined olympicsc");
        for (User user : userList) {
            System.out.println(user);
        }
        List<Event> vinSmagningList = dataManager.getEvent("Vinsmagning");
        assertEquals(1, vinSmagningList.size());

        Event vinSmagning = vinSmagningList.get(0);
        List<User> userList1 = dataManager.getUserForEvent(vinSmagning);
        System.out.println("users joined vinsmagning");
        for (User user : userList1) {
            System.out.println("user = " + user);
        }
        assertEquals(1, userList1.size());
        User user = userList1.get(0);
        assertEquals("mah", user.getUserName());

    }
}
