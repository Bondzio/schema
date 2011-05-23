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
@javax.persistence.Table(name = "event_numberofpasses_request", catalog = "olympicsc")
@Entity
public class EventNumberOfPassesRequest {
    private int ideventNumberofpassesRequest;

    @javax.persistence.Column(name = "idevent_numberofpasses_request")
    @Id
    public int getIdeventNumberofpassesRequest() {
        return ideventNumberofpassesRequest;
    }

    public void setIdeventNumberofpassesRequest(int ideventNumberofpassesRequest) {
        this.ideventNumberofpassesRequest = ideventNumberofpassesRequest;
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

    private int numberofpasses;

    @javax.persistence.Column(name = "numberofpasses")
    @Basic
    public int getNumberofpasses() {
        return numberofpasses;
    }

    public void setNumberofpasses(int numberofpasses) {
        this.numberofpasses = numberofpasses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventNumberOfPassesRequest that = (EventNumberOfPassesRequest) o;

        if (idevent != that.idevent) return false;
        if (ideventNumberofpassesRequest != that.ideventNumberofpassesRequest) return false;
        if (iduser != that.iduser) return false;
        if (numberofpasses != that.numberofpasses) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ideventNumberofpassesRequest;
        result = 31 * result + iduser;
        result = 31 * result + idevent;
        result = 31 * result + numberofpasses;
        return result;
    }
}
