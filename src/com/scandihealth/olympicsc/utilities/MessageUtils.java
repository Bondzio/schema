package com.scandihealth.olympicsc.utilities;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageUtils {

    public static void createMessage(String message) {
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(message));
    }

    public static void createMessage(String message, String componentId) {
        FacesContext.getCurrentInstance().addMessage(componentId, new FacesMessage(message));
    }

    public static void createMessage(String message, String componentId, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage(componentId, new FacesMessage(severity, message, ""));
    }
}
