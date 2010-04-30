package com.scandihealth.olympicsc.activities.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: Martin Hylleberg
 * Date: 28-04-2010
 * Time: 23:30:49
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "activity_partner_request", catalog = "olympicsc")
@Entity
public class ActivityPartnerRequest {
    private int idpartnerrequest;
    private int iduser;
    private int idactivity;
    private String partnernames;

    @javax.persistence.Column(name = "idpartnerrequest")
    @Id
    public int getIdpartnerrequest() {
        return idpartnerrequest;
    }

    public void setIdpartnerrequest(int idpartnerrequest) {
        this.idpartnerrequest = idpartnerrequest;
    }


    @javax.persistence.Column(name = "iduser")
    @Basic
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }


    @javax.persistence.Column(name = "idactivity")
    @Basic
    public int getIdactivity() {
        return idactivity;
    }

    public void setIdactivity(int idactivity) {
        this.idactivity = idactivity;
    }


    @javax.persistence.Column(name = "partnernames")
    @Basic
    public String getPartnernames() {
        return partnernames;
    }

    public void setPartnernames(String partnernames) {
        this.partnernames = partnernames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActivityPartnerRequest that = (ActivityPartnerRequest) o;

        if (idactivity != that.idactivity) return false;
        if (idpartnerrequest != that.idpartnerrequest) return false;
        if (iduser != that.iduser) return false;
        if (partnernames != null ? !partnernames.equals(that.partnernames) : that.partnernames != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idpartnerrequest;
        result = 31 * result + iduser;
        result = 31 * result + idactivity;
        result = 31 * result + (partnernames != null ? partnernames.hashCode() : 0);
        return result;
    }
}
