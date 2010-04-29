package com.scandihealth.olympicsc.issues;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Created by IntelliJ IDEA.
 * User: Martin Hylleberg
 * Date: 07-03-2010
 * Time: 14:04:13
 * To change this template use File | Settings | File Templates.
 */
public class EstimateConverter implements Converter {
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        int result;
        try {
            result = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
        return result;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String result = "";
        if (o != null) {
            Integer priority = (Integer) o;
            switch (priority) {
                case 0:
                    result = "Unknown";
                    break;
                case 1:
                    result = "Severe";
                    break;
                case 2:
                    result = "High";
                    break;
                case 3:
                    result = "Medium";
                    break;
                case 4:
                    result = "Low";
                    break;
            }
        }
        return result;
    }
}