package edu.cmu.footinguidemo.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * User database connector
 * @author Qiaoyi Chen (qiaoyic)
 */
public class UserConnector extends DBConnector {

    // Inner class that define the table contents
    public static abstract class Columns implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_NUM_MILES = "num_miles";
        public static final String COLUMN_NAME_NUM_COUNTRIES = "num_countries";
        public static final String COLUMN_NAME_JOURNAL_ID = "journal_id";
        public static final String COLUMN_NAME_MEDAL_ID = "medal_id";
    }

    private static final String SQL_CREATE_TABLE =
        "CREATE TABLE IF NOT EXISTS " + Columns.TABLE_NAME + " ("
            + Columns._ID + " INTEGER PRIMARY KEY, "
            + Columns.COLUMN_NAME_USERNAME + " TEXT NOT NULL, "
            + Columns.COLUMN_NAME_EMAIL + " TEXT UNIQUE NOT NULL, "
            + Columns.COLUMN_NAME_PASSWORD + " TEXT NOT NULL, "
            + Columns.COLUMN_NAME_NUM_MILES + " INTEGER, "
            + Columns.COLUMN_NAME_NUM_COUNTRIES + " INTEGER, "
            + Columns.COLUMN_NAME_JOURNAL_ID + " TEXT, "
            + Columns.COLUMN_NAME_MEDAL_ID + " TEXT)";
    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + Columns.TABLE_NAME;

    public UserConnector() {}

    public UserConnector(Context context) {
        super(context);
    }

    // Create the table
    @Override
    protected void createTable() {
        mDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    protected void insert() {}

    // Insert a row to the table
    protected void insert(String username, String email, String password, int numMiles, int numCountries, String journalId, String medalId)  {
        mDatabase = mDatabaseOpenHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Columns.COLUMN_NAME_USERNAME, username);
        values.put(Columns.COLUMN_NAME_EMAIL, email);
        values.put(Columns.COLUMN_NAME_PASSWORD, password);
        values.put(Columns.COLUMN_NAME_NUM_MILES, numMiles);
        values.put(Columns.COLUMN_NAME_NUM_COUNTRIES, numCountries);
        values.put(Columns.COLUMN_NAME_JOURNAL_ID, journalId);
        values.put(Columns.COLUMN_NAME_MEDAL_ID, medalId);

        // Insert
        mDatabase.insert(Columns.TABLE_NAME, null, values);
    }

    // Find a row in the table
    @Override
    protected void find() {

    }

    // Update a row in the table
    @Override
    protected void update() {

    }

    // Delete a row in the table
    @Override
    protected void delete() {

    }

    // Get the number of contents in the table
    @Override
    protected void getCount(){

    }
}
