package com.scandihealth.olympicsc.commandsystem.location;

import com.scandihealth.olympicsc.commandsystem.Command;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.location.model.Location;
import com.scandihealth.olympicsc.location.model.LocationRepository;

public class CreateLocationCommand implements Command {
    private Location location;
    private LocationRepository locationRepository;

    public CreateLocationCommand(Location location, LocationRepository locationRepository) {
        this.location = location;
        this.locationRepository = locationRepository;
    }

    public boolean execute() {
        DataManager dataManager = new DataManager();
        dataManager.saveObject(location);
        return locationRepository.addLocation(location);
    }

    public void undo() {
        DataManager dataManager = new DataManager();
        Location location1 = dataManager.getLocation(location.getIdlocation());
        dataManager.deleteObject(location1);
        locationRepository.removeLocation(location);
    }

    public void redo() {
        execute();
    }
}
