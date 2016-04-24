package edu.cmu.footinguidemo.controller;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by os on 4/12/16.
 */
public class MedalConnector extends DBConnector {
    private static final String TABLE_NAME = "medal_table";
    private static final String[] QUERY_COLUMNS = {"medal_id", "medal_name", "path_for_picture"};

    //create the table
    @Override
    protected void createTable(SQLiteDatabase db){

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
    protected void getCount(){

    }

}
