package com.scandihealth.olympicsc.mailsystem.model;

import com.scandihealth.olympicsc.user.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.util.ArrayList;
import java.util.List;


@Name("mail")
@Scope(ScopeType.CONVERSATION)
public class Mail {
    private String sender;
    private String to;
    private String subject;
    private String body;
    private String beforeName;
    private String afterName;
    private String greeting;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setBeforeName(String beforeName) {
        this.beforeName = beforeName;
    }

    public Object getBeforeName() {
        return beforeName;
    }

    public void setAfterName(String afterName) {
        this.afterName = afterName;
    }

    public String getAfterName() {
        return afterName;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        String s = getBeforeName() + " Navn " + getAfterName();
        System.out.println("s = " + s);
        return s;
    }
}
