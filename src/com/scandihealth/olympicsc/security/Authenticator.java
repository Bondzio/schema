package com.scandihealth.olympicsc.security;

import com.scandihealth.olympicsc.security.provider.LdapSecurityProvider;
import com.scandihealth.olympicsc.security.provider.SecurityProvider;
import com.scandihealth.olympicsc.security.provider.SimpleSecurityProvider;
import com.scandihealth.olympicsc.user.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import java.io.Serializable;

@Name("authenticator")
@Scope(ScopeType.SESSION)
public class Authenticator implements Serializable {
    @In
    Credentials credentials;
    @In
    Identity identity;

    private User user;

    private String loginAction;


    public boolean authenticate() {
        System.out.println(credentials.getUsername() + " tried logging in");
        SecurityProvider securityProvider = new LdapSecurityProvider();
        user = securityProvider.logon(credentials.getUsername(), credentials.getPassword());
        if (user == null) {
            securityProvider = new SimpleSecurityProvider();
            user = securityProvider.logon(credentials.getUsername(), credentials.getPassword());
        } else {
        }

        if (user == null) {
            loginAction = "notLoggedIn";
            return false;
        }
        loginAction = "loggedIn";

        if (user.isFirstlogin()) {
            loginAction = "createUserDetails";
        }

        if (user.getRoles() != null) {
            for (String role : user.getRoles()) {
                identity.addRole(role);
            }
        }

        return true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String doLogin() {
        identity.login();
        return "doLogin";
    }

    public String login() {
        String s = identity.login();
        System.out.println("s = " + s);
        if (loginAction != null && !"".equals(loginAction)) {
            System.out.println("loginAction = " + loginAction);
            return loginAction;
        }
        return s;
    }

    public String logout() {
        identity.logout();
        return "doLogin";
    }
}
