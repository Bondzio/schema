package com.scandihealth.olympicsc.user.model;

import org.jboss.seam.annotations.Name;

@Name("userDetails")
public class UserDetails {
    private String email;
    private String userName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
