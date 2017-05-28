package com.example.a15031777.visitormanagementsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 15031777 on 17/5/2017.
 */

public class DBHandlerVisitor extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "visitor.db";
    //Table Name
    private static final String TABLE_VISITOR = "Visitor";
    //Table Columns Names
    private static final String COLUMN_NRIC = "_NRIC";
    private static final String COLUMN_NAME = "full_name";
    private static final String COLUMN_EMAIL = "email_address";
    private static final String COLUMN_TRANSPORT = "mode_of_transport";
    private static final String COLUMN_SIGNED = "signed_in";
    private static final String COLUMN_MOBILE = "mobile_number";
    private static final String COLUMN_LICENSE = "license_plate_number";
    private static final String COLUMN_ID = "user_id";


    public DBHandlerVisitor(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_VISITOR + "( " + COLUMN_NRIC + " TEXT PRIMARY KEY, " + COLUMN_NAME + " TEXT," + COLUMN_EMAIL + " TEXT," +
                COLUMN_TRANSPORT + " TEXT," + COLUMN_SIGNED + " INTEGER," + COLUMN_MOBILE + " INTEGER," + COLUMN_LICENSE + " TEXT NOT NULL," + COLUMN_ID + " INTEGER " + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VISITOR);
// Creating tables again
        onCreate(db);
    }
}
