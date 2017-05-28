package com.example.a15031777.visitormanagementsystem;

/**
 * Created by 15017199 on 28/5/2017.
 */

public class Records {
    private int reportId;
    private String signedIn;
    private String signOut;

    public Records(int reportId, String signedIn, String signOut, String NRIC) {
        this.reportId = reportId;
        this.signedIn = signedIn;
        this.signOut = signOut;
    }

    public int getReportId() {
        return reportId;
    }

    public String getSignedIn() {
        return signedIn;
    }

    public String getSignOut() {
        return signOut;
    }
}
