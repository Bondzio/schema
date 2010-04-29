package com.scandihealth.olympicsc;

import com.scandihealth.olympicsc.commandsystem.CommandController;
import com.scandihealth.olympicsc.commandsystem.location.CreateLocationCommand;
import com.scandihealth.olympicsc.commandsystem.location.DeleteLocationCommand;
import com.scandihealth.olympicsc.commandsystem.user.SaveUserCommand;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.location.model.Location;
import com.scandihealth.olympicsc.location.model.LocationRepository;
import com.scandihealth.olympicsc.user.User;
import junit.framework.TestCase;

import java.util.List;

public class CommandSystemTest extends TestCase {
    private CommandController commandController;
    private LocationRepository locationRepository;
    private DataManager dataManager;

    public void setUp() {
        dataManager = new DataManager();
        commandController = new CommandController();
        locationRepository = new LocationRepository();
    }

    public void tearDown() {
        List<Location> locationList = dataManager.getLocations();
        for (Location location : locationList) {
            if (location.getName().equals("testLocation")) {
                dataManager.deleteObject(location);
            }
        }

        List<User> users = dataManager.getUsers();
        for (User user : users) {
            if (user.getUserName().equals("testUser")) {
                dataManager.deleteObject(user);
            }
        }
        dataManager = null;
        commandController = null;
        locationRepository = null;
    }


    public void testCreateLocationCommand() {
        Location location = new Location();
        location.setName("testLocation");

        CreateLocationCommand createLocationCommand = new CreateLocationCommand(location, locationRepository);
        boolean success = commandController.executeCommand(createLocationCommand);
        assertTrue(success);

        Location location1 = dataManager.getLocation("testLocation");
        assertNotNull(location1);
        assertEquals("testLocation", location1.getName());
    }

    public void testDeleteLocationCommand() {
        testCreateLocationCommand();

        Location location1 = dataManager.getLocation("testLocation");
        DeleteLocationCommand deleteLocationCommand = new DeleteLocationCommand(location1, locationRepository);
        boolean success = commandController.executeCommand(deleteLocationCommand);
        assertTrue(success);
        Location location2 = dataManager.getLocation("testLocation");
        assertNull(location2);
    }

    public void testUndoCreateLocationCommand() {
        testCreateLocationCommand();
        commandController.undo();

        Location location1 = dataManager.getLocation("testLocation");
        assertNull(location1);
    }

    public void testRedoCreateLoctionCommand() {
        testUndoCreateLocationCommand();
        commandController.redo();
        Location location1 = dataManager.getLocation("testLocation");
        assertNotNull(location1);
        assertEquals("testLocation", location1.getName());
    }

    public void testUndoDeleteLocationCommand() {
        testDeleteLocationCommand();
        commandController.undo();
        Location location1 = dataManager.getLocation("testLocation");
        assertNotNull(location1);
        assertEquals("testLocation", location1.getName());

    }

    public void testRedoDeleteLocationCommand() {
        testUndoDeleteLocationCommand();
        commandController.redo();
        Location location1 = dataManager.getLocation("testLocation");
        assertNull(location1);
    }

    public void testSaveUserCommand() {
        User user = new User();
        user.setUserName("testUser");
        user.setFirstname("Test");
        user.setLastname("User");
        user.setShirtsize("");
        SaveUserCommand saveUserCommand = new SaveUserCommand(user);
        commandController.executeCommand(saveUserCommand);

        User user1 = dataManager.getUser("testUser", "");
        assertNotNull(user1);
        assertEquals("Test", user1.getFirstname());
        assertEquals("User", user1.getLastname());
    }

    public void testSaveAndUpdateUserCommand() {
        User user = new User();
        user.setUserName("testUser");
        user.setFirstname("Test");
        user.setLastname("User");
        user.setShirtsize("");
        SaveUserCommand saveUserCommand = new SaveUserCommand(user);
        commandController.executeCommand(saveUserCommand);

        User user1 = dataManager.getUser("testUser", "");
        assertNotNull(user1);
        assertEquals("Test", user1.getFirstname());
        assertEquals("User", user1.getLastname());

        user1.setPhone("11223344");
        SaveUserCommand saveUserCommand1 = new SaveUserCommand(user1);
        commandController.executeCommand(saveUserCommand1);

        User user2 = dataManager.getUser("testUser", "");
        assertNotNull(user2);
        assertEquals("11223344", user2.getPhone());
        assertEquals("Test", user2.getFirstname());
        assertEquals("User", user2.getLastname());
    }

    public void testSaveUserUsingDataManager() {
        List<User> users = dataManager.getUsers();
        assertEquals(2, users.size());
        User user = new User();
        user.setUserName("testUser");
        user.setFirstname("Test");
        user.setLastname("User");
        user.setShirtsize("");
        dataManager.saveUser(user);
        List<User> users1 = dataManager.getUsers();
        assertEquals(3, users1.size());

        User user2 = dataManager.getUser("testUser", "");
        assertNotNull(user2);
        dataManager.saveUser(user2);
        List<User> users2 = dataManager.getUsers();
        assertEquals(3, users2.size());
    }

    public void testUndoSaveUserCommand() {
        testSaveUserCommand();
        commandController.undo();
        User user1 = dataManager.getUser("testUser", "");
        assertNull(user1);
    }

    public void testUndoSaveUpdatedUserCommand() {
        testSaveAndUpdateUserCommand();
        commandController.undo();
        User user = dataManager.getUser("testUser", "");
        assertNotNull(user);
        assertEquals("Test", user.getFirstname());
        assertEquals("User", user.getLastname());
        assertNull(user.getPhone());
    }

    public void testRedoSaveUserCommand() {
        testUndoSaveUserCommand();
        commandController.redo();
        User user1 = dataManager.getUser("testUser", "");
        assertNotNull(user1);
        assertEquals("Test", user1.getFirstname());
        assertEquals("User", user1.getLastname());
    }
}
