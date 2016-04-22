package edu.cmu.footinguidemo.controller;

import android.content.ContentValues;

import java.util.Objects;

/**
 * Created by os on 4/12/16.
 */
public class JournalConnector extends DBConnector {
    private static final String TABLE_NAME = "journal_table";
    private static final String[] QUERY_COLUMNS = {"journal_id", "journal_name", "photo_path", "voice_path"};

    //create the table
    @Override
    protected void createTable(){

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
