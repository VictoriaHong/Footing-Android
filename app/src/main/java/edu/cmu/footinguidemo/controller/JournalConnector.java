package edu.cmu.footinguidemo.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.Objects;

import edu.cmu.footinguidemo.model.Journal;

/**
 * Created by os on 4/12/16.
 */
public class JournalConnector extends DBConnector {
    private static final String TABLE_NAME = "journal_table";
    private static final String[] QUERY_COLUMNS = {"journal_id", "journal_name", "photo_path", "voice_path"};
    // Inner class that define the table contents
    public static abstract class Columns implements BaseColumns {
        public static final String COLUMN_NAME_JOURNAL_NAME = "journal_name";
        public static final String COLUMN_NAME_PHOTO_PATH = "photo_path";
        public static final String COLUMN_NAME_VOICE_PATH = "voice_path";
    }
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + Columns._ID + " INTEGER PRIMARY KEY, "
                    + Columns.COLUMN_NAME_JOURNAL_NAME + " TEXT NOT NULL, "
                    + Columns.COLUMN_NAME_PHOTO_PATH + " TEXT NOT NULL, "
                    + Columns.COLUMN_NAME_VOICE_PATH + " TEXT NOT NULL)";


    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    //create the table
    @Override
    protected void createTable(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_TABLE);
    }
    public void insert(String journalName, String photoPath, String voicePath)  {
        mDatabase = mDatabaseOpenHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(Columns.COLUMN_NAME_JOURNAL_NAME, journalName);
        values.put(Columns.COLUMN_NAME_PHOTO_PATH, photoPath);
        values.put(Columns.COLUMN_NAME_VOICE_PATH, voicePath);

        // Insert
        mDatabase.insert(TABLE_NAME, null, values);
    }
    public Cursor query(String name) {
        mDatabase = mDatabaseOpenHelper.getReadableDatabase();
        String selection = Columns.COLUMN_NAME_JOURNAL_NAME + " = '" + name + "'";

        // Define a projection that specifies which columns from the database to use
        String[] projection =
                {Columns.COLUMN_NAME_JOURNAL_NAME, Columns.COLUMN_NAME_PHOTO_PATH, Columns.COLUMN_NAME_VOICE_PATH};
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
    public static ContentValues getContentValues(Journal journal){
        ContentValues newJournal = new ContentValues();
        newJournal.put(QUERY_COLUMNS[0], journal.getJournalId());
        newJournal.put(QUERY_COLUMNS[1], journal.getJournalName());
        newJournal.put(QUERY_COLUMNS[2], journal.getPhotoPath());
        newJournal.put(QUERY_COLUMNS[3], journal.getVoicePath());
        return newJournal;
    }
/*
    // inserts a new journal in the database
    public void insertJournal(Journal journal){
        ContentValues newJournal = getContentValues(journal);
        open();
        mDatabase.insert(TABLE_NAME, null, newJournal);
        close();
    }

    // update a new student in the database
    public void updateJournal(Journal journal, long id)
    {
        ContentValues newJournal = getContentValues(journal);
        open(); // open the database
        mDatabase.update(TABLE_NAME, newJournal, "journal_id=" + id, null);
        close(); // close the database
    } // end method

    // return a Cursor with all journals information in the database
    public Cursor getAllJournals()
    {
        return mDatabase.query(TABLE_NAME, null,
                null, null, null, null, null);
    } // end method

    // get a Cursor containing all information about the Journal specified
    // by the given id
    public Cursor getOneJournal(long id)
    {
        //query (String table, String[] columns, String selection, String[] selectionArgs,
        // String groupBy, String having, String orderBy, String limit)
        return mDatabase.query(
                TABLE_NAME, null, "journal_id=" + id, null, null, null, null);
    } // end method

    // delete the Student specified by the given String name
    public void deleteJournal(long id)
    {
        open(); // open the database
        mDatabase.delete(TABLE_NAME, "journal_id=" + id, null);
        close(); // close the database
    } // end method

    // end method
    */
}
