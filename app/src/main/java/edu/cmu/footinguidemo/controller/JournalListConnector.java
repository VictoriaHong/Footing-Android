package edu.cmu.footinguidemo.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import edu.cmu.footinguidemo.model.Journal;
import edu.cmu.footinguidemo.model.JournalList;
/**
 * Created by os on 4/12/16.
 */
public class JournalListConnector extends DBConnector {
    private static final String TABLE_NAME = "journal_list_table";

    // Inner class that define the table contents
    public static abstract class Columns implements BaseColumns {
        public static final String COLUMN_NAME_LIST_OF_JOURNAL_ID = "list_of_journal_id";
    }
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + Columns._ID + " INTEGER PRIMARY KEY, "
                    + Columns.COLUMN_NAME_LIST_OF_JOURNAL_ID + " TEXT NOT NULL)";
    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    //create the table
    @Override
    protected void createTable(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_TABLE);
    }
    public void insert(String listJournalId)  {
        mDatabase = mDatabaseOpenHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(Columns.COLUMN_NAME_LIST_OF_JOURNAL_ID, listJournalId);

        // Insert
        mDatabase.insert(TABLE_NAME, null, values);
    }
    public Cursor query(String id) {
        mDatabase = mDatabaseOpenHelper.getReadableDatabase();
        String selection = Columns._ID + " = '" + id + "'";

        // Define a projection that specifies which columns from the database to use
        String[] projection =
                {Columns.COLUMN_NAME_LIST_OF_JOURNAL_ID};
        return mDatabase.query(TABLE_NAME, projection, selection, null, null, null, null);
    }
    //insert a row to the table
    @Override
    protected void insert(){

    }
    //find a row in the table
    @Override
    protected void find(){

    }
    //update a row in the table
    @Override
    protected void update(){

    }
    //delete a row in the table
    @Override
    protected void delete(){

    }
    //get the number of contents in the table
    @Override
    protected int getCount(){
        open(); // open the database
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = mDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        close();
        return cnt;
    }


}
