package com.scandihealth.olympicsc.classification.converter;

import com.scandihealth.olympicsc.classification.model.Classification;
import com.scandihealth.olympicsc.data.DataManager;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ClassificationConverter implements Converter {
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        DataManager dataManager = new DataManager();
        return dataManager.getClassification(s);
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o != null) {
            Classification classification = (Classification) o;
            return classification.getName();
        }
        return "";
    }
}