package com.scandihealth.olympicsc.mailsystem.controller;

import com.scandihealth.olympicsc.data.DataManager;
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
@Scope(ScopeType.CONVERSATION)
public class MailController {

    @In(create = true)
    @Out(value = "mail")
    private Mail mail;


    private Boolean tooAll;
    private Boolean useGreeting;
    private Boolean sendUserDetails;

    private List<User> recipients;

    private List<MailContent> mailContent;

    public String send() {
        mailContent = new ArrayList<MailContent>();
        if (getTooAll()) {
            DataManager dataManager = new DataManager();
            recipients = dataManager.getUsers();
        } else {
            recipients = new ArrayList<User>();
            User user = new User();
            user.setMail(mail.getTo());
            recipients.add(user);
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

    public void setTooAll(Boolean tooAll) {
        this.tooAll = tooAll;
    }

    public Boolean getTooAll() {
        return tooAll;
    }

    public void setUseGreeting(Boolean useGreeting) {
        this.useGreeting = useGreeting;
    }

    public Boolean getUseGreeting() {
        return useGreeting;
    }

    public void setSendUserDetails(Boolean sendUserDetails) {
        this.sendUserDetails = sendUserDetails;
    }

    public Boolean getSendUserDetails() {
        return sendUserDetails;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public List<MailContent> getMailContent() {
        return mailContent;
    }
}
