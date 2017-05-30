package com.example.a15031777.visitormanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        //signed_in is integer (0) is false, (1) is true as SQLite does not have a separate Boolean storage class. Instead, Boolean values are stored as integers 0 (false) and 1 (true).
        String CREATE_TABLE = "CREATE TABLE " + TABLE_VISITOR + "( " + COLUMN_NRIC + " TEXT PRIMARY KEY, " + COLUMN_NAME + " TEXT," + COLUMN_EMAIL + " TEXT," +
                COLUMN_TRANSPORT + " TEXT NULL," + COLUMN_SIGNED + " INTEGER," + COLUMN_MOBILE + " INTEGER," + COLUMN_LICENSE + " TEXT NULL," + COLUMN_ID + " INTEGER " + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VISITOR);
// Creating tables again
        onCreate(db);
    }

    public void updateVisitor(String ic,String mode, int sign, String license) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(COLUMN_TRANSPORT, mode);
        data.put(COLUMN_LICENSE,license);
        data.put(COLUMN_SIGNED, sign);
        db.update(TABLE_VISITOR, data, COLUMN_NRIC + "='" + ic + "'", null);

    }

    public void addVisitor(Visitor visitor, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NRIC, visitor.getNric());
        values.put(COLUMN_NAME, visitor.getFullName());
        values.put(COLUMN_EMAIL, visitor.getEmailAddress());
        //As when you add a visitor, signed in is always false.
        values.put(COLUMN_SIGNED, 0);
        values.put(COLUMN_MOBILE, visitor.getMobileNum());
        values.put(COLUMN_ID, id);
        db.insert(TABLE_VISITOR, null, values);
        db.close();
    }

    public Boolean checkSignedIn(String nric) {
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selectQuery = "SELECT * FROM " + TABLE_VISITOR + " WHERE " + COLUMN_NRIC + " = '" +
                nric + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        int sign_in = cursor.getInt(4);
        cursor.close();
        db.close();
        if (sign_in == 0) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean checkVisitor(String nric) {
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selectQuery = "SELECT * FROM " + TABLE_VISITOR + " WHERE " + COLUMN_NRIC + " = '" +
                nric + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.moveToFirst();
        return false;
    }

    public Visitor getVisitor(String nric) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_VISITOR + " WHERE " + COLUMN_NRIC + " = '" +
                nric + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        //create the user obj
        Visitor obj = new Visitor(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(5), "-");
        obj.setModeOfTransport(cursor.getString(3));
        obj.setLicensePlate(cursor.getString(6));
        obj.setSignedIn(cursor.getInt(4));
        //return the obj
        cursor.close();
        db.close();
        return obj;
    }

}
