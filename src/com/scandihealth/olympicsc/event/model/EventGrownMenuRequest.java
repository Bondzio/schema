package com.scandihealth.olympicsc.event.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: Martin Hylleberg
 * Date: 22-05-11
 * Time: 10:37
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "event_grownmenu_request", catalog = "olympicsc")
@Entity
public class EventGrownMenuRequest {
    private int ideventGrownmenuRequest;

    @javax.persistence.Column(name = "idevent_grownmenu_request")
    @Id
    public int getIdeventGrownmenuRequest() {
        return ideventGrownmenuRequest;
    }

    public void setIdeventGrownmenuRequest(int ideventGrownmenuRequest) {
        this.ideventGrownmenuRequest = ideventGrownmenuRequest;
    }

    private int iduser;

    @javax.persistence.Column(name = "iduser")
    @Basic
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    private int idevent;

    @javax.persistence.Column(name = "idevent")
    @Basic
    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    private int grownmenus;

    @javax.persistence.Column(name = "grownmenus")
    @Basic
    public int getGrownmenus() {
        return grownmenus;
    }

    public void setGrownmenus(int grownmenus) {
        this.grownmenus = grownmenus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventGrownMenuRequest that = (EventGrownMenuRequest) o;

        if (grownmenus != that.grownmenus) return false;
        if (idevent != that.idevent) return false;
        if (ideventGrownmenuRequest != that.ideventGrownmenuRequest) return false;
        if (iduser != that.iduser) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ideventGrownmenuRequest;
        result = 31 * result + iduser;
        result = 31 * result + idevent;
        result = 31 * result + grownmenus;
        return result;
    }
}
