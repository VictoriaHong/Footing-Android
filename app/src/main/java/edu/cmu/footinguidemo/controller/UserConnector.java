package edu.cmu.footinguidemo.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.HashSet;

import edu.cmu.footinguidemo.ui.UI_MainActivity;

/**
 * User database connector
 * @author Qiaoyi Chen (qiaoyic)
 */
public class UserConnector extends DBConnector {
    protected static final String TABLE_NAME = "user_table";
    private static HashSet CountrySet;
    
    // Inner class that define the table contents
    public static abstract class Columns implements BaseColumns {
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_NUM_MILES = "num_miles";
        public static final String COLUMN_NAME_COUNTRIES = "countries";
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
            + Columns.COLUMN_NAME_COUNTRIES + " TEXT, "
            + Columns.COLUMN_NAME_JOURNAL_ID + " TEXT, "
            + Columns.COLUMN_NAME_ACHIEVEMENT + " TEXT)";
    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    
    public UserConnector() {}

    public UserConnector(Context context) {
        super(context);
    }

    /**
     * Insert a new registered user into database
     * @param username - Username
     * @param email - Email
     * @param password - Password
     * @param numMiles - Total number of miles travelled
     * @param countries - CSV of name of countries travelled
     * @param journalId - CSV of journal ID written by the user
     * @param medalId - CSV of medal ID earned by the user
     */
    public void insert(String username, String email, String password, int numMiles, String countries, String journalId, String medalId)  {
        mDatabase = mDatabaseOpenHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Columns.COLUMN_NAME_USERNAME, username);
        values.put(Columns.COLUMN_NAME_EMAIL, email);
        values.put(Columns.COLUMN_NAME_PASSWORD, password);
        values.put(Columns.COLUMN_NAME_NUM_MILES, numMiles);
        values.put(Columns.COLUMN_NAME_COUNTRIES, countries);
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
                Columns.COLUMN_NAME_COUNTRIES, Columns.COLUMN_NAME_JOURNAL_ID, Columns.COLUMN_NAME_ACHIEVEMENT};
        return mDatabase.query(TABLE_NAME, projection, selection, null, null, null, null);
    }

    /**
     * Update the username of a user
     * @param email - Email address of the querying user
     * @param newUsername - New username of the user
     */
    public void updateUsername(String email, String newUsername) {
        mDatabase = mDatabaseOpenHelper.getReadableDatabase();
        String selection = Columns.COLUMN_NAME_EMAIL + " = '" + email + "'";

        ContentValues values = new ContentValues();
        values.put(Columns.COLUMN_NAME_USERNAME, newUsername);

        mDatabase.update(TABLE_NAME, values, selection, null);
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
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = mDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        close();
        return cnt;
    }

    public String hasCountry(String newCountry){
        CountrySet = new HashSet<String>();
        open();
        String getNumOfCountriesQuery = "SELECT " + Columns.COLUMN_NAME_COUNTRIES + " FROM " + TABLE_NAME + " WHERE " + Columns.COLUMN_NAME_EMAIL + "=" + "'" + UI_MainActivity.GlobalClass.usr_email + "'";
        Cursor cursor = mDatabase.rawQuery(getNumOfCountriesQuery, null);
        String countryName = "";
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                countryName = cursor.getString(cursor.getColumnIndex(Columns.COLUMN_NAME_COUNTRIES));
                String[] countries = countryName.split(",");
                for (String c : countries) {
                    CountrySet.add(c);
                    System.out.println(c);
                }
            }
        }

        if (CountrySet.contains(newCountry)) return "YES";
        else return countryName;
    }

    public void updateCountry(String newCountry, String names){
        open();
        String res = names + "," + newCountry;
//        String res = "China,United States,Korea,Thailand,Canada";
        mDatabase.execSQL("UPDATE " + TABLE_NAME + " SET " + Columns.COLUMN_NAME_COUNTRIES + "='" + res + "' WHERE " + Columns.COLUMN_NAME_EMAIL + "=" + "'" + UI_MainActivity.GlobalClass.usr_email + "'");
    }

    public void updateMiles(float distance){
        open();
        int newDistance = (int) (distance + getCurrentMiles());
        mDatabase.execSQL("UPDATE " + TABLE_NAME + " SET " + Columns.COLUMN_NAME_NUM_MILES + "='" + newDistance + "' WHERE " + Columns.COLUMN_NAME_EMAIL + "=" + "'" + UI_MainActivity.GlobalClass.usr_email + "'");
        System.out.println("*************** new miles: " + newDistance);
    }

    public float getCurrentMiles(){
        String getCurrentMilesQuery = "SELECT " + Columns.COLUMN_NAME_NUM_MILES + " FROM " + TABLE_NAME + " WHERE " + Columns.COLUMN_NAME_EMAIL + "=" + "'" + UI_MainActivity.GlobalClass.usr_email + "'";
        Cursor cursor = mDatabase.rawQuery(getCurrentMilesQuery, null);
        String miles = "0";
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                miles = cursor.getString(cursor.getColumnIndex(Columns.COLUMN_NAME_NUM_MILES));
                System.out.println("*************** current miles: " + miles);
            }
        }
        return Float.parseFloat(miles);
    }

}
