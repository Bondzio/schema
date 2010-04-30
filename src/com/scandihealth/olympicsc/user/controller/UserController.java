package com.scandihealth.olympicsc.user.controller;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.user.User;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;

import javax.faces.component.UIInput;
import java.util.ArrayList;
import java.util.List;

@Name("userController")
public class UserController {

    @In(value = "user", required = true)
    @Out(value = "user")
    private User user;
    private UIInput shirtSizeControl;


    @DataModelSelection
    private User selectedUser;

    @DataModel
    private List<User> userList;

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
}
