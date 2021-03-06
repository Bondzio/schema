package com.scandihealth.olympicsc.event.model;

import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.imageupload.model.Logo;
import com.scandihealth.olympicsc.location.model.Location;
import com.scandihealth.olympicsc.user.model.User;
import com.scandihealth.olympicsc.utilities.dates.DateUtilities;
import org.jboss.seam.annotations.Name;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@javax.persistence.Table(name = "event", catalog = "olympicsc")
@Name("event")
public class Event implements Serializable {
    private int idevent;
    private String name;
    private String description;
    private Date start;
    private Date end;
    private boolean enabled = true;
    private Date signstart;
    private Date signend;
    private Date unsignEnd;
    private int memberPrice;
    private int notMemberPrice;
    private int noShowPrice;
    private Location location;
    private Logo logo;

    private boolean canRequestPartner = false;
    private boolean canRequestVegetarian = false;
    private boolean canRequestChildrenAge = false;
    private boolean canRequestChildrenMenus = false;
    private boolean canRequestGrownMenus = false;
    private boolean canRequestPasses = false;

    private boolean partnerRequest = false;
    private int vegetarianRequest = 0;
    private int grownMenuRequest = 0;
    private int childrenMenuRequest = 0;
    private int passesRequest = 0;
    private String childrenAgeRequest = "";

    private boolean showAttendingUsers = false;
    private Set<Activity> activities = new HashSet<Activity>();
    private List<Activity> activityList;
    private User selectedUser;

    @javax.persistence.Column(name = "idevent")
    @GeneratedValue
    @Id
    public int getIdevent() {
        return idevent;
    }


    public void setIdevent(int idevent) {
        this.idevent = idevent;
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

    @Transient
    public String getShortDescription() {
        if (description != null) {
            if (description.length() > 50) {
                return description.substring(0, 47) + "...";
            }
        }
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

    @javax.persistence.Column(name = "enabled")
    @Basic
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @javax.persistence.Column(name = "signstart")
    @Basic
    public Date getSignstart() {
        return signstart;
    }

    public void setSignstart(Date signstart) {
        this.signstart = signstart;
    }

    @javax.persistence.Column(name = "signend")
    @Basic
    public Date getSignend() {
        return signend;
    }

    public void setSignend(Date signend) {
        this.signend = signend;
    }

    @javax.persistence.Column(name = "unsignend")
    @Basic
    public Date getUnsignEnd() {
        return unsignEnd;
    }

    public void setUnsignEnd(Date unsignEnd) {
        this.unsignEnd = unsignEnd;
    }

    @javax.persistence.Column(name = "memberprice")
    @Basic
    public int getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(int memberPrice) {
        this.memberPrice = memberPrice;
    }

    @javax.persistence.Column(name = "notmemberprice")
    @Basic
    public int getNotMemberPrice() {
        return notMemberPrice;
    }

    public void setNotMemberPrice(int notMemberPrice) {
        this.notMemberPrice = notMemberPrice;
    }

    @javax.persistence.Column(name = "noshowprice")
    @Basic
    public Integer getNoShowPrice() {
        return noShowPrice;
    }

    public void setNoShowPrice(Integer noShowPrice) {
        this.noShowPrice = noShowPrice;
    }

    @Column(name = "canrequestpartner")
    public boolean isCanRequestPartner() {
        return canRequestPartner;
    }

    public void setCanRequestPartner(boolean canRequestPartner) {
        this.canRequestPartner = canRequestPartner;
    }

    @Column(name = "canrequestvegetarian")
    public boolean isCanRequestVegetarian() {
        return canRequestVegetarian;
    }

    public void setCanRequestVegetarian(boolean canRequestVegetarian) {
        this.canRequestVegetarian = canRequestVegetarian;
    }

    @Column(name="canrequestchildrenage")
    public boolean isCanRequestChildrenAge() {
        return canRequestChildrenAge;
    }

    public void setCanRequestChildrenAge(boolean canRequestChildrenAge) {
        this.canRequestChildrenAge = canRequestChildrenAge;
    }

    @Column(name="canrequestpasses")
    public boolean isCanRequestPasses() {
        return canRequestPasses;
    }

    public void setCanRequestPasses(boolean canRequestPasses) {
        this.canRequestPasses = canRequestPasses;
    }

    @Column(name="canrequestchildrenmenus")
    public boolean isCanRequestChildrenMenus() {
        return canRequestChildrenMenus;
    }

    public void setCanRequestChildrenMenus(boolean canRequestChildrenMenus) {
        this.canRequestChildrenMenus = canRequestChildrenMenus;
    }

    @Column(name="canrequestgrownmenus")
    public boolean isCanRequestGrownMenus() {
        return canRequestGrownMenus;
    }

    public void setCanRequestGrownMenus(boolean canRequestGrownMenus) {
        this.canRequestGrownMenus = canRequestGrownMenus;
    }

    @Transient
    public boolean isPartnerRequest() {
        return partnerRequest;
    }

    public void setPartnerRequest(boolean partnerRequest) {
        this.partnerRequest = partnerRequest;
    }

    @Transient
    public int getVegetarianRequest() {
        return vegetarianRequest;
    }

    public void setVegetarianRequest(int vegetarianRequest) {
        this.vegetarianRequest = vegetarianRequest;
    }

    @Transient
    public String getChildrenAgeRequest() {
        return childrenAgeRequest;
    }

    public void setChildrenAgeRequest(String childrenAgeRequest) {
        this.childrenAgeRequest = childrenAgeRequest;
    }

    @Column(name = "showattending")
    public boolean isShowAttendingUsers() {
        return showAttendingUsers;
    }

    public void setShowAttendingUsers(boolean showAttendingUsers) {
        this.showAttendingUsers = showAttendingUsers;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "location_idlocation")
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "logo_idlogo")
    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    @Transient
    public byte[] getLogoImage() {
        if (logo != null) {
            return logo.getData();
        }
        return null;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "event_has_activity",
            joinColumns = {@JoinColumn(name = "event_idevent")},
            inverseJoinColumns = {@JoinColumn(name = "activitiy_idactivity")})
    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
        activityList = new ArrayList<Activity>();
        activityList.addAll(activities);
    }

    @Transient
    public List<Activity> getActivityList() {
        Collections.sort(activityList, new Comparator<Activity>() {
            public int compare(Activity o1, Activity o2) {
                if (o2.getStart() == null) {
                    return -1;
                }
                if (o1.getStart() == null) {
                    return 1;
                }
                return o1.getStart().compareTo(o2.getStart());
            }
        });
        return activityList;
    }

    public void addActivity(Activity activity) {
        if (activityList == null) {
            activityList = new ArrayList<Activity>();
        }
        activities.add(activity);
        activityList.add(activity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Event event = (Event) o;

        if (idevent != event.idevent) {
            return false;
        }
        if (name != null ? !name.equals(event.name) : event.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = idevent;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return idevent + " " + name;
    }

    @Transient
    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    @Transient
    public int getTimeToLastSign() {
        Date now = new Date();
        return DateUtilities.dayDifference(now, getSignend());
    }


    public void setGrownMenuRequest(int grownMenuRequest) {
        this.grownMenuRequest = grownMenuRequest;
    }

    @Transient
    public int getGrownMenuRequest() {
        return grownMenuRequest;
    }

    public void setChildrenMenuRequest(int childrenMenuRequest) {
        this.childrenMenuRequest = childrenMenuRequest;
    }

    @Transient
    public int getChildrenMenuRequest() {
        return childrenMenuRequest;
    }

    public void setPassesRequest(int passesRequest) {
        this.passesRequest = passesRequest;
    }

    @Transient
    public int getPassesRequest() {
        return passesRequest;
    }
}
