package com.scandihealth.olympicsc.customvalues.model;

import org.jboss.seam.annotations.Name;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Name("valuerange")
@Table(name = "valuerange", catalog = "customvalues")
@Entity
public class Valuerange {
    private int id;

    @javax.persistence.Column(name = "ID")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int min;

    @javax.persistence.Column(name = "MIN")
    @Basic
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    private int max;

    @javax.persistence.Column(name = "MAX")
    @Basic
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    private int defaultvalue;

    @javax.persistence.Column(name = "DEFAULTVALUE")
    @Basic
    public int getDefaultvalue() {
        return defaultvalue;
    }

    public void setDefaultvalue(int defaultvalue) {
        this.defaultvalue = defaultvalue;
    }

    private int stepsize;

    @javax.persistence.Column(name = "STEPSIZE")
    @Basic
    public int getStepsize() {
        return stepsize;
    }

    public void setStepsize(int stepsize) {
        this.stepsize = stepsize;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Valuerange that = (Valuerange) o;

        if (defaultvalue != that.defaultvalue) return false;
        if (id != that.id) return false;
        if (max != that.max) return false;
        if (min != that.min) return false;
        if (stepsize != that.stepsize) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + min;
        result = 31 * result + max;
        result = 31 * result + defaultvalue;
        result = 31 * result + stepsize;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
