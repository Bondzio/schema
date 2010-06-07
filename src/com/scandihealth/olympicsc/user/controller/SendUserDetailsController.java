package com.scandihealth.olympicsc.user.controller;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.user.model.Recipient;
import com.scandihealth.olympicsc.user.model.User;
import com.scandihealth.olympicsc.user.model.UserDetails;
import com.scandihealth.olympicsc.utilities.MessageUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.Renderer;

@Name("sendUserDetailsController")
@Scope(ScopeType.SESSION)
public class SendUserDetailsController {

    @In(create = true)
    UserDetails userDetails;

    Recipient recipient;

    public String sendMail() {
        String result = "";
        if (userDetails != null) {
            DataManager dataManager = new DataManager();
            String userName = userDetails.getUserName();
            if (userName != null && !"".equals(userName)) {
                User user = dataManager.getUser(userName);
                if (user != null) {
                    if (user.getMail() != null && !"".equals(user.getMail())) {
                        result = sendDetails(user);
                    } else {
                        MessageUtils.createMessage("Der var ingen mail adresse tilknyttet denne bruger.");
                    }
                } else {
                    MessageUtils.createMessage("Kunne ikke finde " + userName);
                }
            } else if (userDetails.getEmail() != null && !"".equals(userDetails.getEmail())) {
                User user = dataManager.getUserByEmail(userDetails.getEmail());
                if (user != null) {
                    result = sendDetails(user);
                } else {
                    MessageUtils.createMessage("Kunne ikke finde en bruger med denne email.");
                }
            } else {
                MessageUtils.createMessage("Du skal angive enten brugernavn eller mail.");
            }
        }
        return result;
    }

    private String sendDetails(User user) {
        recipient = new Recipient();
        recipient.setName(user.getFirstname() + " " + user.getLastname());
        recipient.setMail(user.getMail());
        recipient.setUsername(user.getUserName());
        recipient.setPassword(user.getPassword());
        Renderer.instance().render("/mailSystem/sendUserDetailsMail.xhtml");
        MessageUtils.createMessage("Message is sent");
        return "mailSent";
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }
}
