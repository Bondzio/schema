package com.scandihealth.olympicsc.lifecycle;

import com.scandihealth.olympicsc.exceptions.MissingUserDataException;
import com.scandihealth.olympicsc.security.Authenticator;
import com.scandihealth.olympicsc.user.User;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Name("com.scandihealth.olympicsc.filters.missingUserDataFilter")
public class MissingUserDataPhaseListener implements PhaseListener {

    @In
    Authenticator authenticator;

    public void afterPhase(PhaseEvent phaseEvent) {

    }

    public void beforePhase(PhaseEvent phaseEvent) {
        // read required attributes
        String[] attributes = readRequiredAttributes();
        // check required attributes against user attributes
        boolean hasAllAttributes = true;
        if (authenticator != null) {
            User user = authenticator.getUser();
            for (String attribute : attributes) {
                try {
                    System.out.println("now checking for " + attribute);
                    Method method = user.getClass().getMethod(attribute);
                    System.out.println("method = " + method.getName());
                    Object o = method.invoke(null);
                    if (o == null) {
                        hasAllAttributes = false;
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("authenticator was null");
        }
        // if missing, throw missing dataexception
        if (!hasAllAttributes) {
            throw new MissingUserDataException();
        }
    }

    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    private String[] readRequiredAttributes() {
        String[] requiredAttributes = {"shirtSize", "employeeId"};
        return requiredAttributes;
    }
}
