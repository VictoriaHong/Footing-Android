package edu.cmu.footinguidemo.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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

        String[] mJournalName = {"10 countries explored", "20 countries explored", "30 countries explored","10000 miles traveled", "20000 miles traveled"};
        for (int i = 0; i < 5; i++) {
            db.insert(mJournalName[i], "", "", "");
        }
        db.close();

    }

    public List<Journal> getJournals(){
        List<Journal> journals = new ArrayList<>();
        JournalConnector db = new JournalConnector(mContext);
//        Cursor cursor = db.queryAll();
//
//
//        try{
//            cursor.moveToFirst();
//            while(!cursor.isAfterLast()){
//
//                String medalName = cursor.getString(cursor.getColumnIndex(MedalConnector.Columns.COLUMN_NAME_MEDAL_NAME));
//                //String photoPath = cursor.getString(cursor.getColumnIndex(MedalConnector.Columns.COLUMN_NAME_PHOTO_PATH));
//                int isSolved = cursor.getInt(cursor.getColumnIndex(MedalConnector.Columns.COLUMN_MEDAL_SOLVED));
//
//                Medal medal = new Medal(medalName, isSolved!=0);
//                medals.add(medal);
//                cursor.moveToNext();
//            }
//        } finally {
//            cursor.close();
//        }
        journals.add(new Journal("1"));
        journals.add(new Journal("2"));
        journals.add(new Journal("3"));
        journals.add(new Journal("4"));
        journals.add(new Journal("5"));
        return journals;
    }
//    public Medal getMedal(String medalname){
//        MedalConnector db = new MedalConnector(mContext);
//        Cursor cursor = db.query(medalname);
//        Medal medal = null;
//        if(cursor.getCount() == 0){
//            System.out.println("no medal got");
//        }else{
//            cursor.moveToFirst();
//            String medalName = cursor.getString(cursor.getColumnIndex(MedalConnector.Columns.COLUMN_NAME_MEDAL_NAME));
//            //String photoPath = cursor.getString(cursor.getColumnIndex(MedalConnector.Columns.COLUMN_NAME_PHOTO_PATH));
//            int isSolved = cursor.getInt(cursor.getColumnIndex(MedalConnector.Columns.COLUMN_MEDAL_SOLVED));
//            medal = new Medal(medalName, isSolved!=0);
//            cursor.close();
//        }
//        return medal;
//
//    }
//    public void updateMedal(Medal medal){
//        MedalConnector db = new MedalConnector(mContext);
//        db.update(medal);
//    }
}
