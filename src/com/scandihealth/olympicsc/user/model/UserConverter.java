package com.scandihealth.olympicsc.user.model;

import com.scandihealth.olympicsc.data.DataManager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


public class UserConverter implements Converter {
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        DataManager dataManager = new DataManager();
        int start = s.indexOf("(");
        int end = s.indexOf(")");
        User user = null;
        if (s.length() > 0 && s.length() > start && s.length() - 1 <= end) {
            String s1 = s.substring(start + 1, end);
            user = dataManager.getUser(s1);
        }
        return user;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o != null) {
            User user = (User) o;
            return user.getFirstname() + " " + user.getLastname() + "(" + user.getUserName() + ")";
        }
        return "";
    }
}