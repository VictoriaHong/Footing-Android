package edu.cmu.footinguidemo.controller;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database controller superclass
 * Created by os on 4/12/16.
 */
// Database Connector
public abstract class DBConnector {

    protected SQLiteDatabase mDatabase; // database object
    protected DatabaseOpenHelper mDatabaseOpenHelper; // database helper
    private static final String SQL_CREATE_USERTABLE =
            "CREATE TABLE IF NOT EXISTS " + UserConnector.TABLE_NAME + " ("
            + UserConnector.Columns._ID + " INTEGER PRIMARY KEY, "
            + UserConnector.Columns.COLUMN_NAME_USERNAME + " TEXT NOT NULL, "
            + UserConnector.Columns.COLUMN_NAME_EMAIL + " TEXT UNIQUE NOT NULL, "
            + UserConnector.Columns.COLUMN_NAME_PASSWORD + " TEXT NOT NULL, "
            + UserConnector.Columns.COLUMN_NAME_NUM_MILES + " INTEGER, "
            + UserConnector.Columns.COLUMN_NAME_COUNTRIES + " TEXT, "
            + UserConnector.Columns.COLUMN_NAME_JOURNAL_ID + " TEXT, "
            + UserConnector.Columns.COLUMN_NAME_ACHIEVEMENT + " TEXT)";
    private static final String SQL_CREATE_MEDALTABLE =
            "CREATE TABLE IF NOT EXISTS " + MedalConnector.TABLE_NAME + " ("
                    + MedalConnector.Columns._ID + " INTEGER PRIMARY KEY, "
                    + MedalConnector.Columns.COLUMN_NAME_MEDAL_NAME + " TEXT NOT NULL, "
                    + MedalConnector.Columns.COLUMN_MEDAL_SOLVED + " INTEGER)";
    private static final String SQL_CREATE_JOURNALTABLE =
            "CREATE TABLE IF NOT EXISTS " + JournalConnector.TABLE_NAME + " ("
                    + JournalConnector.Columns._ID + " INTEGER PRIMARY KEY, "
                    + JournalConnector.Columns.COLUMN_NAME_JOURNAL_NAME + " TEXT NOT NULL, "
                    + JournalConnector.Columns.COLUMN_NAME_JOURNAL_CONTENT + " TEXT NOT NULL, "
                    + JournalConnector.Columns.COLUMN_NAME_PHOTO_PATH + " TEXT, "
                    + JournalConnector.Columns.COLUMN_NAME_VOICE_PATH + " TEXT)";


    protected class DatabaseOpenHelper extends SQLiteOpenHelper {
        // public constructor
        private static final int VERSION = 1;
        public static final String DATABASE_NAME = "puff.db";
        public DatabaseOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, VERSION);
        } // end DatabaseOpenHelper constructor

        // creates the contacts table when the database is created
        @Override
        public void onCreate(SQLiteDatabase db) {
            // query to create a new table named contacts
            System.out.println("database create table");
            createTable(db); // execute the query
        } // end method onCreate

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
        } // end method onUpgrade
    }

    public DBConnector() {}

    public DBConnector(Context context) {
        // create a new DatabaseOpenHelper
        System.out.println("DB connector constructor");
        mDatabaseOpenHelper =
                new DatabaseOpenHelper(context);
        open();

    }

    public void open() throws SQLException {
        // create or open a database for reading/writing
        System.out.println("open writable database");
        mDatabase = mDatabaseOpenHelper.getWritableDatabase();


    } // end method open

    public SQLiteDatabase getDatabase() {
        return mDatabase;
    }

    // close the database connection
    public void close() {
        if (mDatabase != null)
            mDatabase.close(); // close the database connection
    }

    private void createTable(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERTABLE);
        db.execSQL(SQL_CREATE_MEDALTABLE);
        db.execSQL(SQL_CREATE_JOURNALTABLE);
    }

    //protected abstract void createTable(SQLiteDatabase db);
    protected abstract void insert();
    protected abstract void find();
    protected abstract void update();
    protected abstract void delete();
    protected abstract int getCount();

}
