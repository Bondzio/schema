package com.scandihealth.olympicsc.mailsystem.model;

import org.jboss.seam.annotations.Name;

@Name("mailContent")
public class MailContent {
    private String to;
    private String body = "";
    private String subject = "";
    private String name = "";
    private String greeting = "";
    private String userDetails = "";

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

    public String getUserDetails() {
        return userDetails;
    }
}
