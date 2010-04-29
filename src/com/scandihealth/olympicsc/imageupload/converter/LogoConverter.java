package com.scandihealth.olympicsc.imageupload.converter;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.imageupload.model.Logo;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


public class LogoConverter implements Converter {
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        DataManager dataManager = new DataManager();
        return dataManager.getLogo(s);
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o != null) {
            Logo location = (Logo) o;
            return location.getName();
        }
        return "";
    }
}
