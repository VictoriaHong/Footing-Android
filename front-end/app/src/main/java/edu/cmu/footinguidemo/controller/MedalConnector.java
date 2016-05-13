package edu.cmu.footinguidemo.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.Date;
import java.util.UUID;

import edu.cmu.footinguidemo.model.Medal;

/**
 * Created by os on 4/12/16.
 */
public class MedalConnector extends DBConnector {
    public static final String TABLE_NAME = "medal_table";
    // Inner class that define the table contents
    public static abstract class Columns implements BaseColumns {
        public static final String COLUMN_NAME_MEDAL_NAME = "medal_name";
        //public static final String COLUMN_NAME_PHOTO_PATH = "photo_path";
        public static final String COLUMN_MEDAL_SOLVED = "medal_solved";
    }
//    private static final String SQL_CREATE_TABLE =
//            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
//                    + Columns._ID + " INTEGER PRIMARY KEY, "
//                    + Columns.COLUMN_NAME_MEDAL_NAME + " TEXT NOT NULL, "
//                    + Columns.COLUMN_NAME_PHOTO_PATH + " TEXT UNIQUE NOT NULL, "
//                    + Columns.COLUMN_MEDAL_SOLVED + " INTEGER)";

    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public MedalConnector() {}

    public MedalConnector(Context context) {
        super(context);
    }

    // Create the table
//    @Override
//    protected void createTable(SQLiteDatabase db) {
//        db.execSQL(SQL_CREATE_TABLE);
//    }


    public void insert(String medalname, boolean medalsolved)  {
        System.out.println("medal connector insert called");
        mDatabase = mDatabaseOpenHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Columns.COLUMN_NAME_MEDAL_NAME, medalname);
        //values.put(Columns.COLUMN_NAME_PHOTO_PATH, photopath);
        values.put(Columns.COLUMN_MEDAL_SOLVED, medalsolved==true? 1 : 0);
        // Insert
        mDatabase.insert(TABLE_NAME,null, values);
    }


    public Cursor query(String medalname) {
        mDatabase = mDatabaseOpenHelper.getReadableDatabase();
        String selection = Columns.COLUMN_NAME_MEDAL_NAME + " = '" + medalname + "'";

        // Define a projection that specifies which columns from the database to use
        String[] projection =
                {Columns.COLUMN_NAME_MEDAL_NAME, Columns.COLUMN_MEDAL_SOLVED};
        return mDatabase.query(TABLE_NAME, projection, selection, null, null, null, null);
    }
    public Cursor queryAll(){
        mDatabase = mDatabaseOpenHelper.getReadableDatabase();
        return mDatabase.query(TABLE_NAME, null, null, null, null, null, null, null);
    }

    public void update(Medal medal){
        String medalName = medal.getMedalName();
        ContentValues values = getContentValues(medal);
        mDatabase.update(TABLE_NAME, values, Columns.COLUMN_NAME_MEDAL_NAME + " = ?", new String[]{medalName});
    }
    private static ContentValues getContentValues(Medal medal){
        ContentValues values = new ContentValues();

        values.put(Columns.COLUMN_NAME_MEDAL_NAME, medal.getMedalName());
        //values.put(Columns.COLUMN_NAME_PHOTO_PATH, medal.getPath());
        values.put(Columns.COLUMN_MEDAL_SOLVED, medal.isSolved()? 1 : 0);

        return values;
    }
    @Override
    protected void insert() {}

    // Find a row in the table
    @Override
    protected void find() {}

    // Update a row in the table
    @Override
    protected void update() {}

    // Delete a row in the table
    @Override
    protected void delete() {}

    // Get the number of contents in the table
    @Override
    protected int getCount() {
        open(); // open the database
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = mDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        close();
        return cnt;
    }
//
//    public MedalConnector(Context context){
//        super(context);
//        System.out.println("medal connector constructor");
//
//    }
//    public static String getTableName() {
//        return MedalTable.TABLE_NAME;
//    }
//
//    public static final class MedalTable{
//
//        public static final class Columns {
//            public static final String COLUMN_UUID = "uuid";
//            public static final String COLUMN_NAME_MEDAL_NAME = "medal_name";
//            public static final String COLUMN_NAME_PHOTO_PATH = "photo_path";
//            public static final String COLUMN_MEDAL_SOLVED = "medal_solved";
//        }
//    }
//
//    // Inner class that define the table contents
//
//    public class MedalCursorWrapper extends CursorWrapper {
//        public MedalCursorWrapper(Cursor cursor){
//            super(cursor);
//        }
//        public Medal getMedal(){
//            String uuidString = getString(getColumnIndex(MedalTable.Columns.COLUMN_UUID));
//            String medalName = getString(getColumnIndex(MedalTable.Columns.COLUMN_NAME_MEDAL_NAME));
//            String photoPath = getString(getColumnIndex(MedalTable.Columns.COLUMN_NAME_PHOTO_PATH));
//            int isSolved = getInt(getColumnIndex(MedalTable.Columns.COLUMN_MEDAL_SOLVED));
//            Medal medal = new Medal(UUID.fromString(uuidString));
//
//            medal.setMedalName(medalName);
//            medal.setPath(photoPath);
//            medal.setSolved(isSolved != 0);
//
//
//            return medal;
//        }
//    }
//
//    private static final String SQL_CREATE_TABLE =
//            "create table " + MedalTable.TABLE_NAME + " ("
//                    +" _id integer primary key autoincrement, "
//                    + MedalTable.Columns.COLUMN_UUID + ", "
//                    + MedalTable.Columns.COLUMN_NAME_MEDAL_NAME + ", "
//                    + MedalTable.Columns.COLUMN_NAME_PHOTO_PATH + ", "
//                    + MedalTable.Columns.COLUMN_MEDAL_SOLVED + ")";
//    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + MedalTable.TABLE_NAME;
//    // Create the table
//    @Override
//    protected void createTable(SQLiteDatabase db) {
//        System.out.println("execute create table");
//        db.execSQL(SQL_CREATE_TABLE);
//    }
//    public void insert(Medal m)  {
//
//
//        // Create a new map of values, where column names are the keys
//        ContentValues values = getContentValues(m);
//
//        // Insert
//        mDatabase.insert(MedalTable.TABLE_NAME, null, values);
//    }
//    private static ContentValues getContentValues(Medal medal){
//        ContentValues values = new ContentValues();
//        values.put(MedalTable.Columns.COLUMN_UUID, medal.getId().toString());
//        values.put(MedalTable.Columns.COLUMN_NAME_MEDAL_NAME, medal.getMedalName());
//        values.put(MedalTable.Columns.COLUMN_NAME_PHOTO_PATH, medal.getPath());
//        values.put(MedalTable.Columns.COLUMN_MEDAL_SOLVED, medal.isSolved()? 1 : 0);
//
//        return values;
//    }
//    public MedalCursorWrapper queryMedals(String whereClause, String[] whereArgs){
//        Cursor cursor = mDatabase.query(
//                MedalTable.TABLE_NAME,
//                null, //Columns-null selects all columns
//                whereClause,
//                whereArgs,
//                null, //groupBy
//                null, //having
//                null //orderBy
//        );
//        return new MedalCursorWrapper(cursor);
//    }
//    public Medal getMedal(UUID id){
//        MedalCursorWrapper cursor = queryMedals(
//                MedalTable.Columns.COLUMN_UUID + " = ?",
//                new String[]{id.toString()}
//        );
//        try{
//            if(cursor.getCount() == 0){
//                return null;
//            }
//            cursor.moveToFirst();
//            return cursor.getMedal();
//        } finally {
//            cursor.close();
//        }
//    }
//    public void updateMedal(Medal medal){
//        String uuidString = medal.getId().toString();
//        ContentValues values = getContentValues(medal);
//        mDatabase.update(MedalTable.TABLE_NAME, values, MedalTable.Columns.COLUMN_UUID + " = ?", new String[]{uuidString});
//    }
//    /*
//    public Cursor query(String name) {
//        mDatabase = mDatabaseOpenHelper.getReadableDatabase();
//        String selection = Columns.COLUMN_NAME_MEDAL_NAME + " = '" + name + "'";
//
//        // Define a projection that specifies which columns from the database to use
//        String[] projection =
//                {Columns.COLUMN_NAME_MEDAL_NAME, Columns.COLUMN_NAME_PHOTO_PATH};
//        return mDatabase.query(TABLE_NAME, projection, selection, null, null, null, null);
//    }
//    */
//    //insert a row to the table
//    @Override
//    protected void insert(){
//
//    }
//    //find a row in the table
//    @Override
//    protected void find(){
//
//    }
//    //update a row in the table
//    @Override
//    protected void update(){
//
//    }
//    //delete a row in the table
//    @Override
//    protected void delete(){
//
//    }
//    //get the number of contents in the table
//    @Override
//    protected int getCount(){
//        open(); // open the database
//        String countQuery = "SELECT  * FROM " + MedalTable.TABLE_NAME;
//        Cursor cursor = mDatabase.rawQuery(countQuery, null);
//        int cnt = cursor.getCount();
//        cursor.close();
//        close();
//        return cnt;
//    }

}
