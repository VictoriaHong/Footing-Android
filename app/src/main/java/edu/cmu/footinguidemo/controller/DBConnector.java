package edu.cmu.footinguidemo.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by os on 4/12/16.
 */
//Database Connector
public abstract class DBConnector {
    public static final String DATABASE_NAME = "footing.db";
    private SQLiteDatabase mDatabase; // database object
    private DatabaseOpenHelper mDatabaseOpenHelper; // database helper
    private class DatabaseOpenHelper extends SQLiteOpenHelper {
        // public constructor
        private static final int VERSION = 1;

        public DatabaseOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, VERSION);
        } // end DatabaseOpenHelper constructor

        // creates the contacts table when the database is created
        @Override
        public void onCreate(SQLiteDatabase db) {
            // query to create a new table named contacts
            createTable(); // execute the query
        } // end method onCreate

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
        } // end method onUpgrade
    }
    public DBConnector(){}
    public DBConnector(Context context)
    {
        // create a new DatabaseOpenHelper
        mDatabaseOpenHelper =
                new DatabaseOpenHelper(context);
    }
    public void open() throws SQLException
    {
        // create or open a database for reading/writing
        mDatabase = mDatabaseOpenHelper.getWritableDatabase();
    } // end method open

    // close the database connection
    public void close()
    {
        if (mDatabase != null)
            mDatabase.close(); // close the database connection
    }
    protected abstract void createTable();
    protected abstract void insert();
    protected abstract void find();
    protected abstract void update();
    protected abstract void delete();
    protected abstract void getCount();

}
