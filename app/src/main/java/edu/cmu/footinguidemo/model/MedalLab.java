package edu.cmu.footinguidemo.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.footinguidemo.controller.MedalConnector;

/**
 * Created by os on 3/16/16.
 */
public class MedalLab {
    //singleton
    private static MedalLab sMedalLab;

    private Context mContext;
    private MedalConnector mMedalConnector;
    private SQLiteDatabase mDatabase;
    public static MedalLab get(Context context){
        if(sMedalLab == null){
            sMedalLab = new MedalLab(context);
        }
        return sMedalLab;
    }

    private MedalLab(Context context){
        mContext = context.getApplicationContext();
        MedalConnector db = new MedalConnector(mContext);

        String[] mMedalName = { "1 countries explored", "5 countries explored", "10 countries explored", "20 countries explored", "30 countries explored","10000 miles traveled", "20000 miles traveled", "United State", "China", "Japan", "Taiwan"};
        for (int i = 0; i < mMedalName.length; i++) {
            if(mMedalName[i].equals("1 countries explored") || mMedalName[i].equals("United State")) {
                db.insert(mMedalName[i], true);
            }
            else{
                db.insert(mMedalName[i], false);
            }
        }

        db.close();

    }

    public List<Medal> getMedals(){
        List<Medal> medals = new ArrayList<>();
        MedalConnector db = new MedalConnector(mContext);
        Cursor cursor = db.queryAll();


        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){

                String medalName = cursor.getString(cursor.getColumnIndex(MedalConnector.Columns.COLUMN_NAME_MEDAL_NAME));
                //String photoPath = cursor.getString(cursor.getColumnIndex(MedalConnector.Columns.COLUMN_NAME_PHOTO_PATH));
                int isSolved = cursor.getInt(cursor.getColumnIndex(MedalConnector.Columns.COLUMN_MEDAL_SOLVED));

                Medal medal = new Medal(medalName, isSolved!=0);
                medals.add(medal);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return medals;
    }
    public Medal getMedal(String medalname){
        MedalConnector db = new MedalConnector(mContext);
        Cursor cursor = db.query(medalname);
        Medal medal = null;
        if(cursor.getCount() == 0){
            System.out.println("no medal got");
        }else{
            cursor.moveToFirst();
            String medalName = cursor.getString(cursor.getColumnIndex(MedalConnector.Columns.COLUMN_NAME_MEDAL_NAME));
            int isSolved = cursor.getInt(cursor.getColumnIndex(MedalConnector.Columns.COLUMN_MEDAL_SOLVED));
            medal = new Medal(medalName, isSolved!=0);
            cursor.close();
        }
        return medal;

    }
    public void updateMedal(Medal medal){
        MedalConnector db = new MedalConnector(mContext);
        db.update(medal);
    }


}
