package com.scandihealth.olympicsc.data;

import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.activities.model.ActivityPartnerRequest;
import com.scandihealth.olympicsc.customvalues.model.CustomValueType;
import com.scandihealth.olympicsc.event.model.Event;
import com.scandihealth.olympicsc.event.model.EventPartnerRequest;
import com.scandihealth.olympicsc.event.model.EventVegetarianRequest;
import com.scandihealth.olympicsc.imageupload.model.Logo;
import com.scandihealth.olympicsc.location.model.Location;
import com.scandihealth.olympicsc.teams.model.Team;
import com.scandihealth.olympicsc.teams.model.TeamUserSelection;
import com.scandihealth.olympicsc.user.model.User;
import com.scandihealth.olympicsc.utilities.MessageUtils;
import org.hibernate.*;

import java.io.Serializable;
import java.util.*;

public class DataManager implements Serializable {

    public void deleteObject(Object o) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(o);
            session.flush();
            transaction.commit();
        }
        finally {
            if (session.isOpen()) {
                session.close();
            }
        }

    }

    public void saveObject(Object entity) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
    }

    public void updateObject(Object entity) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            session.flush();
            transaction.commit();
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }

    }


    public List<Event> getEvents() {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        List<Event> result = new ArrayList<Event>();
        try {
            Transaction transaction = session.beginTransaction();
            List list = session.createQuery("from Event").list();
            transaction.commit();
            for (Object o : list) {
                result.add((Event) o);
            }
        }
        finally {
            if (session.isOpen()) {
                session.flush();
                session.disconnect();
                session.close();
            }
        }
        return result;
    }

    public void saveEvent(Event event) {
        Event event1 = getEvent(event);

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            if (event1 == null) {
                Transaction transaction = session.beginTransaction();
                try {
                    session.save(event);
                    transaction.commit();
                }
                catch (HibernateException e) {
                    transaction.rollback();
                    e.printStackTrace();
                }
            } else {
                Transaction transaction = session.beginTransaction();
                try {
                    session.update(event);
                    transaction.commit();
                }
                catch (HibernateException e) {
                    transaction.rollback();
                    e.printStackTrace();
                }
            }
        }
        finally {
            if (session.isOpen()) {
                session.flush();
                session.disconnect();
                session.close();
            }
        }
    }


    public Location getLocation(String s) {
        Location result = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Object o = session.createQuery("from Location as location where location.name like '" + s + "'").uniqueResult();


            transaction.commit();
            if (o != null) {
                if (o instanceof Location) {
                    result = (Location) o;
                }
            }
        }
        finally {
            if (session.isOpen()) {
                session.flush();
                session.disconnect();
                session.close();
            }
        }
        return result;
    }

    public Location getLocation(int id) {
        Location result = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Object o = session.createQuery("from Location as location where location.idlocation = " + id).uniqueResult();


            transaction.commit();
            if (o != null) {
                if (o instanceof Location) {
                    Location location = (Location) o;
                    result = new Location();
                    result.setName(location.getName());
                    result.setIdlocation(location.getIdlocation());
                }
            }
        }
        finally {
            if (session.isOpen()) {
                session.flush();
                session.disconnect();
                session.close();
            }
        }
        return result;
    }

    public Location getLocation(Location location) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Object o = session.createQuery("from Location as location where location.idlocation = " + location.getIdlocation()).uniqueResult();
            transaction.commit();
            if (o != null) {
                return (Location) o;
            }
        }
        finally {
            if (session.isOpen()) {
                session.flush();
                session.disconnect();
                session.close();
            }
        }
        return null;
    }


    public List<Location> getLocations() {
        List<Location> result = new ArrayList<Location>();
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            List list = session.createQuery("from Location").list();
            transaction.commit();


            if (list != null) {
                for (Object o : list) {
                    Location location = (Location) o;
                    result.add(location);
                }
            }
        }
        finally {
            if (session.isOpen()) {
                session.flush();
                session.disconnect();
                session.close();
            }
        }
        return result;
    }


    public List<Activity> getActivities() {
        List<Activity> result = new ArrayList<Activity>();
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            List list = session.createQuery("from Activity").list();
            transaction.commit();

            for (Object o : list) {
                Activity activity = (Activity) o;
                result.add(activity);
            }
        }
        finally {
            if (session.isOpen()) {
                session.flush();
                session.disconnect();
                session.close();
            }
        }
        return result;
    }


    public List<Activity> getActivities(Event parent) {
        List<Activity> result = new ArrayList<Activity>();
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Object event = session.createQuery("from Event as event where event.idevent= " + parent.getIdevent()).uniqueResult();
            transaction.commit();
            if (event != null) {
                Event eventEntity = (Event) event;
                Collection<Activity> activityList = eventEntity.getActivities();
                for (Activity activity : activityList) {
                    result.add(activity);
                }
            }
        }
        finally {
            if (session.isOpen()) {
                session.flush();
                session.disconnect();
                session.close();
            }
        }
        return result;
    }

    public Activity getActivity(Activity activity) {
        Activity result = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Object o = session.createQuery("from Activity as activity where activity.idactivity= " + activity.getIdactivity()).uniqueResult();
            transaction.commit();
            if (o != null) {
                result = (Activity) o;
            }
        }
        finally {
            if (session.isOpen()) {
                session.flush();
                session.disconnect();
                session.close();
            }
        }
        return result;
    }

    public List<Activity> getActivity(String name) {
        List<Activity> result = new ArrayList<Activity>();
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            List list = session.createQuery("from Activity as activity where activity.name='" + name + "'").list();

            transaction.commit();
            for (Object o : list) {
                result.add((Activity) o);
            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
        return result;

    }

    public Event getEvent(Event event) {
        Event result = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Object o = session.createQuery("from Event as event where event.idevent= " + event.getIdevent()).uniqueResult();
            transaction.commit();
            if (o != null) {
                result = (Event) o;
            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
        return result;
    }

    public List<Event> getEvent(String name) {
        List<Event> result = new ArrayList<Event>();
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            List list = session.createQuery("from Event as event where event.name='" + name + "'").list();
            transaction.commit();
            for (Object o : list) {
                result.add((Event) o);
            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
        return result;

    }

    public User getUser(String username) {
        User result = null;
        Session session;
        Object user;
        session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = null;
            user = null;
            try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("from User as user where user.userName='" + username + "'");
                user = query.uniqueResult();
                transaction.commit();
            }
            catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
            }

            if (user != null) {
                result = (User) user;
            }
        }
        finally {
            if (session.isOpen()) {
                if (session.isOpen()) {
                    session.disconnect();
                    session.close();
                }
            }
        }
        return result;
    }


    public User getUser(String username, String password) {
        User result = null;
        Session session;
        Object user;
        session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = null;
            user = null;
            try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("from User as user where user.userName='" + username + "' and user.password='" + password + "'");
                user = query.uniqueResult();
                transaction.commit();
            }
            catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
            }

            if (user != null) {
                result = (User) user;
            }
        }
        finally {
            if (session.isOpen()) {
                if (session.isOpen()) {
                    session.disconnect();
                    session.close();
                }
            }
        }
        return result;
    }

    public boolean saveUser(User user) {
        boolean result = false;
        if (user.getEmployeeId() != null) {
            User userById = getUserByEmployeeId(user.getEmployeeId());
            if (userById != null) {
                MessageUtils.createMessage("Brugeren eksisterer allerede.");
                return false;
            }
        }
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(user);
            result = true;
            session.flush();
            transaction.commit();
        }
        catch (StaleObjectStateException sose) {
            System.out.println("StaleObjectException");
            transaction.rollback();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
            result = false;
        }
        finally {
            if (session.isOpen()) {
                session.close();
            }
        }


        return result;
    }

    private User getUserByEmployeeId(String employeeId) {

            Session session = SessionFactoryUtil.getInstance().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            String queryString = "from User user where user.employeeId ='" + employeeId+ "'";

            List list = session.createQuery(queryString).list();
            transaction.commit();
            if (list != null && list.size() == 1) {
                return (User) list.get(0);
            }
            return null;
    }

    public List<User> getUsers() {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        List<User> result = new ArrayList<User>();
        try {
            Transaction transaction = session.beginTransaction();
            List list = session.createQuery("from User").list();
            transaction.commit();
            for (Object o : list) {
                User user = (User) o;
                result.add(user);
            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
        return result;
    }

    public void deleteEvent(Event event) {
        List<User> list = getUsers();
        Collection<Activity> activities = event.getActivities();
        Set<Activity> tmpActivities = new HashSet<Activity>();
        for (Activity activityEntity : activities) {
            tmpActivities.add(activityEntity);
        }
        for (User user : list) {
            boolean userUpdated = false;
            if (user.getEvents().contains(event)) {
                for (Activity activity : tmpActivities) {
                    user.getActivities().remove(activity);
                    event.getActivities().remove(activity);
                }
                user.getEvents().remove(event);
                userUpdated = true;
            }
            if (userUpdated) {
                updateObject(user);
            }
        }

        deleteObject(event);
//        for (Activity activity : tmpActivities) {
//            deleteObject(activity);
//        }
    }

    public void deleteActivity(Activity activity) {
        List<User> list = getUsers();
        for (User user : list) {
            if (user.getActivities().contains(activity)) {
                user.getActivities().remove(activity);
            }
        }
        deleteObject(activity);

    }

    public User getUser(User user) {
        User result = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Object o = session.createQuery("from User as user where user.iduser=" + user.getIduser()).uniqueResult();
            transaction.commit();
            if (o != null) {
                result = (User) o;
            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }

        return result;
    }

    public List<User> getUserForEvent(Event selectedEvent) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        List<User> result = new ArrayList<User>();
        try {
            Transaction transaction = session.beginTransaction();

            String queryString = "select user from User as user where " + selectedEvent.getIdevent() + " in elements (user.events) order by user.firstname";
            List users = session.createQuery(queryString).list();
            transaction.commit();

            for (Object user : users) {
                result.add((User) user);
            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
        return result;
    }

    public List<Logo> getLogos() {
        List<Logo> result = new ArrayList<Logo>();
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();

            List list = session.createQuery("from Logo").list();
            transaction.commit();
            for (Object o : list) {
                Logo logo = (Logo) o;
                result.add(logo);
            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
        return result;
    }

    public Logo getLogo(String name) {
        Logo result = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Object o = session.createQuery("from Logo as logo where logo.name like '" + name + "'").uniqueResult();
            transaction.commit();
            if (o != null) {
                if (o instanceof Logo) {
                    result = (Logo) o;
                }
            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
        return result;
    }

    public ActivityPartnerRequest getPartnerRequest(User user, Activity activity) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            List result = session.createQuery("from ActivityPartnerRequest as partnerrequest " +
                    "where partnerrequest.idactivity=" + activity.getIdactivity() + " and partnerrequest.iduser=" + user.getIduser()).list();
            transaction.commit();

            if (result != null && result.size() > 0) {
                return (ActivityPartnerRequest) result.get(0);
            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
        return null;
    }

    public EventPartnerRequest getEventPartnerRequest(User user, Event event) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            List result = session.createQuery("from EventPartnerRequest as eventPartnerRequest " +
                    "where eventPartnerRequest.idevent=" + event.getIdevent() + " and eventPartnerRequest.iduser=" + user.getIduser()).list();
            transaction.commit();

            if (result != null && result.size() > 0) {
                return (EventPartnerRequest) result.get(0);
            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
        return null;
    }

    public void deleteUser(User user) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            User o = (User) session.createQuery("from User as user where user.userName='" + user.getUserName() + "'").uniqueResult();
            transaction.commit();
            if (o != null) {
                Session session1 = SessionFactoryUtil.getInstance().getCurrentSession();
                Transaction transaction1 = session1.beginTransaction();
                session1.delete(o);
                transaction1.commit();

            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
    }

    public void savePartnerRequest(ActivityPartnerRequest activityPartnerRequest) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            List result = session.createQuery("from ActivityPartnerRequest as partnerrequest " +
                    "where partnerrequest.idactivity=" + activityPartnerRequest.getIdactivity() + " and partnerrequest.iduser=" + activityPartnerRequest.getIduser()).list();
            transaction.commit();
            if (result != null && result.size() > 0) {
                Session session1 = SessionFactoryUtil.getInstance().getCurrentSession();
                Transaction transaction1 = session1.beginTransaction();
                for (Object o : result) {
                    session1.delete(o);
                }
                transaction1.commit();
            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
        saveObject(activityPartnerRequest);
    }

    public List<User> getUserForActivity(Activity activity) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        List<User> result = new ArrayList<User>();
        try {
            Transaction transaction = session.beginTransaction();

            String queryString = "select user from User as user where " + activity.getIdactivity() + " in elements (user.activities)";
            List users = session.createQuery(queryString).list();
            transaction.commit();

            for (Object user : users) {
                result.add((User) user);
            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
        return result;
    }

    public void deletePartnerRequest(ActivityPartnerRequest activityPartnerRequest) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(activityPartnerRequest);
            session.flush();
            transaction.commit();

        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
    }

    public void saveEventVegetarianRequest(EventVegetarianRequest eventVegetarianRequest) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            List result = session.createQuery("from EventVegetarianRequest as eventVegetarianRequest " +
                    "where eventVegetarianRequest.idevent=" + eventVegetarianRequest.getIdevent() + " and eventVegetarianRequest.iduser=" + eventVegetarianRequest.getIduser()).list();
            transaction.commit();
            if (result != null && result.size() > 0) {
                Session session1 = SessionFactoryUtil.getInstance().getCurrentSession();
                Transaction transaction1 = session1.beginTransaction();
                for (Object o : result) {
                    session1.delete(o);
                }
                transaction1.commit();
            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
        saveObject(eventVegetarianRequest);


    }

    public void saveEventPartnerRequest(EventPartnerRequest eventPartnerRequest) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            List result = session.createQuery("from EventPartnerRequest as eventPartnerRequest " +
                    "where eventPartnerRequest.idevent=" + eventPartnerRequest.getIdevent() + " and eventPartnerRequest.iduser=" + eventPartnerRequest.getIduser()).list();
            transaction.commit();
            if (result != null && result.size() > 0) {
                Session session1 = SessionFactoryUtil.getInstance().getCurrentSession();
                Transaction transaction1 = session1.beginTransaction();
                for (Object o : result) {
                    session1.delete(o);
                }
                transaction1.commit();
            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
        saveObject(eventPartnerRequest);
    }

    public EventVegetarianRequest getEventVegetarianRequest(User user, Event event) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            List result = session.createQuery("from EventVegetarianRequest as eventVegetarianRequest " +
                    "where eventVegetarianRequest.idevent=" + event.getIdevent() + " and eventVegetarianRequest.iduser=" + user.getIduser()).list();
            transaction.commit();

            if (result != null && result.size() > 0) {
                return (EventVegetarianRequest) result.get(0);
            }
        }
        finally {
            if (session.isOpen()) {
                session.disconnect();
                session.close();
            }
        }
        return null;
    }

    public List<Team> getTeams() {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        List result = null;
        try {
            Transaction transaction = session.beginTransaction();
            result = session.createQuery("from Team").list();
            session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

        List<Team> teams = new ArrayList<Team>();
        for (Object o : result) {
            if (o instanceof Team) {
                teams.add((Team) o);
            }
        }

        return teams;
    }

    public Team getTeam(Team teamSelection) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Team team = (Team) session.createQuery("from Team as team where team.id = " + teamSelection.getId()).uniqueResult();
        transaction.commit();
        return team;
    }

    public List<User> getUsersForTeam(Team team) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List list = session.createQuery("from TeamUserSelection as teamUserSelection where teamUserSelection.idteam= " + team.getId()).list();
        transaction.commit();
        if (list != null) {
            List<User> result = new ArrayList<User>();
            for (Object o : list) {
                User user = getUser(((TeamUserSelection) o).getIduser());
                result.add(user);
            }
            return result;
        }
        return null;
    }

    public User getUser(int iduser) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.createQuery("from User as user where user.iduser = " + iduser).uniqueResult();
        transaction.commit();
        return user;
    }

    public Team getTeam(String name) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Team team = (Team) session.createQuery("from Team as team where team.name= '" + name + "'").uniqueResult();
        transaction.commit();
        return team;

    }

    public TeamUserSelection getTeamUserSelection(User user) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        TeamUserSelection teamUserSelection = (TeamUserSelection) session.createQuery("from TeamUserSelection as teamUserSelection where teamUserSelection.iduser=" + user.getIduser()).uniqueResult();
        transaction.commit();
        return teamUserSelection;

    }

    public User getUserByEmail(String email) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List list = session.createQuery("from User as user where user.mail = '" + email + "'").list();

        transaction.commit();
        if (list != null && list.size() == 1) {
            return (User) list.get(0);
        }

        return null;
    }

    public List<CustomValueType> getCustomValueTypes() {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List list = session.createQuery("from CustomValueType").list();
        transaction.commit();
        List<CustomValueType> result = new ArrayList<CustomValueType>();
        for (Object o : list) {
            if (o instanceof CustomValueType) {
                result.add((CustomValueType) o);
            }
        }
        return result;
    }

    public List<ActivityPartnerRequest> getAllActivityPartnerRequestForUser(User user) {
        List<ActivityPartnerRequest> result = new ArrayList<ActivityPartnerRequest>();
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List list = session.createQuery("from ActivityPartnerRequest apr where apr.iduser=" + user.getIduser()).list();
        transaction.commit();
        if (list != null && list.size() > 0) {
            for (Object o : list) {
                result.add((ActivityPartnerRequest) o);
            }
        }
        return result;
    }

    public User getUserByName(String firstName, String lastName) {
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String queryString = "from User user where user.firstname ='" + firstName + "' and user.lastname ='" + lastName + "'";
//        System.out.println("queryString = " + queryString);
        List list = session.createQuery(queryString).list();
        transaction.commit();
        if (list != null && list.size() == 1) {
            return (User) list.get(0);
        }
        return null;
    }
}