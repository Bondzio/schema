package com.scandihealth.olympicsc.location.model;

import com.scandihealth.olympicsc.data.DataManager;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.util.ArrayList;
import java.util.List;

@Name("locationRepository")
@Scope(ScopeType.SESSION)
public class LocationRepository {
    List<Location> locations;

    public LocationRepository() {
        DataManager dataManager = new DataManager();
        locations = dataManager.getLocations();
        if (locations == null) {
            locations = new ArrayList<Location>();
        }
    }

    public boolean addLocation(Location location) {
        return !locations.contains(location) && locations.add(location);
    }

    public void removeLocation(Location location) {
        locations.remove(location);
    }

    public List<Location> getLocations() {
        return locations;
    }


}
