package com.scandihealth.olympicsc.commandsystem.user;

import com.scandihealth.olympicsc.commandsystem.Command;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.user.model.User;

public class DeleteUserCommand implements Command {
    private User user;
    private User oldUser;
    private DataManager dataManager;

    public DeleteUserCommand(User user) {
        this.user = user;
        dataManager = new DataManager();
    }

    public boolean execute() {
        this.oldUser = dataManager.getUser(user);
        dataManager.deleteUser(user);
        return true;
    }

    public void undo() {
        if (oldUser != null) {
            dataManager.saveUser(oldUser);
        }
    }

    public void redo() {
        execute();
    }
}