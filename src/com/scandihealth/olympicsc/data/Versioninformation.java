package com.scandihealth.olympicsc.data;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Name("versioninformation")
@Scope(ScopeType.SESSION)
@Entity
public class Versioninformation {
    private int idversioninformation;

    @javax.persistence.Column(name = "idversioninformation")
    @Id
    public int getIdversioninformation() {
        return idversioninformation;
    }

    public void setIdversioninformation(int idversioninformation) {
        this.idversioninformation = idversioninformation;
    }

    private int major;

    @javax.persistence.Column(name = "major")
    @Basic
    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    private int majorminor;

    @javax.persistence.Column(name = "majorminor")
    @Basic
    public int getMajorminor() {
        return majorminor;
    }

    public void setMajorminor(int majorminor) {
        this.majorminor = majorminor;
    }

    private int minor;

    @javax.persistence.Column(name = "minor")
    @Basic
    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    private int minorminor;

    @javax.persistence.Column(name = "minorminor")
    @Basic
    public int getMinorminor() {
        return minorminor;
    }

    public void setMinorminor(int minorminor) {
        this.minorminor = minorminor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Versioninformation that = (Versioninformation) o;

        if (idversioninformation != that.idversioninformation) return false;
        if (major != that.major) return false;
        if (majorminor != that.majorminor) return false;
        if (minor != that.minor) return false;
        if (minorminor != that.minorminor) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idversioninformation;
        result = 31 * result + major;
        result = 31 * result + majorminor;
        result = 31 * result + minor;
        result = 31 * result + minorminor;
        return result;
    }
}
