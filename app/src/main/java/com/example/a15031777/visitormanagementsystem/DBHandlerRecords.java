package com.example.a15031777.visitormanagementsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static final String COLUMN_USERNAME = "user_name";
    private static final String COLUMN_EMAIL = "email_address";
    private static final String COLUMN_PW = "password";
    private static final String COLUMN_ROLE = "user_role";
    private static final String COLUMN_NAME = "full_name";
    private static final String COLUMN_ADDRESS = "unit_address";

    public DBHandlerRecords(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
