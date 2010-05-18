package com.scandihealth.olympicsc.event.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@javax.persistence.Table(name = "event_vegetarian_request", catalog = "olympicsc")
@Entity
public class EventVegetarianRequest {
    private int idvegetarianrequest;

    @javax.persistence.Column(name = "idvegetarianrequest", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdvegetarianrequest() {
        return idvegetarianrequest;
    }

    public void setIdvegetarianrequest(int idvegetarianrequest) {
        this.idvegetarianrequest = idvegetarianrequest;
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

    private boolean vegetarian;

    @javax.persistence.Column(name = "vegetarian", nullable = false, insertable = true, updatable = true, length = 0, precision = 0)
    @Basic
    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventVegetarianRequest that = (EventVegetarianRequest) o;

        if (idevent != that.idevent) return false;
        if (iduser != that.iduser) return false;
        if (idvegetarianrequest != that.idvegetarianrequest) return false;
        if (vegetarian != that.vegetarian) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idvegetarianrequest;
        result = 31 * result + iduser;
        result = 31 * result + idevent;
        result = 31 * result + (vegetarian ? 1 : 0);
        return result;
    }
}
