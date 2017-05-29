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
    private int signedIn;
    private int mobileNum;
    private String hostName;

    public Visitor(String nric, String fullName, String emailAddress, String modeOfTransport, int signedIn, int mobileNum, String hostName) {
        this.nric = nric;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.modeOfTransport = "";
        this.signedIn = signedIn;
        this.mobileNum = mobileNum;
        this.hostName = hostName;
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

    public void setSignedIn(int signedIn) {
        this.signedIn = signedIn;
    }

    public int getMobileNum() {
        return mobileNum;
    }

    public String getHostName() {
        return hostName;
    }
}
