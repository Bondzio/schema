package com.scandihealth.olympicsc.teams.model;

import com.scandihealth.olympicsc.data.DataManager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


public class TeamConverter implements Converter {
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        DataManager dataManager = new DataManager();
        Team team = dataManager.getTeam(s);
        return team;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o != null) {
            Team team = (Team) o;
            return team.getName();
        }
        return "";
    }
}