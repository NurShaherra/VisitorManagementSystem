package com.example.a15031777.visitormanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static com.example.a15031777.visitormanagementsystem.R.string.username;

/**
 * Created by 15031777 on 17/5/2017.
 */

public class DBHandlerRecords extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "Records.db";
    //Table Name
    private static final String TABLE_RECORDS = "Records";
    //Table Columns Names
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SIGNEDIN = "signed_in";
    private static final String COLUMN_SIGNEDOUT = "signed_out";

    public DBHandlerRecords(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createRecordsTableSql = "CREATE TABLE " + TABLE_RECORDS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SIGNEDIN + " TEXT," + COLUMN_SIGNEDOUT + " TEXT )";
        db.execSQL(createRecordsTableSql);
        ContentValues values = new ContentValues();
        //no need id
        for (int i = 0; i < 3; i++) {
            values.put(COLUMN_SIGNEDIN, "signed-in" + i);
            values.put(COLUMN_SIGNEDOUT, "signed-out" + i);
            db.insert(TABLE_RECORDS, null, values);

            int countSignedIn = COLUMN_SIGNEDIN.length();
            int countSignedOut = COLUMN_SIGNEDOUT.length();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
        // Creating tables again
        onCreate(db);
    }

    public void addSignIn(Records records) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //No need id because auto increment
        values.put(COLUMN_SIGNEDIN, records.getSignedIn());
        values.put(COLUMN_SIGNEDOUT, records.getSignOut());

        db.insert(TABLE_RECORDS, null, values);
        db.close();
    }

    public Records getRecordWithId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_RECORDS + " WHERE " + COLUMN_ID + " = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        //create the record obj
        Records obj = new Records(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2));
        //return the obj
        cursor.close();
        db.close();
        return obj;
    }

    public ArrayList<String> getAllRecords() {
        ArrayList<String> records = new ArrayList<String>();

        String selectQuery = "SELECT " + COLUMN_SIGNEDIN + "," + COLUMN_SIGNEDOUT + " FROM " + TABLE_RECORDS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String signedIn = cursor.getString(0);
                records.add(signedIn);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return records;
    }

    public int updateRecords(Records data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SIGNEDIN, data.getSignedIn());
        values.put(COLUMN_SIGNEDOUT, data.getSignOut());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getRecordId())};
        int result = db.update(TABLE_RECORDS, values, condition, args);
//        if (result < 1) {
//            Log.d("DBHelper", "Update failed");
//        }
        db.close();
        return result;
    }

    public int deleteRecord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_RECORDS, condition, args);
//        if (result < 1) {
//            Log.d("DBHelper", "Update failed");
//        }
        db.close();
        return result;
    }

}

