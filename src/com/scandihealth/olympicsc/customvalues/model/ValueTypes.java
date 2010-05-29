package com.scandihealth.olympicsc.customvalues.model;

import org.jboss.seam.annotations.Name;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Name("valueTypes")
@Table(name = "valuetypes", catalog = "customvalues")
@Entity
public class ValueTypes {
    private int idvaluetypes;

    @javax.persistence.Column(name = "idValueTypes")
    @Id
    public int getIdvaluetypes() {
        return idvaluetypes;
    }

    public void setIdvaluetypes(int idvaluetypes) {
        this.idvaluetypes = idvaluetypes;
    }

    private String type;

    @javax.persistence.Column(name = "Type")
    @Basic
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValueTypes that = (ValueTypes) o;

        if (idvaluetypes != that.idvaluetypes) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idvaluetypes;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
