package edu.cmu.footinguidemo.controller;

/**
 * Created by os on 4/12/16.
 */
public class UserConnector extends DBConnector{
    public static final String TABLE_NAME = "user_table";
    private static final String[] QUERY_COLUMNS = {"user_id", "user_name", "email", "password", "num_of_total_countries", "num_of_total_miles", "journal_list_id", "achievement"};

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
