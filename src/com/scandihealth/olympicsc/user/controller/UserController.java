package com.scandihealth.olympicsc.user.controller;

import com.scandihealth.olympicsc.commandsystem.CommandController;
import com.scandihealth.olympicsc.commandsystem.user.DeleteUserCommand;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.security.Authenticator;
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
@Scope(ScopeType.CONVERSATION)
public class UserController {

    @In(value = "user", required = true)
    @Out(value = "user")
    private User user;
    private UIInput shirtSizeControl;

    @In(create = true)
    private CommandController commandController;

    @In
    Authenticator authenticator;

    @DataModelSelection
    private User selectedUser;

    @DataModel
    private List<User> userList;
    private boolean personaleForening = false;


    private User newUser;

    @Create
    public void init() {
        if (newUser == null) {
            newUser = new User();
        }
    }

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

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public String createUser() {
        newUser.setFirstlogin(true);
        DataManager dataManager = new DataManager();
        dataManager.saveUser(newUser);
        return "";
    }

    public String updateUser() {
        User user1 = authenticator.getUser();
        DataManager dataManager = new DataManager();
        user1.setFirstlogin(false);

        dataManager.saveUser(user1);
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
        FacesContext.getCurrentInstance().addMessage("userList", new FacesMessage("Brugerne er blever opdateret."));
        return "";
    }

    public String deleteUser() {
        DeleteUserCommand deleteUserCommand = new DeleteUserCommand(selectedUser);
        commandController.executeCommand(deleteUserCommand);
        findUsers();
        return "";
    }
}
