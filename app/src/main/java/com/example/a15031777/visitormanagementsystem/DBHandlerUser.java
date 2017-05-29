package com.example.a15031777.visitormanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by HP on 16/5/2017.
 */

public class DBHandlerUser extends SQLiteOpenHelper {
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


    public DBHandlerUser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_USER + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " TEXT," + COLUMN_EMAIL + " TEXT," +
                COLUMN_PW + " TEXT," + COLUMN_ROLE + " TEXT," + COLUMN_NAME + " TEXT," + COLUMN_ADDRESS + " TEXT " + ")";
        db.execSQL(CREATE_TABLE);
        ContentValues values = new ContentValues();
        //No need id because auto increment
        for (int i = 0; i <= 3; i++) {
            values.put(COLUMN_USERNAME, "admin" + i);
            values.put(COLUMN_EMAIL, "email");
            values.put(COLUMN_PW, "123");
            values.put(COLUMN_ROLE, "Administrator");
            values.put(COLUMN_NAME, "-");
            values.put(COLUMN_ADDRESS, "-");
            db.insert(TABLE_USER, null, values);
        }
        for (int x = 0; x <= 2; x++) {
            values.put(COLUMN_USERNAME, "manager" + x);
            values.put(COLUMN_EMAIL, "email");
            values.put(COLUMN_PW, "123");
            values.put(COLUMN_ROLE, "Manager");
            values.put(COLUMN_NAME, "-");
            values.put(COLUMN_ADDRESS, "-");
            db.insert(TABLE_USER, null, values);
        }
        for (int y = 0; y <= 4; y++) {
            values.put(COLUMN_USERNAME, "security" + y);
            values.put(COLUMN_EMAIL, "email");
            values.put(COLUMN_PW, "123");
            values.put(COLUMN_ROLE, "Security Guard");
            values.put(COLUMN_NAME, "-");
            values.put(COLUMN_ADDRESS, "-");
            db.insert(TABLE_USER, null, values);
        }
        values.put(COLUMN_USERNAME, "host");
        values.put(COLUMN_EMAIL, "email");
        values.put(COLUMN_PW, "123");
        values.put(COLUMN_ROLE, "Host");
        values.put(COLUMN_NAME, "-");
        values.put(COLUMN_ADDRESS, "-");
        db.insert(TABLE_USER, null, values);
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
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
        //return the obj
        cursor.close();
        db.close();
        return obj;
    }

    //This is just to get all the usernames from the database and add it into a String arraylist. nothing special.
    public ArrayList<String> getAllUsername() {
        ArrayList<String> usernames = new ArrayList<String>();

        String selectQuery = "SELECT " + COLUMN_USERNAME + " FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(0);
                Log.d("username", username);
                usernames.add(username);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return usernames;
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
                        cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
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

    //The same as getAllUsernameFilter except it returns a User type arraylist.
    public ArrayList<User> getUsersFiltered(String keyword) {
        ArrayList<User> users = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_USERNAME, COLUMN_EMAIL, COLUMN_PW, COLUMN_ROLE, COLUMN_NAME, COLUMN_ADDRESS};
        String condition = COLUMN_ROLE + " Like ?";
        String[] args = {"%" + keyword + "%"};
        Cursor cursor = db.query(TABLE_USER, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                User obj = new User(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
                users.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }

    //This method uses the data passed into the keyword parameter to filter through the database. In this case, it would be role.
    public ArrayList<String> getAllUsernameFilter(String keyword) {
        //Create a new list that is empty.
        ArrayList<String> usernames = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        //These are the columns for the table user.
        //Why is it done this way and there is no select query statement because we are not using rawQuery() but query() instead.
        String[] columns = {COLUMN_ID, COLUMN_USERNAME, COLUMN_EMAIL, COLUMN_PW, COLUMN_ROLE, COLUMN_NAME, COLUMN_ADDRESS};
        String condition = COLUMN_ROLE + " Like ?";
        String[] args = {"%" + keyword + "%"};
        //query works in this way where android helps precompile the queries as rawQueries can cause performance issues. However, it does not mean rawQuery cannot be used.
        //rawQuery is used when it is absolutely needed. For example, select statements from tables that are joined together.
        //This is learnt from P05 so yeah...wait for your class to come i guess?
        Cursor cursor = db.query(TABLE_USER, columns, condition, args,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(1);
                Log.d("username", username);
                usernames.add(username);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return usernames;
    }

    public ArrayList<User> getUser() {
        ArrayList<User> tasks = new ArrayList<User>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_NAME + ", "
                + COLUMN_USERNAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PW + ", " + COLUMN_ROLE  + ", " + COLUMN_NAME + ", " + COLUMN_ADDRESS
                + " FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);

                String username = cursor.getString(1);
                String email = cursor.getString(2);
                String pw = cursor.getString(3);
                String role = cursor.getString(4);
                String fullName = cursor.getString(5);
                String add = cursor.getString(6);
                User obj = new User(id, username, email,pw,role,fullName,add);
                tasks.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }
}


