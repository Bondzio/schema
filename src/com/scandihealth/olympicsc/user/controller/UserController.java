package com.scandihealth.olympicsc.user.controller;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.user.User;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

import javax.faces.component.UIInput;

@Name("userController")
public class UserController {

    @In(value = "user", required = true)
    @Out(value = "user")
    private User user;
    private UIInput shirtSizeControl;

    public UserController() {
    }

    private void validateShirtSizeControl() {
        if (shirtSizeControl != null) {
            Object value = shirtSizeControl.getValue();
            if (value == null) {
                if (user != null) {
                    if (!user.getShirtsize().equals("")) {
                        shirtSizeControl.setValid(true);
                    } else {
                        shirtSizeControl.setValid(false);
                    }
                } else {
                    shirtSizeControl.setValid(false);
                }
            } else if (value instanceof String) {
                String value1 = (String) value;
                if (value1.equals("")) {
                    shirtSizeControl.setValid(false);
                }
            }
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String createUser() {
        DataManager dataManager = new DataManager();
        dataManager.saveUser(user);
        return null;
    }

    public String updateUser() {
        DataManager dataManager = new DataManager();
        user.setFirstlogin(false);
        dataManager.saveUser(user);
        return "startpage";
    }

    public void setShirtSizeControl(UIInput shirtSizeControl) {
        this.shirtSizeControl = shirtSizeControl;
        validateShirtSizeControl();
    }

    public UIInput getShirtSizeControl() {
        return shirtSizeControl;
    }
}
