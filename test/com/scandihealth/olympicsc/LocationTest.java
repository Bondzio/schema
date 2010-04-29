package com.scandihealth.olympicsc;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.location.model.Location;
import junit.framework.*;

public class LocationTest extends TestCase {

    DataManager dataManager;

    @Override
    protected void setUp() throws Exception {
        dataManager = new DataManager();
    }

    public void tearDown() {
        Location location1 = dataManager.getLocation("testLocation");
        if (location1 != null) {
            dataManager.deleteObject(location1);
        }
        dataManager = null;
    }

    public void testCreateLocation() {
        Location location = new Location();
        location.setName("testLocation");

        assertEquals(0, location.getIdlocation());
        dataManager.saveObject(location);

        Location location1 = dataManager.getLocation("testLocation");
        assertNotNull(location1);

        assertEquals("testLocation", location1.getName());
        assertNotSame(0, location1.getIdlocation());
    }

    public void testDeleteLocation() {
        testCreateLocation();

        Location location = dataManager.getLocation("testLocation");
        dataManager.deleteObject(location);

        Location location1 = dataManager.getLocation("testLocation");
        assertNull(location1);
    }

}