package com.scandihealth.olympicsc.location.model;

import org.jboss.seam.annotations.Name;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@javax.persistence.Table(name = "location", catalog = "olympicsc")
@Entity
@Name("location")
public class Location implements Serializable {
    private int idlocation;
    private String name;

    @javax.persistence.Column(name = "idlocation")
    @GeneratedValue
    @Id
    public int getIdlocation() {
        return idlocation;
    }

    public void setIdlocation(int idlocation) {
        this.idlocation = idlocation;
    }


    @javax.persistence.Column(name = "name")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Location that = (Location) o;

        if (idlocation != that.idlocation) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = idlocation;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
