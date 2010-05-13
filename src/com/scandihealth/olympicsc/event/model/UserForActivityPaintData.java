package com.scandihealth.olympicsc.event.model;

import com.scandihealth.olympicsc.activities.model.Activity;
import org.jboss.seam.annotations.Name;

import java.io.Serializable;

@Name("userForActivityPaintData")
public class UserForActivityPaintData implements Serializable{
    private int numberOfUsers;
    private Activity activity;


    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public Activity getActivity() {
        return activity;
    }
}
