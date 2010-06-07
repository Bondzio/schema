package com.scandihealth.olympicsc.location.controller;

import com.scandihealth.olympicsc.commandsystem.CommandController;
import com.scandihealth.olympicsc.commandsystem.location.CreateLocationCommand;
import com.scandihealth.olympicsc.commandsystem.location.DeleteLocationCommand;
import com.scandihealth.olympicsc.commandsystem.location.EditLocationCommand;
import com.scandihealth.olympicsc.location.model.Location;
import com.scandihealth.olympicsc.location.model.LocationRepository;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;

import java.util.List;

@Name("locationController")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class LocationController {
    private LocationRepository locationRepository;

    @In(create = true)
    private CommandController commandController;

    @DataModel
    private List<Location> locationList;

    @In(create = true)
    private Location location;

    @DataModelSelection
    @Out(required = false)
    private Location selectedLocation;

    @Create
    public void init() {
        locationRepository = new LocationRepository();
    }

    @Factory("locationList")
    public void findLocations() {
        if (locationRepository == null) {
            locationRepository = new LocationRepository();
        }
        locationList = locationRepository.getLocations();
    }

    public String createLocation() {
        CreateLocationCommand createLocationCommand = new CreateLocationCommand(location, locationRepository);
        commandController.executeCommand(createLocationCommand);
        return "";
    }

    public String deleteLocation() {
        DeleteLocationCommand deleteLocationCommand = new DeleteLocationCommand(selectedLocation, locationRepository);
        commandController.executeCommand(deleteLocationCommand);
        return "";
    }

    public String updateLocation() {
        return "editLocation";
    }

    public String editLocation() {
        EditLocationCommand editLocationCommand = new EditLocationCommand(selectedLocation, locationRepository);
        commandController.executeCommand(editLocationCommand);
        return "";
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(Location selectedLocation) {
        this.selectedLocation = selectedLocation;
    }
}
