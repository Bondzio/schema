/*
 * Copyright (c) 2010. Olympicsc
 */

package com.scandihealth.olympicsc.commandsystem.location;

import com.scandihealth.olympicsc.commandsystem.Command;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.location.model.Location;
import com.scandihealth.olympicsc.location.model.LocationRepository;

public class DeleteLocationCommand implements Command {
    private Location location;
    private LocationRepository locationRepository;
    private DataManager dataManager;

    public DeleteLocationCommand(Location location, LocationRepository locationRepository) {
        this.location = location;
        this.locationRepository = locationRepository;
    }

    public boolean execute() {
        dataManager = new DataManager();
        Location location1 = dataManager.getLocation(location);
        dataManager.deleteObject(location1);
        locationRepository.removeLocation(location);
        return true;
    }

    public void undo() {
        locationRepository.addLocation(location);
        dataManager.saveObject(location);
    }

    public void redo() {
        execute();
    }
}