package com.scandihealth.olympicsc.activities.model;

import org.jboss.seam.annotations.Name;

import java.awt.*;


@Name("gantPaintBean")
public class PaintBean {

    public void paint(Graphics2D g2, Object obj) {
        Activity activity = (Activity) obj;

        g2.setColor(Color.ORANGE);
        g2.fillRect((int) activity.getOffset(), 0, (int) activity.getPercentage(), 20);
    }

}
