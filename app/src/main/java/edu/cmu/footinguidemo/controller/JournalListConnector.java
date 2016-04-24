package edu.cmu.footinguidemo.controller;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by os on 4/12/16.
 */
public class JournalListConnector extends DBConnector {
    private static final String TABLE_NAME = "journal_list_table";
    private static final String[] QUERY_COLUMNS = {"journal_list_id", "list_of_journal_id"};
    private class JournalList{
        int journal_list_id;
        int list_of_journal_id;

    }
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
