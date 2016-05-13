package edu.cmu.footinguidemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    public void onListItemClick(ListView l, View view, int position, long id){
        ViewGroup viewGroup = (ViewGroup)view;
        TextView txt = (TextView) viewGroup.findViewById(R.id.list_item_medal_title_text_view);
        Toast.makeText(this, txt.getText().toString(), Toast.LENGTH_SHORT).show();
    }

}
