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

import java.io.Serializable;
import java.util.List;

@Name("teamController")
@Scope(ScopeType.SESSION)
public class TeamController implements Serializable {

    @In(create = true)
    Team team;

    @DataModel
    List<Team> teamList;

    @DataModelSelection
    private Team selectedTeam;

    private List<User> usersForTeam;

    private Team teamSelection;
    private List<User> userList;

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
        userList = dataManager.getUsers();
    }


    public List<User> getUsersForTeam() {
        return usersForTeam;
    }

    public void setUsersForTeam(List<User> usersForTeam) {
        this.usersForTeam = usersForTeam;
    }

    public Team getTeamSelection() {
        return teamSelection;
    }

    public void setTeamSelection(Team teamSelection) {
        System.out.println("changing team selection");
        this.teamSelection = teamSelection;
        DataManager dataManager = new DataManager();
        usersForTeam = dataManager.getUsersForTeam(teamSelection);
    }

    public void setSelectedTeam(Team selectedTeam) {
        this.selectedTeam = selectedTeam;
    }

    public Team getSelectedTeam() {
        return selectedTeam;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public String updateTeam() {
        if (teamSelection != null) {
            DataManager dataManager = new DataManager();
            Team team1 = dataManager.getTeam(teamSelection);
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
                    teamUserSelection.setIdteam(teamSelection.getId());
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
}
