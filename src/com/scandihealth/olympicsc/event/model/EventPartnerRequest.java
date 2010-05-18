package com.scandihealth.olympicsc.event.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;


@javax.persistence.Table(name = "event_partner_request", catalog = "olympicsc")
@Entity
public class EventPartnerRequest {
    private int idpartnerrequest;

    @javax.persistence.Column(name = "idpartnerrequest", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdpartnerrequest() {
        return idpartnerrequest;
    }

    public void setIdpartnerrequest(int idpartnerrequest) {
        this.idpartnerrequest = idpartnerrequest;
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

    private String partnernames;

    @javax.persistence.Column(name = "partnernames", nullable = true, insertable = true, updatable = true, length = 90, precision = 0)
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

        EventPartnerRequest that = (EventPartnerRequest) o;

        if (idevent != that.idevent) return false;
        if (idpartnerrequest != that.idpartnerrequest) return false;
        if (iduser != that.iduser) return false;
        if (partnernames != null ? !partnernames.equals(that.partnernames) : that.partnernames != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idpartnerrequest;
        result = 31 * result + iduser;
        result = 31 * result + idevent;
        result = 31 * result + (partnernames != null ? partnernames.hashCode() : 0);
        return result;
    }
}
