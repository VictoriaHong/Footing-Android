package edu.cmu.footinguidemo.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * User database connector
 * @author Qiaoyi Chen (qiaoyic)
 */
public class UserConnector extends DBConnector {
    private static final String TABLE_NAME = "user_table";
    
    
    // Inner class that define the table contents
    public static abstract class Columns implements BaseColumns {
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_NUM_MILES = "num_miles";
        public static final String COLUMN_NAME_NUM_COUNTRIES = "num_countries";
        public static final String COLUMN_NAME_JOURNAL_ID = "journal_id";
        public static final String COLUMN_NAME_ACHIEVEMENT = "achievement";
    }

    private static final String SQL_CREATE_TABLE =
        "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + Columns._ID + " INTEGER PRIMARY KEY, "
            + Columns.COLUMN_NAME_USERNAME + " TEXT NOT NULL, "
            + Columns.COLUMN_NAME_EMAIL + " TEXT UNIQUE NOT NULL, "
            + Columns.COLUMN_NAME_PASSWORD + " TEXT NOT NULL, "
            + Columns.COLUMN_NAME_NUM_MILES + " INTEGER, "
            + Columns.COLUMN_NAME_NUM_COUNTRIES + " INTEGER, "
            + Columns.COLUMN_NAME_JOURNAL_ID + " TEXT, "
            + Columns.COLUMN_NAME_ACHIEVEMENT + " TEXT)";
    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    
    public UserConnector() {}

    public UserConnector(Context context) {
        super(context);
    }

    // Create the table
    @Override
    protected void createTable(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    /**
     * Insert a new registered user into databse
     * @param username - Username
     * @param email - Email
     * @param password - Password
     * @param numMiles - Total number of miles travelled
     * @param numCountries - Total number of countries travelled
     * @param journalId - CSV of journal ID written by the user
     * @param medalId - CSV of medal ID earned by the user
     */
    public void insert(String username, String email, String password, int numMiles, int numCountries, String journalId, String medalId)  {
        mDatabase = mDatabaseOpenHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Columns.COLUMN_NAME_USERNAME, username);
        values.put(Columns.COLUMN_NAME_EMAIL, email);
        values.put(Columns.COLUMN_NAME_PASSWORD, password);
        values.put(Columns.COLUMN_NAME_NUM_MILES, numMiles);
        values.put(Columns.COLUMN_NAME_NUM_COUNTRIES, numCountries);
        values.put(Columns.COLUMN_NAME_JOURNAL_ID, journalId);
        values.put(Columns.COLUMN_NAME_ACHIEVEMENT, medalId);

        // Insert
        mDatabase.insert(TABLE_NAME, null, values);
    }

    /**
     * Get user data with a specific email address
     * @param email - Email address of the querying user
     * @return One row containing all user data, if user exists
     */
    public Cursor query(String email) {
        mDatabase = mDatabaseOpenHelper.getReadableDatabase();
        String selection = Columns.COLUMN_NAME_EMAIL + " = '" + email + "'";

        // Define a projection that specifies which columns from the database to use
        String[] projection =
                {Columns.COLUMN_NAME_USERNAME, Columns.COLUMN_NAME_PASSWORD, Columns.COLUMN_NAME_NUM_MILES,
                Columns.COLUMN_NAME_NUM_COUNTRIES, Columns.COLUMN_NAME_JOURNAL_ID, Columns.COLUMN_NAME_ACHIEVEMENT};
        return mDatabase.query(TABLE_NAME, projection, selection, null, null, null, null);
    }

    @Override
    protected void insert() {}

    // Find a row in the table
    @Override
    protected void find() {}

    // Update a row in the table
    @Override
    protected void update() {}

    // Delete a row in the table
    @Override
    protected void delete() {}

    // Get the number of contents in the table
    @Override
    protected int getCount() {
        open(); // open the database
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = mDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        close();
        return cnt;
    }
}
