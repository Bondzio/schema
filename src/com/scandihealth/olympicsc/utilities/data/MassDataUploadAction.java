package com.scandihealth.olympicsc.utilities.data;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.teams.model.Team;
import com.scandihealth.olympicsc.teams.model.TeamUserSelection;
import com.scandihealth.olympicsc.user.model.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Scope(ScopeType.SESSION)
@Name("massDataUploadService")
public class MassDataUploadAction implements Serializable {
    private static final String accept = "text/plain,text/xml,application/xml,application/vnd.ms-excel";
    private byte[] data;
    private String contentType;
    private String name;
    private String title;
    private int size;

    Map<Team, User> teamMap = new HashMap<Team, User>();

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void upload() {
        System.out.println("in upload---name----> " + name);
        System.out.println("in upload---contentType----> " + contentType);
        System.out.println("in upload---size----> " + size);
        System.out.println("in upload---title----> " + title);


        // Process uploaded csv file.

        if (data != null) {
            DataManager dataManager = new DataManager();
            ByteArrayInputStream csvInput = new ByteArrayInputStream(data);
            List<List<String>> csvList = CsvUtil.parseCsv(csvInput, ';');
            for (List<String> strings : csvList) {
                int counter = 0;
                User user = null;
                for (String string : strings) {
                    if (counter++ % 2 == 0) {
                        user = dataManager.getUser(string);
                        System.out.println("username = " + string);
                    } else {
                        TeamUserSelection teamUserSelection = new TeamUserSelection();
                        Team team = dataManager.getTeam(string);
                        if (team == null) {
                            team = createTeam(string, dataManager);
                        }

                        teamUserSelection.setIdteam(team.getId());
                        teamUserSelection.setIduser(user.getIduser());
                        dataManager.saveObject(teamUserSelection);
                        System.out.println("team = " + string);
                    }
                }
            }
            // Now you can do your thing with the CSV List.
        } else {
            // Empty file error, do your thing.
        }
    }

    private Team createTeam(String name, DataManager dataManager) {
        Team team = new Team();
        team.setName(name);
        dataManager.saveObject(team);
        return team;
    }


    public String getAccept() {
        return accept;
    }
}
