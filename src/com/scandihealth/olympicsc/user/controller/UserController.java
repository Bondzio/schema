package com.scandihealth.olympicsc.user.controller;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.user.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@Name("userController")
@Scope(ScopeType.SESSION)
public class UserController {

    @In(value = "user", required = true)
    @Out(value = "user")
    private User user;
    private UIInput shirtSizeControl;


    @DataModelSelection
    private User selectedUser;

    @DataModel
    private List<User> userList;
    private boolean personaleForening = false;

    public UserController() {
    }

    @Factory("userList")
    public void findUsers() {
        userList = new ArrayList<User>();
        DataManager dataManager = new DataManager();
        userList = dataManager.getUsers();
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

    public void selectAllPersonel() {
        System.out.println("setting personel " + personaleForening);
        personaleForening = !personaleForening;
        for (User user1 : userList) {
            user1.setPersonaleForening(personaleForening);
        }
    }

    public String saveAllPersonel() {
        DataManager dataManager = new DataManager();
        for (User user1 : userList) {
            dataManager.saveUser(user1);
        }
        FacesContext.getCurrentInstance().addMessage("Test", new FacesMessage("Brugere gemt"));
        return "";
    }
}
