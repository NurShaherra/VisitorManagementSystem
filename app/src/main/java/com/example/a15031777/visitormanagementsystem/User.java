package com.example.a15031777.visitormanagementsystem;

/**
 * Created by 15017199 on 16/5/2017.
 */

public class User {
    private int userId;
    private String userName;
    private String emailAddress;
    private String password;
    private String userRole;
    private String fullName;
    private String unitAddress;

    public User(int userId, String userName, String emailAddress, String password, String userRole, String fullName, String unitAddress) {
        this.userId = userId;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.userRole = userRole;
        this.fullName = fullName;
        this.unitAddress = unitAddress;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUnitAddress() {
        return unitAddress;
    }
}

