package edu.cmu.footinguidemo.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.cmu.footinguidemo.R;

public class UI_MedalActivity extends AppCompatActivity {

    private String[] medals = {
            "Golden Gate Bridge Medal",
            "Fisherman's Wharf",
            "Russian Hill"
    };
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medal);

        listView = (ListView) findViewById(R.id.medalList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, medals);
        listView.setAdapter(adapter);
    }

}
