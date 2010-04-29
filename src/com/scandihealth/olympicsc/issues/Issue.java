package com.scandihealth.olympicsc.issues;

import org.jboss.seam.annotations.Name;

@Name("issue")
public class Issue {

    private String name;
    private int priority;
    private int estimate;
    private String area;
    private String description;
    private int issueNumber;
    public static final int UNKNOWN = 0;
    public static final int SEVERE = 1;
    public static final int HIGH = 2;
    public static final int MEDIUM = 3;
    public static final int LOW = 4;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }
}
