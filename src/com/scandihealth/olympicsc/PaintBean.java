package com.scandihealth.olympicsc;

import com.scandihealth.olympicsc.activities.model.Activity;

import java.awt.*;


public class PaintBean {

    public void paint(Graphics2D g2, Object obj) {
        System.out.println("paint");
        Activity activity = (Activity) obj;

        g2.setColor(Color.ORANGE);
        g2.fillRect((int) activity.getOffset(), 0, (int) activity.getPercentage(), 20);
    }

}
