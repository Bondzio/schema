package com.scandihealth.olympicsc.security.provider;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.user.User;

public class SimpleSecurityProvider implements SecurityProvider {
    public User logon(User user) {
        DataManager dataManager = new DataManager();
        return dataManager.getUser(user.getUserName(), user.getPassword());
    }

    public User logon(String userName, String password) {
        DataManager dataManager = new DataManager();
        return dataManager.getUser(userName, password);
    }
}
