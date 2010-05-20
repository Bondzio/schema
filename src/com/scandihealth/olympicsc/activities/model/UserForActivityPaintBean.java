package com.scandihealth.olympicsc.activities.model;

import com.scandihealth.olympicsc.event.model.UserForActivityPaintData;
import org.jboss.seam.annotations.Name;

import java.awt.*;
import java.io.Serializable;

@Name("userForActivityPaintBean")
public class UserForActivityPaintBean implements Serializable {
    public UserForActivityPaintBean() {
    }

    public void paint(Graphics2D g2, Object obj) {
        UserForActivityPaintData userForActivityPaintData = (UserForActivityPaintData) obj;
        if (userForActivityPaintData != null) {
            Activity activity = userForActivityPaintData.getActivity();
            int minimumplayers = activity.getMinimumplayers();
            int numberOfUsers = userForActivityPaintData.getNumberOfUsers();
            if (numberOfUsers < minimumplayers) {
                g2.setColor(Color.RED);
                g2.fillRect(0, -4, numberOfUsers * 8, 20);
                g2.setColor(Color.WHITE);
            } else {
                g2.setColor(Color.GREEN);
                g2.fillRect(0, -4, numberOfUsers * 8, 20);
                g2.setColor(Color.BLACK);
            }

            g2.drawString(activity.getName() + "(" + numberOfUsers + ")", 0, 14);
        } else {
            g2.drawString("null??", 0, 14);
        }
    }
}
