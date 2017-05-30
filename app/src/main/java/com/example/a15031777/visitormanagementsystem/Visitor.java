package com.example.a15031777.visitormanagementsystem;

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
    //This licensePlate variable is for testing, not sure if it is needed yet so do not remove, it will not mess up anything.
    private String licensePlate;

    public Visitor(String nric, String fullName, String emailAddress, int mobileNum, String hostName) {
        this.nric = nric;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.modeOfTransport = "";
        this.licensePlate = "";
        this.signedIn = 0;
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

    public void setModeOfTransport(String modeOfTransport) {
        this.modeOfTransport = modeOfTransport;
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

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
