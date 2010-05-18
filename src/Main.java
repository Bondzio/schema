import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.event.model.Event;
import com.scandihealth.olympicsc.location.model.Location;
import com.scandihealth.olympicsc.user.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(final String[] args) throws Exception {

        DataManager dataManager = new DataManager();

//        cleanData(dataManager);
        createUsers(dataManager);
        createData(dataManager);
        testUsers(dataManager);
    }

    private static void createUsers(DataManager dataManager) {
        User user1 = new User();
        user1.setUserName("mah");
        user1.setFirstname("Martin");
        user1.setLastname("Hylleberg");
        user1.setAdmin(true);
        user1.setDepartment("CCS");
        user1.setEmployeeId("2020743");
        user1.setMail("mhylleberg@csc.com");
        user1.setPhone("61791394");
        dataManager.saveObject(user1);

        User user2 = new User();
        user2.setUserName("sxh");
        user2.setFirstname("Sune");
        user2.setLastname("Håkonsson");
        user2.setAdmin(false);
        user2.setDepartment("OCW");
        user2.setEmployeeId("2020152");
        user2.setMail("sxh@csc.com");
        user2.setPhone("NA");
        dataManager.saveObject(user2);
    }

    private static void createData(DataManager dataManager) {
        Location kantinen = new Location();
        kantinen.setName("Kantinen");
        Location kaelderen = new Location();
        kaelderen.setName("Kælderen");
        List<Location> locationList = dataManager.getLocations();
        for (Location location : locationList) {
            if (location.getName().equals("Kantinen")) {
                kantinen = location;
            }
            if (location.getName().equals("Kælderen")) {
                kaelderen = location;
            }

        }

//        dataManager.saveObject(kantinen);
//        dataManager.saveObject(kaelderen);


        Activity poolActivity = new Activity();
        poolActivity.setName("Pool");
        poolActivity.setStart(new Date(2010, 4, 17, 20, 0));
        poolActivity.setEnd(new Date(2010, 4, 17, 21, 0));
        poolActivity.setMinimumplayers(2);
        poolActivity.setMinimumteams(2);
        poolActivity.setLocation(kaelderen);
        poolActivity.setDescription("Pool er nu en god ting.");
//
        Activity backgammonActivity = new Activity();
        backgammonActivity.setName("Backgammon");
        backgammonActivity.setStart(new Date(2010, 4, 17, 21, 0));
        backgammonActivity.setEnd(new Date(2010, 4, 17, 22, 0));
        backgammonActivity.setMinimumplayers(2);
        backgammonActivity.setMinimumteams(2);
        backgammonActivity.setLocation(kantinen);
        backgammonActivity.setDescription("Det er nu lang tid siden");

        Event olympicscEvent = new Event();
        olympicscEvent.setName("Olympicsc");
        olympicscEvent.setStart(new Date(2010, 4, 20, 20, 0));
        olympicscEvent.setEnd(new Date(2010, 4, 20, 22, 0));
        olympicscEvent.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada, nibh at mollis fermentum, dui orci interdum est, non mattis sapien magna ac nibh. Duis eleifend turpis at diam scelerisque rhoncus. Etiam in orci vel erat viverra placerat. Vestibulum augue lacus, sodales et venenatis ultrices, posuere elementum dolor. In malesuada tempor orci ultricies tincidunt. Cras tempus sapien ac massa consectetur adipiscing. Pellentesque faucibus congue blandit. Donec convallis lorem et dui cursus sit amet dignissim dolor gravida. Pellentesque ante urna, congue non tempor a, convallis eu lorem. Aliquam augue orci, gravida in aliquet ut, pellentesque ut dui. Integer dui orci, semper quis faucibus eu, pharetra et purus. Praesent cursus tempus enim vel auctor.\n" +
                "\n" +
                "Morbi fringilla tincidunt ullamcorper. Nunc tortor enim, venenatis id varius id, dictum non justo. Sed euismod laoreet augue, nec porta nunc venenatis sit amet. Donec adipiscing ultricies mi nec fringilla. Aenean adipiscing dolor nec urna cursus sit amet porttitor libero condimentum. Suspendisse in velit odio. Phasellus sit amet posuere dui. Nulla facilisi. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Praesent adipiscing facilisis turpis, eget adipiscing ante euismod non. Nam hendrerit urna malesuada ante laoreet tincidunt.");
//        olympicscEvent.setLogo("resources/olympiCSC.png");

        olympicscEvent.addActivity(poolActivity);
        olympicscEvent.addActivity(backgammonActivity);
//
        dataManager.saveEvent(olympicscEvent);
        System.out.println("Saved olympicscEvent");

        Event vinsmagningEvent = new Event();
        vinsmagningEvent.setName("Vinsmagning");
        vinsmagningEvent.setStart(new Date(2010, 4, 17, 20, 0));
        vinsmagningEvent.setEnd(new Date(2010, 4, 17, 22, 0));
        vinsmagningEvent.setDescription("Denne gang er det hvidvine");
//        vinsmagningEvent.setLogo("");
        vinsmagningEvent.setLocation(kantinen);
        dataManager.saveEvent(vinsmagningEvent);

        List<Event> list = dataManager.getEvents();
        for (Event event1 : list) {
            System.out.println("event1 = " + event1);
            Collection<Activity> activities = event1.getActivities();
            System.out.println("Event has the following activities:");
            for (Activity activity : activities) {
                System.out.println(activity.getName() + " at " + activity.getLocation()
                        + " from " + activity.getStart() + " to " + activity.getEnd());
            }
        }
        User mah = dataManager.getUser("mah", "");
        mah.addEvent(vinsmagningEvent);

        User sxh = dataManager.getUser("sxh", "");
        sxh.addEvent(olympicscEvent);
        mah.addEvent(olympicscEvent);

        dataManager.saveUser(sxh);
        dataManager.saveUser(mah);

//        addUserToEvent(dataManager, olympicscEvent, vinsmagningEvent);
    }

    private static void addUserToEvent(DataManager dataManager, Event olympicscEvent, Event vinsmagningEvent) {
        User user = dataManager.getUser("mah", "");
        System.out.println("uservalues = " + user);

        List<Event> eventList = dataManager.getEvent(olympicscEvent.getName());
        for (Event event : eventList) {
            user.addEvent(event);
        }
        List<Event> eventList1 = dataManager.getEvent(vinsmagningEvent.getName());
        for (Event event : eventList1) {
            user.addEvent(event);
        }

        List<Activity> activityEntities = dataManager.getActivity("Pool");
        for (Activity activity : activityEntities) {
            user.addActivity(activity);
        }


        System.out.println("user = " + user);
        dataManager.updateObject(user);
    }


    private static void testUsers(DataManager dataManager) {
        User user = dataManager.getUser("mah", "");
        System.out.println("user = " + user);
    }

//    private static void cleanData(DataManager dataManager) {
//        Session session1 = SessionFactoryUtil.getInstance().getCurrentSession();
//        Transaction transaction1 = session1.beginTransaction();
//        List result1 = session1.createQuery("from Event").list();
//        System.out.println("Found " + result1.size() + " events");
//        transaction1.commit();
//
//        for (Object o : result1) {
//            Event event = (Event) o;
//            dataManager.deleteEvent(event);
//        }
//        Session session3 = SessionFactoryUtil.getInstance().getCurrentSession();
//        Transaction transaction3 = session3.beginTransaction();
//        List result3 = session3.createQuery("from Activity").list();
//        System.out.println("Found " + result3.size() + " activities");
//        transaction3.commit();
//
//        for (Object o : result3) {
//            Activity activity = (Activity) o;
//            dataManager.deleteActivity(activity);
//        }
//        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
//        Transaction transaction = session.beginTransaction();
//        List result = session.createQuery("from Location").list();
//        System.out.println("Found " + result.size() + " locations");
//        transaction.commit();
//
//        for (Object o : result) {
////            dataManager.deleteObject(o);
//        }
//
//        Session session2 = SessionFactoryUtil.getInstance().getCurrentSession();
//        Transaction transaction2 = session2.beginTransaction();
//        List result2 = session2.createQuery("from User").list();
//        System.out.println("Found " + result2.size() + " users");
//        transaction2.commit();
//
//        for (Object o : result2) {
//            dataManager.deleteObject(o);
//        }
//
//
//    }
}
