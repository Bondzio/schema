package com.scandihealth.olympicsc.event.model;

import com.scandihealth.olympicsc.user.User;

import java.util.ArrayList;
import java.util.List;

public class ExcelColumnData {
    private String header;
    private List<User> userList = new ArrayList<User>();
    private List<String> joinedData = new ArrayList<String>();

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public List<User> getUserList() {
        return userList;
    }

    public void addJoinedData(String joined) {
        joinedData.add(joined);
    }

    public List<String> getJoinedData() {
        return joinedData;
    }
}
