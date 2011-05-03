package com.scandihealth.olympicsc.event.model;

import org.jboss.seam.annotations.Name;

import java.util.Date;

@Name("eventCopyData")
public class EventCopyData {
    private String name;
    private Date start;
    private Date end;
    private Date signStart;
    private Date signEnd;
    private Date unsignEnd;
    private int memberPrice;
    private int notMemberPrice;
    private int noShowPrice;


    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getSignStart() {
        return signStart;
    }

    public void setSignStart(Date signStart) {
        this.signStart = signStart;
    }

    public Date getSignEnd() {
        return signEnd;
    }

    public void setSignEnd(Date signEnd) {
        this.signEnd = signEnd;
    }

    public Date getUnsignEnd() {
        return unsignEnd;
    }

    public void setUnsignEnd(Date unsignEnd) {
        this.unsignEnd = unsignEnd;
    }

    public int getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(int memberPrice) {
        this.memberPrice = memberPrice;
    }

    public int getNotMemberPrice() {
        return notMemberPrice;
    }

    public void setNotMemberPrice(int notMemberPrice) {
        this.notMemberPrice = notMemberPrice;
    }

    public int getNoShowPrice() {
        return noShowPrice;
    }

    public void setNoShowPrice(int noShowPrice) {
        this.noShowPrice = noShowPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
