package edu.cmu.footinguidemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import edu.cmu.footinguidemo.R;

public class UI_RecordingActivity extends AppCompatActivity {

    ListView list;
    String[] web = {
            "04/04/2016 Mon 16:34",
            "04/04/2016 Mon 16:35",
            "04/04/2016 Mon 16:37",
            "04/04/2016 Mon 17:51",
            "04/04/2016 Mon 18:33",
            "04/04/2016 Mon 19:22",
            "04/04/2016 Mon 20:34"
    };
    Integer[] imageId = {
            R.drawable.golden_gate_bridge,
            R.drawable.golden_gate_bridge,
            R.drawable.golden_gate_bridge,
            R.drawable.golden_gate_bridge,
            R.drawable.golden_gate_bridge,
            R.drawable.golden_gate_bridge,
            R.drawable.golden_gate_bridge,
    };

    String[] len = {
            "4:10",
            "2:03",
            "1:09",
            "2:30",
            "5:02",
            "3:33",
            "4:42",
            "1:19",


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //for record view
        RecordList adapter = new RecordList(UI_RecordingActivity.this, web, len);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(UI_RecordingActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();

            }
        });


    }
}
