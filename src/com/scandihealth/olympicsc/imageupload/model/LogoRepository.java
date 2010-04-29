package com.scandihealth.olympicsc.imageupload.model;

import com.scandihealth.olympicsc.data.DataManager;

import java.util.ArrayList;
import java.util.List;

public class LogoRepository {
    List<Logo> logoList;

    public LogoRepository() {
        DataManager dataManager = new DataManager();
        logoList = dataManager.getLogos();
        if (logoList == null) {
            logoList = new ArrayList<Logo>();
        }
    }

    public boolean addLogo(Logo logo) {
        return !logoList.contains(logo) && logoList.add(logo);
    }

    public void removeLocation(Logo logo) {
        logoList.remove(logo);
    }

    public List<Logo> getLogoList() {
        return logoList;
    }

}
