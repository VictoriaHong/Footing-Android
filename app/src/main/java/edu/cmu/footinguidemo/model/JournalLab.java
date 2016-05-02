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
    private int[] mPhotoPath = {R.drawable.golden_gate_bridge, R.drawable.mountain_view};
    private int[] mVoicePath = {R.raw.mayday_voice1, R.raw.mayday_voice2};
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
        for(int i = 0; i < mJournalName.length; i++){
            db.insert(mJournalName[i], mPhotoPath[i], mVoicePath[i]);
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
                int photopath = cursor.getInt(cursor.getColumnIndex(JournalConnector.Columns.COLUMN_NAME_PHOTO_PATH));
                int voicepath = cursor.getInt(cursor.getColumnIndex(JournalConnector.Columns.COLUMN_NAME_VOICE_PATH));
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

    JournalConnector db = new JournalConnector(mContext);
    Cursor cursor = db.query(journalname);
    Journal journal = null;
    if(cursor.getCount() == 0){
        System.out.println("no journal got");
    }else{
        cursor.moveToFirst();
        String journalName = cursor.getString(cursor.getColumnIndex(JournalConnector.Columns.COLUMN_NAME_JOURNAL_NAME));
        int photoPath = cursor.getInt(cursor.getColumnIndex(JournalConnector.Columns.COLUMN_NAME_PHOTO_PATH));
        int voicePath = cursor.getInt(cursor.getColumnIndex(JournalConnector.Columns.COLUMN_NAME_VOICE_PATH));
        journal = new Journal(journalName, photoPath, voicePath);
        cursor.close();
    }
    return journal;

}
    public void updateJournal(Journal journal){
        JournalConnector db = new JournalConnector(mContext);
        db.update(journal);

    }
}
