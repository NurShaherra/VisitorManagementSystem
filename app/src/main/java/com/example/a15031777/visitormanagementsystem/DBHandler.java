package com.example.a15031777.visitormanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by HP on 16/5/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    //Database Version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "vms.db";
    //Table Name
    private static final String TABLE_USER = "User";
    //Table Columns Names
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "user_name";
    private static final String COLUMN_EMAIL = "email_address";
    private static final String COLUMN_PW = "password";
    private static final String COLUMN_ROLE = "user_role";
    private static final String COLUMN_NAME = "full_name";
    private static final String COLUMN_ADDRESS = "unit_address";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_USER + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " TEXT," + COLUMN_EMAIL + " TEXT," +
                COLUMN_PW + " TEXT," + COLUMN_ROLE + " TEXT," + COLUMN_NAME + " TEXT," + COLUMN_ADDRESS + " TEXT " + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
// Creating tables again
        onCreate(db);
    }

    //Add new Item
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //No need id because auto increment
        values.put(COLUMN_USERNAME, user.getUserName());
        values.put(COLUMN_EMAIL, user.getEmailAddress());
        values.put(COLUMN_PW, user.getPassword());
        values.put(COLUMN_ROLE, user.getUserRole());
        values.put(COLUMN_NAME, user.getFullName());
        values.put(COLUMN_ADDRESS, user.getUnitAddress());
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    //this will return a user object
    //this is for getting a specific user, mostly by getting the id from the listview or array like index.
    public User getUserWithId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_ID + " = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        //create the user obj
        User obj = new User(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        //return the obj
        cursor.close();
        db.close();
        return obj;
    }

    //wowe now it's everything else nice
    //getting all the users lmao
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<User>();
        //Selecting all the bs
        String selectQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //looping shit
        if (cursor.moveToFirst()) {
            do {
                User obj = new User(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                ;
                //add shit to list
                users.add(obj);

            } while (cursor.moveToNext());
        }
        return users;

    }

    public void editUser(int id, User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(COLUMN_USERNAME, user.getUserName());
        data.put(COLUMN_EMAIL, user.getEmailAddress());
        data.put(COLUMN_PW, user.getPassword());
        data.put(COLUMN_ROLE, user.getUserRole());
        data.put(COLUMN_NAME, user.getFullName());
        data.put(COLUMN_ADDRESS, user.getUnitAddress());
        db.update(TABLE_USER, data, COLUMN_ID + "=" + id, null);

    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getUserId())});
        db.close();
    }


    public void removeAllUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_USER);
        db.close();
    }

    public Boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USERNAME + " = '" +
                username + "' AND " + COLUMN_PW + " = '" + password + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.moveToFirst();
        return false;
    }

    public int getUserId(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + COLUMN_ID + " FROM " + TABLE_USER + " WHERE " + COLUMN_USERNAME + " = '" +
                username + "' AND " + COLUMN_PW + " = '" + password + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        //create the user obj
        int id = cursor.getInt(0);
        //return the obj
        cursor.close();
        db.close();
        return id;
    }
}


