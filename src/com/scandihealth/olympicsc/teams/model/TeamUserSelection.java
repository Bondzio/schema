package com.scandihealth.olympicsc.teams.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Table(name = "teamuserselection", catalog = "olympicsc")
@Entity
public class TeamUserSelection implements Serializable {
    private int id;

    @javax.persistence.Column(name = "ID")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    private int idteam;

    @javax.persistence.Column(name = "idteam")
    @Basic
    public int getIdteam() {
        return idteam;
    }

    public void setIdteam(int idteam) {
        this.idteam = idteam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamUserSelection that = (TeamUserSelection) o;

        if (id != that.id) return false;
        if (idteam != that.idteam) return false;
        if (iduser != that.iduser) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + iduser;
        result = 31 * result + idteam;
        return result;
    }
}
