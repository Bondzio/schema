package com.scandihealth.olympicsc.teams.controller;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.teams.model.Team;
import com.scandihealth.olympicsc.user.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;

import java.util.List;

@Name("teamController")
@Scope(ScopeType.EVENT)
public class TeamController {

    @In(create = true)
    Team team;

    @DataModel
    List<Team> teamList;
    private List<User> listValues;

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

    public void setListValues(List<User> listValues) {
        this.listValues = listValues;
    }

    public List<User> getListValues() {
        return listValues;
    }
}
