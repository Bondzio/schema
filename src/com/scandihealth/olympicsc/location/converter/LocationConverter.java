package com.scandihealth.olympicsc.location.converter;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.location.model.Location;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class LocationConverter implements Converter {
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        DataManager dataManager = new DataManager();
        return dataManager.getLocation(s);
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o != null) {
            Location location = (Location) o;
            return location.getName();
        }
        return "";
    }
}
