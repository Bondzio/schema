package com.scandihealth.olympicsc.customvalues.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "customvalue", catalog = "olympicsc")
@Entity
public class CustomValue {
    private int id;

    @javax.persistence.Column(name = "ID")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    @javax.persistence.Column(name = "NAME")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String type;

    @javax.persistence.Column(name = "TYPE")
    @Basic
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private int idCustomvaluetype;

    @javax.persistence.Column(name = "ID_CUSTOMVALUETYPE")
    @Basic
    public int getIdCustomvaluetype() {
        return idCustomvaluetype;
    }

    public void setIdCustomvaluetype(int idCustomvaluetype) {
        this.idCustomvaluetype = idCustomvaluetype;
    }

    private int idEvent;

    @javax.persistence.Column(name = "ID_EVENT")
    @Basic
    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    private int idUser;

    @javax.persistence.Column(name = "ID_USER")
    @Basic
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    private int idActivity;

    @javax.persistence.Column(name = "ID_ACTIVITY")
    @Basic
    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    private String value;

    @javax.persistence.Column(name = "VALUE")
    @Basic
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomValue that = (CustomValue) o;

        if (id != that.id) return false;
        if (idActivity != that.idActivity) return false;
        if (idCustomvaluetype != that.idCustomvaluetype) return false;
        if (idEvent != that.idEvent) return false;
        if (idUser != that.idUser) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + idCustomvaluetype;
        result = 31 * result + idEvent;
        result = 31 * result + idUser;
        result = 31 * result + idActivity;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
