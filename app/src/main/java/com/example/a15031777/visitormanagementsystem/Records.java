package com.example.a15031777.visitormanagementsystem;

/**
 * Created by 15017199 on 28/5/2017.
 */

public class Records {
    private int recordId;
    private String signedIn;
    private String signOut;

    public Records(int recordId, String signedIn, String signOut) {
        this.recordId = recordId;
        this.signedIn = signedIn;
        this.signOut = signOut;
    }

    public int getRecordId() {
        return recordId;
    }

    public String getSignedIn() {
        return signedIn;
    }

    public String getSignOut() {
        return signOut;
    }
}
