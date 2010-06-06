package com.scandihealth.olympicsc.utilities.data;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.event.model.Event;
import com.scandihealth.olympicsc.teams.model.Team;
import com.scandihealth.olympicsc.teams.model.TeamUserSelection;
import com.scandihealth.olympicsc.user.model.User;
import com.scandihealth.olympicsc.utilities.MessageUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import javax.faces.application.FacesMessage;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Scope(ScopeType.SESSION)
@Name("teamUploadController")
public class TeamUploadController implements Serializable {
    private static final String accept = "text/plain,text/xml,application/xml,application/vnd.ms-excel";
    private byte[] data;
    private String contentType;
    private String name;
    private String title;
    private int size;

    List<String> errorMessages = new ArrayList<String>();

    @In(value = "selectedEvent")
    @Out
    Event selectedEvent;

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

    public String upload() {

        if (data != null) {
            if (selectedEvent != null) {
                DataManager dataManager = new DataManager();
                ByteArrayInputStream csvInput = new ByteArrayInputStream(data);
                List<List<String>> csvList = CsvUtil.parseCsv(csvInput, ';');
                for (List<String> strings : csvList) {
                    int counter = 0;
                    User user = null;
                    for (String string : strings) {
                        if (counter++ % 2 == 0) {
                            user = dataManager.getUser(string);
                        } else {
                            if (user != null) {
                                TeamUserSelection teamUserSelection = new TeamUserSelection();
                                Team team = dataManager.getTeam(string);
                                if (team == null) {
                                    team = createTeam(string, dataManager);
                                }

                                teamUserSelection.setIdteam(team.getId());
                                teamUserSelection.setIduser(user.getIduser());
                                teamUserSelection.setIdevent(selectedEvent.getIdevent());
                                dataManager.saveObject(teamUserSelection);
                            } else {
                                errorMessages.add("Had no user to add to team");
                            }
                        }
                    }
                }
            }
        } else {
            errorMessages.add("Data was null.");
        }

        if (errorMessages.size() > 0) {
            for (String errorMessage : errorMessages) {
                MessageUtils.createMessage(errorMessage, "", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            MessageUtils.createMessage("Teams opdateret.");
        }

        return "";
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

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }
}
