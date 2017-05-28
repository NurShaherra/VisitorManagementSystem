package com.example.a15031777.visitormanagementsystem;

import android.view.ViewGroup;

/**
 * Created by 15017199 on 28/5/2017.
 */

public class Visitor {
    private String NRIC;
    private String fullName;
    private String emailAddress;
    private String modeOfTransport;
    private boolean signedIn;
    private int mobileNum;

    public Visitor(String NRIC, String fullName, String emailAddress, String modeOfTransport, boolean signedIn, int mobileNum) {
        this.NRIC = NRIC;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.modeOfTransport = modeOfTransport;
        this.signedIn = false;
        this.mobileNum = mobileNum;
    }

    public String getNRIC() {
        return NRIC;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getModeOfTransport() {
        return modeOfTransport;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    public int getMobileNum() {
        return mobileNum;
    }
}
