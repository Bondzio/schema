package com.scandihealth.olympicsc.commandsystem.user;

import com.scandihealth.olympicsc.commandsystem.Command;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.user.User;

public class SaveUserCommand implements Command {
    private User user;
    private User oldUser;
    private DataManager dataManager;

    public SaveUserCommand(User user) {
        this.user = user;
        dataManager = new DataManager();
    }

    public boolean execute() {
        this.oldUser = dataManager.getUser(user);
        return dataManager.saveUser(user);
    }

    public void undo() {
        dataManager.deleteObject(user);
        if (oldUser != null) {
            dataManager.saveUser(oldUser);
        }
    }

    public void redo() {
        execute();
    }
}
