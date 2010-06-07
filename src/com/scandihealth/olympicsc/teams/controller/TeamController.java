package com.scandihealth.olympicsc.teams.controller;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.teams.model.Team;
import com.scandihealth.olympicsc.teams.model.TeamUserSelection;
import com.scandihealth.olympicsc.user.model.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Name("teamController")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class TeamController implements Serializable {

    @In(create = true)
    Team team;

    @DataModel
    List<Team> teamList;

    @DataModelSelection
    private Team selectedTeam;

    private List<User> usersForTeam;

    public String createTeam() {
        if (team != null && team.getName() != null && !"".equals(team.getName())) {
            DataManager dataManager = new DataManager();
            dataManager.saveObject(team);
        }
        return "";
    }

    @Factory("teamList")
    public void findTeams() {
        DataManager dataManager = new DataManager();
        teamList = dataManager.getTeams();
    }


    public List<User> usersForTeam(Team team) {
        DataManager dataManager = new DataManager();
        usersForTeam = dataManager.getUsersForTeam(team);
        return usersForTeam;
    }

    public String updateTeam() {
        if (selectedTeam != null) {
            DataManager dataManager = new DataManager();
            Team team1 = dataManager.getTeam(selectedTeam);
            List<User> result = dataManager.getUsersForTeam(team1);

            for (User user : result) {
                if (!usersForTeam.contains(user)) {
                    TeamUserSelection teamUserSelection = dataManager.getTeamUserSelection(user);
                    dataManager.deleteObject(teamUserSelection);
                }
            }
            for (User user : usersForTeam) {
                if (!result.contains(user)) {
                    TeamUserSelection teamUserSelection = new TeamUserSelection();
                    teamUserSelection.setIdteam(selectedTeam.getId());
                    teamUserSelection.setIduser(user.getIduser());
                    dataManager.saveObject(teamUserSelection);
                }
            }
        } else {
            System.out.println("test");
        }

        return "";
    }

    public String deleteTeamComposition() {
        DataManager dataManager = new DataManager();
        List<User> userList1 = dataManager.getUsers();
        for (User user : userList1) {
            TeamUserSelection teamUserSelection = dataManager.getTeamUserSelection(user);
            if (teamUserSelection != null) {
                dataManager.deleteObject(teamUserSelection);
            }
        }
        return "";
    }

    public static void main(String[] args) {
        new TeamController().calculateTShirts();
    }

    public void calculateTShirts() {
        findTeams();
        DataManager dataManager = new DataManager();
        Map<String, Map<String, Integer>> shirtMap = new HashMap<String, Map<String, Integer>>();
        for (Team team1 : teamList) {
            List<User> usersForTeam = dataManager.getUsersForTeam(team1);

            Map<String, Integer> teamShirts = shirtMap.get(team1.getName());
            if (teamShirts == null) {
                teamShirts = new HashMap<String, Integer>();
            }
            for (User user : usersForTeam) {
                String shirtSize = user.getShirtsize().toUpperCase().trim();
                shirtSize = convertShirtSizes(shirtSize);
                Integer shirtCount = 1;
                if (teamShirts.containsKey(shirtSize)) {
                    shirtCount = teamShirts.get(shirtSize) + 1;
                }

                teamShirts.put(shirtSize, shirtCount);

            }
            shirtMap.put(team1.getName(), teamShirts);
        }


        for (String s : shirtMap.keySet()) {
            System.out.println("Team " + s);
            Map<String, Integer> sizeCountMap = shirtMap.get(s);
            for (String sizes : sizeCountMap.keySet()) {
                System.out.println("Number of " + sizes + ": " + sizeCountMap.get(sizes));
            }
        }
    }

    private String convertShirtSizes(String shirtSize) {
        if ("X-LARGE".equals(shirtSize)) {
            shirtSize = "XL";
        }
        if ("LARGE".equals(shirtSize)) {
            shirtSize = "L";
        }
        if ("MEDIUM".equals(shirtSize)) {
            shirtSize = "M";
        }
        if ("SMALL".equals(shirtSize)) {
            shirtSize = "S";
        }
        if ("14 ÅR".equals(shirtSize)) {
            shirtSize = "14";
        }
        if ("STR. 14 ÅR".equals(shirtSize)) {
            shirtSize = "14";
        }
        if ("39".equals(shirtSize)) {
            shirtSize = "M";
        }
        if ("39 / MEDIUM".equals(shirtSize)) {
            shirtSize = "M";
        }
        if ("42 (M/L)".equals(shirtSize)) {
            shirtSize = "L";
        }
        if ("42".equals(shirtSize)) {
            shirtSize = "L";
        }
        if ("43".equals(shirtSize)) {
            shirtSize = "L";
        }
        if ("44".equals(shirtSize)) {
            shirtSize = "XL";
        }
        return shirtSize;
    }
}
