package com.scandihealth.olympicsc.event.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;


@javax.persistence.Table(name = "event_childrenmenu_request", catalog = "olympicsc")
@Entity
public class EventChildrenMenuRequest {
    private int ideventChildrenmenuRequest;

    @javax.persistence.Column(name = "idevent_childrenmenu_request")
    @Id
    public int getIdeventChildrenmenuRequest() {
        return ideventChildrenmenuRequest;
    }

    public void setIdeventChildrenmenuRequest(int ideventChildrenmenuRequest) {
        this.ideventChildrenmenuRequest = ideventChildrenmenuRequest;
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

    private int childrenmenus;

    @javax.persistence.Column(name = "childrenmenus")
    @Basic
    public int getChildrenmenus() {
        return childrenmenus;
    }

    public void setChildrenmenus(int childrenmenus) {
        this.childrenmenus = childrenmenus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventChildrenMenuRequest that = (EventChildrenMenuRequest) o;

        if (childrenmenus != that.childrenmenus) return false;
        if (idevent != that.idevent) return false;
        if (ideventChildrenmenuRequest != that.ideventChildrenmenuRequest) return false;
        if (iduser != that.iduser) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ideventChildrenmenuRequest;
        result = 31 * result + iduser;
        result = 31 * result + idevent;
        result = 31 * result + childrenmenus;
        return result;
    }
}
