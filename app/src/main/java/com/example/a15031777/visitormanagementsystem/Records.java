package com.example.a15031777.visitormanagementsystem;

/**
 * Created by 15017199 on 28/5/2017.
 */

public class Records {
    private int reportId;
    private String signedIn;
    private String signOut;
    private String NRIC;

    public Records(int reportId, String signedIn, String signOut, String NRIC) {
        this.reportId = reportId;
        this.signedIn = signedIn;
        this.signOut = signOut;
        this.NRIC = NRIC;
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

    public String getNRIC() {
        return NRIC;
    }
}
