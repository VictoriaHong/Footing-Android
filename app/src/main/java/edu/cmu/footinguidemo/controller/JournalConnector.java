package edu.cmu.footinguidemo.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import edu.cmu.footinguidemo.model.Journal;

/**
 * Created by os on 4/12/16.
 */
public class JournalConnector extends DBConnector {
    public static final String TABLE_NAME = "journal_table";

    // Inner class that define the table contents
    public static abstract class Columns implements BaseColumns {
        public static final String COLUMN_NAME_JOURNAL_NAME = "journal_name";
        public static final String COLUMN_NAME_PHOTO_PATH = "photo_path";
        public static final String COLUMN_NAME_VOICE_PATH = "voice_path";
    }

    public JournalConnector(Context context) {
        super(context);
    }




    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    //create the table

    public void insert(String journalName, int photoPath, int voicePath)  {
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
    public Cursor queryAll(){
        mDatabase = mDatabaseOpenHelper.getReadableDatabase();
        return mDatabase.query(TABLE_NAME, null, null, null, null, null, null, null);
    }
    public void update(Journal journal){
        String journalName = journal.getJournalName();
        ContentValues values = getContentValues(journal);
        mDatabase.update(TABLE_NAME, values, Columns.COLUMN_NAME_JOURNAL_NAME + " = ?", new String[]{journalName});
    }
    private static ContentValues getContentValues(Journal journal){
        ContentValues values = new ContentValues();

        values.put(Columns.COLUMN_NAME_JOURNAL_NAME, journal.getJournalName());
        values.put(Columns.COLUMN_NAME_PHOTO_PATH, journal.getPhotoPath());
        values.put(Columns.COLUMN_NAME_VOICE_PATH, journal.getVoicePath());

        return values;
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
