package edu.cmu.footinguidemo.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.footinguidemo.R;
import edu.cmu.footinguidemo.controller.JournalConnector;

/**
 * Created by XinHong on 5/1/16.
 */
public class JournalLab {
    //singleton
    private static JournalLab sJournalLab;

    private Context mContext;
    private JournalConnector mJournalConnector;
    private SQLiteDatabase mDatabase;

    public static JournalLab get(Context context){
        if(sJournalLab == null){
            sJournalLab = new JournalLab(context);
        }
        return sJournalLab;
    }
    private JournalLab(Context context){
        mContext = context.getApplicationContext();
        JournalConnector db = new JournalConnector(mContext);


        String[] mJournalName = { "San Francisco", "Mountain View"};

        for(int i = 0; i < mJournalName.length; i++) {
            //String photoPath = String.valueOf(mPhotoPath[i]);
            //String voicePath = String.valueOf(mVoicePath[i]);
            db.insert(mJournalName[i], "", "", "");
        }
        db.close();

    }

    public List<Journal> getJournals(){
        List<Journal> journals = new ArrayList<>();
        JournalConnector db = new JournalConnector(mContext);
        Cursor cursor = db.queryAll();


        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){

                String journalName = cursor.getString(cursor.getColumnIndex(JournalConnector.Columns.COLUMN_NAME_JOURNAL_NAME));
                String photopath = cursor.getString(cursor.getColumnIndex(JournalConnector.Columns.COLUMN_NAME_PHOTO_PATH));
                String voicepath = cursor.getString(cursor.getColumnIndex(JournalConnector.Columns.COLUMN_NAME_VOICE_PATH));
                Journal journal = new Journal(journalName, photopath, voicepath);
                journals.add(journal);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return journals;
    }
   public Journal getJournal(String journalname){

       /*
    JournalConnector db = new JournalConnector(mContext);
    Cursor cursor = db.query(journalname);
    Journal journal = null;
    if (cursor.getCount() == 0) {
        System.out.println("no journal got");
    } else {
        cursor.moveToFirst();
        String journalName = cursor.getString(cursor.getColumnIndex(JournalConnector.Columns.COLUMN_NAME_JOURNAL_NAME));
        String photoPath = cursor.getString(cursor.getColumnIndex(JournalConnector.Columns.COLUMN_NAME_PHOTO_PATH));
        String voicePath = cursor.getString(cursor.getColumnIndex(JournalConnector.Columns.COLUMN_NAME_VOICE_PATH));
        journal = new Journal(journalName, photoPath, voicePath);
        cursor.close();
    }
    return journal;
*/
       return null;
    }

    public void updateJournal(Journal journal) {
        JournalConnector db = new JournalConnector(mContext);
        db.update(journal);

    }
}
