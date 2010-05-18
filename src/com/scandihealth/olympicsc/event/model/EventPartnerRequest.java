package com.scandihealth.olympicsc.event.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;


@javax.persistence.Table(name = "event_partner_request", catalog = "olympicsc")
@Entity
public class EventPartnerRequest {
    private int idpartnerrequest;
    private boolean partnerRequest;
    private int iduser;
    private int idevent;

    @javax.persistence.Column(name = "idpartnerrequest", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdpartnerrequest() {
        return idpartnerrequest;
    }

    public void setIdpartnerrequest(int idpartnerrequest) {
        this.idpartnerrequest = idpartnerrequest;
    }


    @javax.persistence.Column(name = "iduser", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }


    @javax.persistence.Column(name = "idevent", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }


    @javax.persistence.Column(name = "partnerrequest", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public boolean isPartnerRequest() {
        return partnerRequest;
    }

    public void setPartnerRequest(boolean partnerRequest) {
        this.partnerRequest = partnerRequest;
    }
}
