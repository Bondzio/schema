package com.scandihealth.olympicsc.utilities;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageUtils {

    public static void createMessage(String message) {
        createMessage(message, "");
    }

    public static void createMessage(String message, String componentId) {
        createMessage(message, componentId, FacesMessage.SEVERITY_INFO);
    }

    public static void createMessage(String message, String componentId, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage(componentId, new FacesMessage(severity, message, ""));
    }
}
