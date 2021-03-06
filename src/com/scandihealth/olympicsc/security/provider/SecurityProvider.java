package com.scandihealth.olympicsc.security.provider;

import com.scandihealth.olympicsc.user.model.User;

public interface SecurityProvider {

    public User logon(User user);

    public User logon(String userName, String password);
}
