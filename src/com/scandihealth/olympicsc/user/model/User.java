package com.scandihealth.olympicsc.user.model;


import com.scandihealth.olympicsc.activities.model.Activity;
import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.event.model.Event;
import org.hibernate.validator.NotNull;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.management.UserFirstName;
import org.jboss.seam.annotations.security.management.UserLastName;
import org.jboss.seam.annotations.security.management.UserPassword;
import org.jboss.seam.annotations.security.management.UserPrincipal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@javax.persistence.Table(name = "user", catalog = "olympicsc")
@Entity
@Name("user")
@Scope(ScopeType.SESSION)
public class User implements Serializable {

    public enum SHIRTSIZES {
        S, M, L, XL, XXL, XXXL, XXXXL
    }

    private int iduser;
    private String firstname;
    private String lastname;
    private String userName;
    private String password;
    private String employeeId;
    private String mail;
    private String department;
    private String phone;
    private boolean admin;
    private boolean personaleForening;
    private String shirtsize;
    private boolean firstlogin;
    private Set<Event> events = new HashSet<Event>();
    private Set<Activity> activities = new HashSet<Activity>();
    private List<String> roles;

    public User() {
        roles = new ArrayList<String>();
    }

    @javax.persistence.Column(name = "iduser")
    @GeneratedValue
    @Id
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @javax.persistence.Column(name = "firstname")
    @Basic
    @UserFirstName
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @javax.persistence.Column(name = "lastname")
    @Basic
    @UserLastName
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @javax.persistence.Column(name = "shortname")
    @Basic
    @UserPrincipal
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "password")
    @UserPassword
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @javax.persistence.Column(name = "employeeId")
    @Basic
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @javax.persistence.Column(name = "mail")
    @Basic
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @javax.persistence.Column(name = "department")
    @Basic
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @javax.persistence.Column(name = "phone")
    @Basic
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @javax.persistence.Column(name = "admin")
    @Basic
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @javax.persistence.Column(name = "personaleforening")
    @Basic
    public boolean isPersonaleForening() {
        return personaleForening;
    }

    public void setPersonaleForening(boolean personaleForening) {
        this.personaleForening = personaleForening;
    }

    @javax.persistence.Column(name = "shirtsize")
    @Basic
    @NotNull
    public String getShirtsize() {
        return shirtsize;
    }

    public void setShirtsize(String shirtsize) {
        this.shirtsize = shirtsize;
    }

    @javax.persistence.Column(name = "firstlogin")
    @Basic
    public boolean isFirstlogin() {
        return firstlogin;
    }

    public void setFirstlogin(boolean firstlogin) {
        this.firstlogin = firstlogin;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "user_has_event",
            joinColumns = {@JoinColumn(name = "user_iduser")},
            inverseJoinColumns = {@JoinColumn(name = "event_idevent")})
    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;

    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }


    public boolean hasJoinedEvent(Event event) {
        if (events == null || events.size() == 0) {
            DataManager dataManager = new DataManager();

        }
        return events.contains(event);
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "user_has_activity",
            joinColumns = {@JoinColumn(name = "user_iduser")},
            inverseJoinColumns = {@JoinColumn(name = "activity_idactivity")})
    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public boolean hasJoinedActivity(Activity activity) {
        return activities.contains(activity);
    }

    @Transient
    public List<String> getRoles() {
        if (!roles.contains("admin") && admin) {
            roles.add("admin");
        }
        if (!roles.contains("user")) {
            roles.add("user");
        }
        return roles;
    }

    @SuppressWarnings({"RedundantIfStatement"})
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (iduser != user.iduser) {
            return false;
        }
        if (employeeId != null ? !employeeId.equals(user.employeeId) : user.employeeId != null) {
            return false;
        }
        if (firstname != null ? !firstname.equals(user.firstname) : user.firstname != null) {
            return false;
        }
        if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null) {
            return false;
        }
        if (!userName.equals(user.userName)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = iduser;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + userName.hashCode();
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "iduser=" + iduser +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", userName='" + userName + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }


}
