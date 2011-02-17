package com.scandihealth.olympicsc.event.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@javax.persistence.Table(name = "event_childrenage_request", catalog = "olympicsc")
@Entity
public class EventChildrenAgeRequest {
    private int idchildrenagerequest;

    @javax.persistence.Column(name = "idchildrenagerequest", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdchildrenagerequest() {
        return idchildrenagerequest;
    }

    public void setIdchildrenagerequest(int idchildrenagerequest) {
        this.idchildrenagerequest = idchildrenagerequest;
    }

    private int iduser;

    @javax.persistence.Column(name = "iduser", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    private int idevent;

    @javax.persistence.Column(name = "idevent", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    private String childrenAge;

    @javax.persistence.Column(name = "childrenage", nullable = false, insertable = true, updatable = true, length = 0, precision = 0)
    @Basic
    public String getChildrenAge() {
        return childrenAge;
    }

    public void setChildrenAge(String childrenAge) {
        this.childrenAge = childrenAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventChildrenAgeRequest that = (EventChildrenAgeRequest) o;

        if (idchildrenagerequest != that.idchildrenagerequest) return false;
        if (idevent != that.idevent) return false;
        if (iduser != that.iduser) return false;
        if (childrenAge != null ? !childrenAge.equals(that.childrenAge) : that.childrenAge != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idchildrenagerequest;
        result = 31 * result + iduser;
        result = 31 * result + idevent;
        result = 31 * result + (childrenAge != null ? childrenAge.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EventChildrenAgeRequest{" +
                "idchildrenagerequest=" + idchildrenagerequest +
                ", iduser=" + iduser +
                ", idevent=" + idevent +
                ", childrenAge='" + childrenAge + '\'' +
                '}';
    }
}