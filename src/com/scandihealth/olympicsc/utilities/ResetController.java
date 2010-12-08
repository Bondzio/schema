package com.scandihealth.olympicsc.utilities;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import java.util.Iterator;

@Name("resetController")
@Scope(ScopeType.EVENT)
public class ResetController {

    private UIInput uiInput;

    public UIInput getUiInput() {
        return uiInput;
    }

    public void setUiInput(UIInput uiInput) {
        this.uiInput = uiInput;
    }

    public void makeInvalid() {
        FacesContext context = FacesContext.getCurrentInstance();
        MessageUtils.createMessage("test",myComponent.getClientId(context), FacesMessage.SEVERITY_ERROR);
//        FacesContext.getCurrentInstance().addMessage("testComp1", new FacesMessage("test"));
//        myComponent.setValid(false);
    }

    public void reset() {
        if (myComponent != null) {
            myComponent.getChildren().clear();
//            myComponent.setValue("");
        }

        if (uiInput != null) {
            uiInput.getChildren().clear();
            uiInput.setValue("");
        }
    }

    private UIComponent myComponent;

    public UIComponent getMyComponent() {
        return myComponent;
    }

    public void setMyComponent(UIComponent myComponent) {
        this.myComponent = myComponent;
    }

    public String getErrorStyle() {
        FacesContext context = FacesContext.getCurrentInstance();
        String clientId = myComponent.getClientId(context);
        System.out.println("clientId = " + clientId);
        Iterator<FacesMessage> messages = context.getMessages(clientId);

        while (messages.hasNext()) {
            System.out.println("had a message");
            if (messages.next().getSeverity().compareTo(FacesMessage.SEVERITY_ERROR) >= 0) {
                System.out.println("setting to error color");
                return "background-color: red";
            }
        }
        return null;
    }
}
