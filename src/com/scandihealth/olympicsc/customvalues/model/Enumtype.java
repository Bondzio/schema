package com.scandihealth.olympicsc.customvalues.model;

import org.jboss.seam.annotations.Name;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Name("enumtype")
@Table(name = "enumtype", catalog = "customvalues")
@Entity
public class Enumtype {
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

        Enumtype enumtype = (Enumtype) o;

        if (id != enumtype.id) return false;
        if (name != null ? !name.equals(enumtype.name) : enumtype.name != null) return false;
        if (value != null ? !value.equals(enumtype.value) : enumtype.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
