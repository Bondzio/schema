package com.scandihealth.olympicsc.issues;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.util.ArrayList;
import java.util.List;

@Name("issueRepository")
@Scope(ScopeType.SESSION)
public class IssueRepository {

    private List<Issue> issues;

    public IssueRepository() {
        issues = new ArrayList<Issue>();

        Issue issue1 = new Issue();
        issue1.setName("Logout clears data");
        issue1.setPriority(Issue.MEDIUM);
        issue1.setEstimate(Issue.MEDIUM);
        issue1.setArea("Security");
        issue1.setDescription("Logging out clears all data. This issue should solve itself once the backend is implemented");
        issue1.setIssueNumber(1);
        issues.add(issue1);

        Issue issue2 = new Issue();
        issue2.setName("Add multiple locations to an event");
        issue2.setPriority(Issue.HIGH);
        issue2.setEstimate(Issue.SEVERE);
        issue2.setArea("Event");
        issue2.setDescription("An event should be able to take place in several locations, such as OlympiCSC does.");
        issue2.setIssueNumber(2);
        issues.add(issue2);

        Issue issue3 = new Issue();
        issue3.setName("Multiple toolbars on Create Location page");
        issue3.setPriority(Issue.MEDIUM);
        issue3.setEstimate(Issue.LOW);
        issue3.setArea("Location");
        issue3.setIssueNumber(3);
        issues.add(issue3);

        Issue issue4 = new Issue();
        issue4.setName("Theme support not working");
        issue4.setPriority(Issue.LOW);
        issue4.setEstimate(Issue.UNKNOWN);
        issue4.setArea("General");
        issue4.setDescription("Theme support is not needed, but just a nice way to show off :)");
        issue4.setIssueNumber(4);
        issues.add(issue4);

        Issue issue5 = new Issue();
        issue5.setName("Undo/redo not implemented");
        issue5.setPriority(Issue.LOW);
        issue5.setEstimate(Issue.LOW);
        issue5.setArea("General");
        issue5.setDescription("Just need to add it to the toolbar and implement keyboard shortcuts in the mastertemplate");
        issue5.setIssueNumber(5);
        issues.add(issue5);

        Issue issue6 = new Issue();
        issue6.setName("Add user details page");
        issue6.setPriority(Issue.HIGH);
        issue6.setEstimate(Issue.MEDIUM);
        issue6.setArea("User");
        issue6.setDescription("Need to figure out which details must be added manually and what details we can get from LDAP");
        issue6.setIssueNumber(6);
        issues.add(issue6);

        Issue issue7 = new Issue();
        issue7.setName("Missing edit activity page");
        issue7.setPriority(Issue.HIGH);
        issue7.setEstimate(Issue.MEDIUM);
        issue7.setArea("Activities");
        issue7.setIssueNumber(7);
        issues.add(issue7);

        Issue issue8 = new Issue();
        issue8.setName("Missing edit location page");
        issue8.setPriority(Issue.HIGH);
        issue8.setEstimate(Issue.MEDIUM);
        issue8.setArea("Location");
        issue8.setIssueNumber(8);
        issues.add(issue8);

        Issue issue9 = new Issue();
        issue9.setName("Missing edit event page");
        issue9.setPriority(Issue.HIGH);
        issue9.setEstimate(Issue.MEDIUM);
        issue9.setArea("Event");
        issue9.setIssueNumber(9);
        issues.add(issue9);

        Issue issue10 = new Issue();
        issue10.setName("Missing group functionality");
        issue10.setPriority(Issue.MEDIUM);
        issue10.setEstimate(Issue.MEDIUM);
        issue10.setArea("Group");
        issue10.setIssueNumber(10);
        issues.add(issue10);

        Issue issue11 = new Issue();
        issue11.setName("Prettify Login page");
        issue11.setPriority(Issue.MEDIUM);
        issue11.setEstimate(Issue.MEDIUM);
        issue11.setArea("Security");
        issue11.setIssueNumber(11);
        issues.add(issue11);

        Issue issue12 = new Issue();
        issue12.setName("Save/load not working");
        issue12.setPriority(Issue.MEDIUM);
        issue12.setEstimate(Issue.UNKNOWN);
        issue12.setArea("Backend");
        issue12.setIssueNumber(12);
        issues.add(issue12);

        Issue issue13 = new Issue();
        issue13.setName("Export to excel");
        issue13.setPriority(Issue.MEDIUM);
        issue13.setEstimate(Issue.UNKNOWN);
        issue13.setArea("Backend");
        issue13.setIssueNumber(13);
        issues.add(issue13);

        Issue issue14 = new Issue();
        issue14.setName("Missing frontpage");
        issue14.setPriority(Issue.LOW);
        issue14.setEstimate(Issue.MEDIUM);
        issue14.setArea("General");
        issue14.setDescription("Only a simple frontpage has currently been implemented. Create a frontpage listing the different events that will take place over the year. This could be a collection of event boxes with descriptions, dates and a \"pretty\" event logo");
        issue14.setIssueNumber(14);
        issues.add(issue14);

        Issue issue15 = new Issue();
        issue15.setName("Pages are not translated");
        issue15.setPriority(Issue.LOW);
        issue15.setEstimate(Issue.LOW);
        issue15.setArea("General");
        issue15.setDescription("Should not be hard to implement. Just need the resource files and a language selection in the toolbar.");
        issue15.setIssueNumber(15);
        issues.add(issue15);

        Issue issue16 = new Issue();
        issue16.setName("Join event");
        issue16.setPriority(Issue.LOW);
        issue16.setEstimate(Issue.UNKNOWN);
        issue16.setArea("Event");
        issue16.setDescription("Need a join event page, so the system can be used for other events than just OlympiCSC. ");
        issue16.setIssueNumber(16);
        issues.add(issue16);

        Issue issue17 = new Issue();
        issue17.setName("Event types");
        issue17.setPriority(Issue.LOW);
        issue17.setEstimate(Issue.UNKNOWN);
        issue17.setArea("Event");
        issue17.setDescription("Might need event types to differentiate between different types of events. OlympiCSC has multiple activities, and as such needs activities to work. Whiskeytasting consists of 1 activity and therefore do not need activities.");
        issue17.setIssueNumber(17);
        issues.add(issue17);

        Issue issue18 = new Issue();
        issue18.setName("Join activity with preferred partner");
        issue18.setPriority(Issue.HIGH);
        issue18.setEstimate(Issue.UNKNOWN);
        issue18.setArea("Activities");
        issue18.setDescription("Players needs to be able to join with partners");
        issue18.setIssueNumber(18);
        issues.add(issue18);

        Issue issue19 = new Issue();
        issue19.setName("No resources implemented");
        issue19.setPriority(Issue.MEDIUM);
        issue19.setEstimate(Issue.LOW);
        issue19.setArea("Resources");
        issue19.setDescription("Players needs to be able to join with partners");
        issue19.setIssueNumber(19);
        issues.add(issue19);

        Issue issue20 = new Issue();
        issue20.setName("Location based resources");
        issue20.setPriority(Issue.MEDIUM);
        issue20.setEstimate(Issue.LOW);
        issue20.setArea("Location");
        issue20.setDescription("Adding a resource to a location, with the amount available");
        issue20.setIssueNumber(20);
        issues.add(issue20);

        Issue issue21 = new Issue();
        issue21.setName("Selecting a person");
        issue21.setPriority(Issue.MEDIUM);
        issue21.setEstimate(Issue.LOW);
        issue21.setArea("Person");
        issue21.setDescription("How do we select person, for instance when choosing a responsible party for an activity. (simple type, drop down, etc).");
        issue21.setIssueNumber(21);
        issues.add(issue21);

        Issue issue22 = new Issue();
        issue22.setName("Selecting multiple persons");
        issue22.setPriority(Issue.MEDIUM);
        issue22.setEstimate(Issue.LOW);
        issue22.setArea("Person");
        issue22.setDescription("How do we select multiple persons? issue 21 should be decided upon before implementing this.");
        issue22.setIssueNumber(22);
        issues.add(issue22);

        Issue issue23 = new Issue();
        issue23.setName("Create about page");
        issue23.setPriority(Issue.LOW);
        issue23.setEstimate(Issue.LOW);
        issue23.setArea("About");
        issue23.setIssueNumber(23);
        issues.add(issue23);

        Issue issue24 = new Issue();
        issue24.setName("connect activities with events");
        issue24.setPriority(Issue.HIGH);
        issue24.setEstimate(Issue.HIGH);
        issue24.setDescription("An activity should be connected to an event, user details page should reflect this. Creating activities should be a part of creating an event. Start and End date of an activity should be within the start and end date of an event.");
        issue24.setArea("Event");
        issue24.setIssueNumber(24);
        issues.add(issue24);
    }

    public List<Issue> getIssues() {
        return issues;
    }
}
