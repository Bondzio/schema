package com.scandihealth.olympicsc.mailsystem.controller;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.event.model.Event;
import com.scandihealth.olympicsc.mailsystem.model.Mail;
import com.scandihealth.olympicsc.mailsystem.model.MailContent;
import com.scandihealth.olympicsc.user.model.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import java.util.ArrayList;
import java.util.List;

@Name("mailController")
@Scope(ScopeType.SESSION)
public class MailController {

    @In(create = true)
    @Out(value = "mail")
    private Mail mail;

    @In
    Event selectedEvent;

    private boolean tooAll;
    private boolean useGreeting;
    private boolean sendUserDetails;

    private List<User> recipients;

    private List<MailContent> mailContent;


    public String send() {
        mailContent = new ArrayList<MailContent>();
        if (getTooAll()) {
            DataManager dataManager = new DataManager();
            recipients = dataManager.getUsers();
        } else {
            if (selectedEvent != null) {
                recipients = new ArrayList<User>(getSelectedUsers());
            } else {
                recipients = new ArrayList<User>();
                User user = new User();
                user.setMail(mail.getTo());
                recipients.add(user);
            }
        }
        for (User recipient : recipients) {
            MailContent mailContent = new MailContent();
            mailContent.setTo(recipient.getMail());
            mailContent.setName(recipient.getFirstname() + " " + recipient.getLastname());
            mailContent.setSubject(mail.getSubject());
            if (getUseGreeting()) {
                String greeting = "Hej ";
                greeting += recipient.getFirstname();
                mailContent.setGreeting(greeting);

            }
            mailContent.setBody(mail.getBody());


            if (getSendUserDetails()) {
                String bodyText = "";
                bodyText += "Dine detaljer: ";
                bodyText += "Brugernavm: " + recipient.getUserName();
                bodyText += "Password: " + recipient.getPassword();
                mailContent.setUserDetails(bodyText);

            }
            this.mailContent.add(mailContent);
        }

        return "sendMail";
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public void setTooAll(boolean tooAll) {
        this.tooAll = tooAll;
    }

    public boolean getTooAll() {
        return tooAll;
    }

    public void setUseGreeting(boolean useGreeting) {
        this.useGreeting = useGreeting;
    }

    public boolean getUseGreeting() {
        return useGreeting;
    }

    public void setSendUserDetails(boolean sendUserDetails) {
        this.sendUserDetails = sendUserDetails;
    }

    public boolean getSendUserDetails() {
        return sendUserDetails;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public List<MailContent> getMailContent() {
        return mailContent;
    }

    public List<User> getSelectedUsers() {
        List<User> result = new ArrayList<User>();

        if (selectedEvent != null) {
            DataManager dataManager = new DataManager();
            List<User> list = dataManager.getUserForEvent(selectedEvent);
            for (User user : list) {
                result.add(user);
            }
        }
        return result;
    }



}
