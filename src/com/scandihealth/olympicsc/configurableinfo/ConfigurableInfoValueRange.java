package com.scandihealth.olympicsc.configurableinfo;

import org.jboss.seam.annotations.Name;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@javax.persistence.Table(name = "configurableinfovaluerange", catalog = "olympicsc")
@Entity
@Name("configurableinfovaluerange")
public class ConfigurableInfoValueRange implements Serializable {
    private int idconfigurableinfovaluerange;
    private int min;
    private int max;
    private int stepsize;
    private int initial;
    private String name;


    @javax.persistence.Column(name = "idconfigurableinfovaluerange", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdconfigurableinfovaluerange() {
        return idconfigurableinfovaluerange;
    }

    public void setIdconfigurableinfovaluerange(int idconfigurableinfovaluerange) {
        this.idconfigurableinfovaluerange = idconfigurableinfovaluerange;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @javax.persistence.Column(name = "min", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }


    @javax.persistence.Column(name = "max", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }


    @javax.persistence.Column(name = "stepSize", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getStepsize() {
        return stepsize;
    }

    public void setStepsize(int stepsize) {
        this.stepsize = stepsize;
    }


    @javax.persistence.Column(name = "initial", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getInitial() {
        return initial;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigurableInfoValueRange that = (ConfigurableInfoValueRange) o;

        if (idconfigurableinfovaluerange != that.idconfigurableinfovaluerange) return false;
        if (initial != that.initial) return false;
        if (max != that.max) return false;
        if (min != that.min) return false;
        if (stepsize != that.stepsize) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idconfigurableinfovaluerange;
        result = 31 * result + min;
        result = 31 * result + max;
        result = 31 * result + stepsize;
        result = 31 * result + initial;
        return result;
    }


}
