package com.scandihealth.olympicsc.classification.converter;

import com.scandihealth.olympicsc.classification.model.ClassificationType;
import com.scandihealth.olympicsc.data.DataManager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ClassificationTypeConverter implements Converter {
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        DataManager dataManager = new DataManager();
        return dataManager.getClassificationType(s);
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o != null) {
            ClassificationType location = (ClassificationType) o;
            return location.getName();
        }
        return "";
    }
}