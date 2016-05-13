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
        public static final String COLUMN_NAME_JOURNAL_CONTENT = "journal_content";
        public static final String COLUMN_NAME_PHOTO_PATH = "photo_path";
        public static final String COLUMN_NAME_VOICE_PATH = "voice_path";
    }

    public JournalConnector(Context context) {
        super(context);
    }

    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    //create the table

    /**
     * Insert journal into database
     * @param title - Journal title
     * @param content - Journal content
     * @param photoPath - Photo path (in MediaStore photo gallery)
     * @param voicePath - Voice path
     * @return _ID of inserted row
     */
    public long insert(String title, String content, String photoPath, String voicePath)  {
        System.out.println("journal connector insert called");
        mDatabase = mDatabaseOpenHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Columns.COLUMN_NAME_JOURNAL_NAME, title);
        values.put(Columns.COLUMN_NAME_JOURNAL_CONTENT, content);
        values.put(Columns.COLUMN_NAME_PHOTO_PATH, photoPath);
        values.put(Columns.COLUMN_NAME_VOICE_PATH, voicePath);

        // Insert
        return mDatabase.insert(TABLE_NAME, null, values);
    }

    /**
     * Query journal data by a set of _ID of journal_table
     * Used to get all the journals written by a particular user
     * @param journalIdCSV - Journal ID CSV values
     * @return Cursor containing one row of journal data
     */
    public Cursor query(String journalIdCSV) {
        mDatabase = mDatabaseOpenHelper.getReadableDatabase();
        String selection = Columns._ID + " IN (" + journalIdCSV + ")";

        // Return rows reversely ordered by _id (which should be reverse chronological order) (all columns)
        return mDatabase.query(TABLE_NAME, null, selection, null, null, null, Columns._ID + " DESC");
    }

    /**
     * Update journal data by querying _ID
     * @param journalId
     * @param title
     * @param content
     * @param imagePath
     * @param voicePath
     */
    public void update(String journalId, String title, String content, String imagePath, String voicePath) {
        Journal journal = new Journal(journalId, title, content, imagePath, voicePath);
        String selection = Columns._ID + " = " + journalId;
        ContentValues values = getContentValues(journal);
        mDatabase.update(TABLE_NAME, values, selection, null);
    }

    public Cursor queryAll() {
        mDatabase = mDatabaseOpenHelper.getReadableDatabase();
        return mDatabase.query(TABLE_NAME, null, null, null, null, null, null, null);
    }

    public void update(Journal journal) {
        String journalName = journal.getJournalName();
        ContentValues values = getContentValues(journal);
        mDatabase.update(TABLE_NAME, values, Columns.COLUMN_NAME_JOURNAL_NAME + " = ?", new String[]{journalName});
    }

    private static ContentValues getContentValues(Journal journal){
        ContentValues values = new ContentValues();

        values.put(Columns.COLUMN_NAME_JOURNAL_NAME, journal.getJournalName());
        values.put(Columns.COLUMN_NAME_JOURNAL_CONTENT, journal.getJournalContent());
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
