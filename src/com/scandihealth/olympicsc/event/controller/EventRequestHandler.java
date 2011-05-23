package com.scandihealth.olympicsc.event.controller;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.event.model.*;
import com.scandihealth.olympicsc.user.model.User;

import java.io.Serializable;

public class EventRequestHandler implements Serializable {
    private final EventController eventController;

    public EventRequestHandler(EventController eventController) {
        this.eventController = eventController;
    }

    void handleRequestUpdate() {
        if (eventController.getEventSelection().isCanRequestVegetarian()) {
            DataManager dataManager = new DataManager();
            EventVegetarianRequest eventVegetarianRequest = new EventVegetarianRequest();
            eventVegetarianRequest.setIduser(eventController.getAuthenticator().getUser().getIduser());
            eventVegetarianRequest.setIdevent(eventController.getEventSelection().getIdevent());
            eventVegetarianRequest.setVegetarian(eventController.getEventSelection().getVegetarianRequest());
            dataManager.saveEventVegetarianRequest(eventVegetarianRequest);
        }

        if (eventController.getEventSelection().isCanRequestPartner()) {
            DataManager dataManager = new DataManager();
            EventPartnerRequest eventPartnerRequest = new EventPartnerRequest();
            eventPartnerRequest.setIduser(eventController.getAuthenticator().getUser().getIduser());
            eventPartnerRequest.setIdevent(eventController.getEventSelection().getIdevent());
            eventPartnerRequest.setPartnerRequest(eventController.getEventSelection().isPartnerRequest());
            dataManager.saveEventPartnerRequest(eventPartnerRequest);
        }

        if (eventController.getEventSelection().isCanRequestChildrenAge()) {
            DataManager dataManager = new DataManager();
            EventChildrenAgeRequest eventChildrenAgeRequest = new EventChildrenAgeRequest();
            eventChildrenAgeRequest.setIduser(eventController.getAuthenticator().getUser().getIduser());
            eventChildrenAgeRequest.setIdevent(eventController.getEventSelection().getIdevent());
            eventChildrenAgeRequest.setChildrenAge(eventController.getEventSelection().getChildrenAgeRequest());
            dataManager.saveEventChildrenAgeRequest(eventChildrenAgeRequest);
        }

        if (eventController.getEventSelection().isCanRequestPasses()) {
            DataManager dataManager = new DataManager();
            EventNumberOfPassesRequest eventNumberOfPassesRequest = new EventNumberOfPassesRequest();
            eventNumberOfPassesRequest.setIduser(eventController.getAuthenticator().getUser().getIduser());
            eventNumberOfPassesRequest.setIdevent(eventController.getEventSelection().getIdevent());
            eventNumberOfPassesRequest.setNumberofpasses(eventController.getEventSelection().getPassesRequest());
            dataManager.saveEventPassesRequest(eventNumberOfPassesRequest);
        }

        if (eventController.getEventSelection().isCanRequestGrownMenus()) {
            DataManager dataManager = new DataManager();
            EventGrownMenuRequest eventGrownMenuRequest = new EventGrownMenuRequest();
            eventGrownMenuRequest.setIduser(eventController.getAuthenticator().getUser().getIduser());
            eventGrownMenuRequest.setIdevent(eventController.getEventSelection().getIdevent());
            eventGrownMenuRequest.setGrownmenus(eventController.getEventSelection().getGrownMenuRequest());
            dataManager.saveEventGrownMenuRequest(eventGrownMenuRequest);
        }


        //
        if (eventController.getEventSelection().isCanRequestChildrenMenus()) {
            DataManager dataManager = new DataManager();
            EventChildrenMenuRequest eventChildrenMenuRequest = new EventChildrenMenuRequest();
            eventChildrenMenuRequest.setIduser(eventController.getAuthenticator().getUser().getIduser());
            eventChildrenMenuRequest.setIdevent(eventController.getEventSelection().getIdevent());
            eventChildrenMenuRequest.setChildrenmenus(eventController.getEventSelection().getChildrenMenuRequest());
            dataManager.saveEventChildrenMenuRequest(eventChildrenMenuRequest);
        }
    }

    void addPartnerRequestToEvent(Event event) {
        User user = eventController.getAuthenticator().getUser();
        DataManager dataManager = new DataManager();
        EventPartnerRequest eventPartnerRequest = dataManager.getEventPartnerRequest(user, event);
        if (eventPartnerRequest != null) {
            event.setPartnerRequest(eventPartnerRequest.isPartnerRequest());
        }
    }

    void addVegetarianRequestToEvent(Event event) {
        User user = eventController.getAuthenticator().getUser();
        DataManager dataManager = new DataManager();
        EventVegetarianRequest eventVegetarianRequest = dataManager.getEventVegetarianRequest(user, event);
        if (eventVegetarianRequest != null) {
            event.setVegetarianRequest(eventVegetarianRequest.getVegetarian());
        }
    }

    void addChildrenAgeRequestToEvent(Event event) {
        User user = eventController.getAuthenticator().getUser();
        DataManager dataManager = new DataManager();
        EventChildrenAgeRequest childrenAgeRequest = dataManager.getEventChildrenAgeRequest(user, event);
        if (childrenAgeRequest != null) {
            event.setChildrenAgeRequest(childrenAgeRequest.getChildrenAge());
        }
    }

    void addPassesRequestToEvent(Event event) {
        User user = eventController.getAuthenticator().getUser();
        DataManager dataManager = new DataManager();
        EventNumberOfPassesRequest eventPassesRequest = dataManager.getEventPassesRequest(user, event);
        if (eventPassesRequest != null) {
            event.setPassesRequest(eventPassesRequest.getNumberofpasses());
        }
    }

    void addGrownMenusRequestToEvent(Event event) {
        User user = eventController.getAuthenticator().getUser();
        DataManager dataManager = new DataManager();
        EventGrownMenuRequest eventGrownMenuRequest = dataManager.getEventGrownMenuRequest(user, event);
        if (eventGrownMenuRequest != null) {
            event.setGrownMenuRequest(eventGrownMenuRequest.getGrownmenus());
        }
    }

    void addChildrenMenusRequestToEvent(Event event) {
        User user = eventController.getAuthenticator().getUser();
        DataManager dataManager = new DataManager();
        EventChildrenMenuRequest eventChildrenMenuRequest = dataManager.getEventChildrenMenuRequest(user, event);
        if (eventChildrenMenuRequest != null) {
            event.setChildrenMenuRequest(eventChildrenMenuRequest.getChildrenmenus());
        }
    }

    public Boolean hasUserPartnerRequest(User user) {
        DataManager dataManager = new DataManager();
        EventPartnerRequest eventPartnerRequest = dataManager.getEventPartnerRequest(user, eventController.getEventSelection());
        return eventPartnerRequest != null && eventPartnerRequest.isPartnerRequest();
    }

    public Integer hasUserVegetarianRequest(User user) {
        DataManager dataManager = new DataManager();
        EventVegetarianRequest vegetarianRequest = dataManager.getEventVegetarianRequest(user, eventController.getEventSelection());
        if (vegetarianRequest != null) {
            return vegetarianRequest.getVegetarian();
        }
        return 0;
    }

    public String hasUserChildrenAgeRequest(User user) {
        DataManager dataManager = new DataManager();
        EventChildrenAgeRequest eventChildrenAgeRequest = dataManager.getEventChildrenAgeRequest(user, eventController.getEventSelection());
        if (eventChildrenAgeRequest != null) {
            return eventChildrenAgeRequest.getChildrenAge();
        }
        return "";
    }

    public Integer hasUserPassesRequest(User user) {
        DataManager dataManager = new DataManager();
        EventNumberOfPassesRequest eventPassesRequest = dataManager.getEventPassesRequest(user, eventController.getEventSelection());
        if (eventPassesRequest != null) {
            return eventPassesRequest.getNumberofpasses();
        }
        return 0;
    }

    public Integer hasUserGrownMenusRequest(User user) {
        DataManager dataManager = new DataManager();
        EventGrownMenuRequest eventGrownMenuRequest = dataManager.getEventGrownMenuRequest(user, eventController.getEventSelection());
        if (eventGrownMenuRequest != null) {
            return eventGrownMenuRequest.getGrownmenus();
        }
        return 0;
    }

    public Integer hasUserChildrenMenusRequest(User user) {
        DataManager dataManager = new DataManager();
        EventChildrenMenuRequest eventChildrenMenuRequest = dataManager.getEventChildrenMenuRequest(user, eventController.getEventSelection());
        if (eventChildrenMenuRequest != null) {
            return eventChildrenMenuRequest.getChildrenmenus();
        }
        return 0;
    }
}