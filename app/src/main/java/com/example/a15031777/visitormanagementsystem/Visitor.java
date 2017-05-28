package com.example.a15031777.visitormanagementsystem;

import android.view.ViewGroup;

/**
 * Created by 15017199 on 28/5/2017.
 */

public class Visitor {
    private String nric;
    private String fullName;
    private String emailAddress;
    private String modeOfTransport;
    private boolean signedIn;
    private int mobileNum;

    public Visitor(String nric, String fullName, String emailAddress, int mobileNum) {
        this.nric = nric;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.modeOfTransport = "";
        this.signedIn = false;
        this.mobileNum = mobileNum;
    }

    public String getNric() {
        return nric;
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
