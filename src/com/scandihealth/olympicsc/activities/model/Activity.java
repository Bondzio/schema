package com.scandihealth.olympicsc.activities.model;

import com.scandihealth.olympicsc.location.model.Location;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@javax.persistence.Table(name = "activity", catalog = "olympicsc")
@Name("activity")
@Scope(ScopeType.EVENT)
public class Activity implements Serializable {
    private int idactivity;
    private String name;
    private String description;
    private Date start;
    private Date end;
    private int minimumplayers;
    private int minimumteams;
    private Location location;
    private String responsible;
    private boolean canRequestPartner = false;
    private Integer memberPrice;
    private Integer notMemberPrice;
    private Integer noShowPrice;
    private String partnerRequest;


    @javax.persistence.Column(name = "idactivity")
    @GeneratedValue
    @Id
    public int getIdactivity() {
        return idactivity;
    }

    public void setIdactivity(int idactivity) {
        this.idactivity = idactivity;
    }


    @javax.persistence.Column(name = "name")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @javax.persistence.Column(name = "description")
    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @javax.persistence.Column(name = "start")
    @Basic
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @javax.persistence.Column(name = "end")
    @Basic
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @javax.persistence.Column(name = "minimumPlayers")
    @Basic
    public int getMinimumplayers() {
        return minimumplayers;
    }

    public void setMinimumplayers(int minimumplayers) {
        this.minimumplayers = minimumplayers;
    }

    @javax.persistence.Column(name = "minimumTeams")
    @Basic
    public int getMinimumteams() {
        return minimumteams;
    }

    public void setMinimumteams(int minimumteams) {
        this.minimumteams = minimumteams;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "location_idlocation")
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Column(name = "responsible", nullable = true, length = 90)
    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    @Column(name = "canrequestpartner")
    public boolean isCanRequestPartner() {
        return canRequestPartner;
    }

    public void setCanRequestPartner(boolean canRequestPartner) {
        this.canRequestPartner = canRequestPartner;
    }

    @Column(name = "memberprice")
    public Integer getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Integer memberPrice) {
        this.memberPrice = memberPrice;
    }

    @Column(name = "notmemberprice")
    public Integer getNotMemberPrice() {
        return notMemberPrice;
    }

    public void setNotMemberPrice(Integer notMemberPrice) {
        this.notMemberPrice = notMemberPrice;
    }

    @Column(name = "noshowprice")
    public Integer getNoShowPrice() {
        return noShowPrice;
    }

    public void setNoShowPrice(Integer noShowPrice) {
        this.noShowPrice = noShowPrice;
    }

    @Transient
    public String getShortDescription() {
        if (description.length() > 300) {
            return description.substring(0, 297) + "...";
        }
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Activity activity = (Activity) o;

        if (idactivity != activity.idactivity) {
            return false;
        }
        if (name != null ? !name.equals(activity.name) : activity.name != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = idactivity;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "idactivity=" + idactivity +
                ", name='" + name + '\'' +
                '}';
    }

    @Transient
    public String getPartnerRequest() {
        return partnerRequest;
    }

    public void setPartnerRequest(String partnerRequest) {
        this.partnerRequest = partnerRequest;
    }

    private String text;
    private Integer color;
    private float scale;

    @Transient
    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    @Transient
    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    @Transient
    public String getText() {
        return "Test";
    }

    public void setText(String text) {
//        this.text = text;
    }

    private long percentage;

    @Transient
    public long getPercentage() {
        return percentage;
    }

    public void setPercentage(long percentage) {
        this.percentage = percentage;
    }

    private long offset;

    @Transient
    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }
}
