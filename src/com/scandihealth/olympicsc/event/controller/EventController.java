package com.scandihealth.olympicsc.event.controller;

import com.scandihealth.olympicsc.commandsystem.CommandController;
import com.scandihealth.olympicsc.commandsystem.event.*;
import com.scandihealth.olympicsc.commandsystem.user.SaveUserCommand;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.event.model.Event;
import com.scandihealth.olympicsc.event.model.EventRepository;
import com.scandihealth.olympicsc.security.Authenticator;
import com.scandihealth.olympicsc.user.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.Renderer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Name("eventController")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class EventController implements Serializable {

    @In(create = true)
    private CommandController commandController;

    private EventRepository eventRepository;

    @DataModel
    private List<Event> eventList;

    @In(value = "event", create = true)
    @Out(value = "selectedEvent")
    private Event event;

    @DataModelSelection
//    @In(value="selectedEvent", required = false)
    @Out(required = false)
    private Event selectedEvent;

    @In
    Authenticator authenticator;
    private Boolean hasActivities;

    @In(create = true)
    private Renderer renderer;

    @Create
    public void init() {
        eventRepository = new EventRepository();
    }

    @Factory("eventList")
    public void findEvents() {
        if (eventRepository == null) {
            eventRepository = new EventRepository();
        }
        eventList = eventRepository.getEvents();
        Collections.sort(eventList, new Comparator<Event>() {
            public int compare(Event o1, Event o2) {
                if (o1.getStart() != null && o2.getStart() != null) {
                    return o1.getStart().compareTo(o2.getStart());
                }

                return 0;
            }
        });
    }


    public String join() {
        String result = "";
        JoinEventCommand joinEventCommand = new JoinEventCommand(authenticator.getUser(), selectedEvent);
        commandController.executeCommand(joinEventCommand);
        if (selectedEvent.getActivities().size() > 0) {
            result = "showEventInfo";
        }

        SaveUserCommand saveUserCommand = new SaveUserCommand(authenticator.getUser());
        commandController.executeCommand(saveUserCommand);

        return result;
    }

    public String cancel() {
        UnJoinEventCommand unJoinEventCommand = new UnJoinEventCommand(authenticator.getUser(), selectedEvent);
        commandController.executeCommand(unJoinEventCommand);
        SaveUserCommand saveUserCommand = new SaveUserCommand(authenticator.getUser());
        commandController.executeCommand(saveUserCommand);
        return "";

    }


    public boolean isJoinedEvent() {
        return authenticator.getUser().hasJoinedEvent(selectedEvent);
    }


    public String createEvent() {
        CreateEventCommand createEventCommand = new CreateEventCommand(eventRepository, event);
        commandController.executeCommand(createEventCommand);
        if (hasActivities) {
            selectedEvent = event;
            return "createActivities";
        } else {
            return "eventCreated";
        }
    }

    public String addActivityToEvent() {
        return "createActivities";
    }

    public void setHasActivities(Boolean hasActivities) {
        this.hasActivities = hasActivities;
    }

    public Boolean getHasActivities() {
        return hasActivities;
    }

    public void deleteEvent() {
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(eventRepository, selectedEvent);
        commandController.executeCommand(deleteEventCommand);
    }

    public String doUpdateEvent() {
        UpdateEventCommand updateEventCommand = new UpdateEventCommand(eventRepository, selectedEvent);
        commandController.executeCommand(updateEventCommand);
        FacesContext.getCurrentInstance().addMessage("eventList", new FacesMessage( selectedEvent.getName() + " er blevet opdateret."));
        return "";
    }

    public String updateEvent() {
        return "updateEvent";
    }

    public List<User> getUsers() {
        DataManager dataManager = new DataManager();
        return dataManager.getUserForEvent(selectedEvent);
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public String showDetails() {
        return "showDetails";
    }

    public Object showEventInfo() {
        return "showEventInfo";
    }

    public boolean isCanSign() {
        boolean result = true;
        Date date = new Date();
        Date signStart = selectedEvent.getSignstart();
        Date signEnd = selectedEvent.getSignend();

        if (signStart != null) {
            result = date.after(signStart);
        }
        if (signEnd != null) {
            result = result && date.before(signEnd);
        }

        return result;
    }

    public boolean isCanCancel() {
        Date unsignEnd = selectedEvent.getUnsignEnd();
        return unsignEnd == null || new Date().before(unsignEnd);
    }

    public boolean isHasJoined() {
        User user = authenticator.getUser();
        return user.hasJoinedEvent(selectedEvent);
    }

    public String mailAttendees() {
        try {
            renderer.render("/mailSystem/sendMail.xhtml");
//            facesMessages.add("Email sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
//            facesMessages.add("Email sending failed: " + e.getMessage());
        }
        return "sendMail";
    }
}
