package edu.cmu.footinguidemo.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import edu.cmu.footinguidemo.R;

/**
 * Created by os on 4/4/16.
 */
public class RecordList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;
    private final String[] length;

    public RecordList(Activity context,
                      String[] web, String[] length) {
        super(context, R.layout.list_item_photo, web);
        this.context = context;
        this.web = web;
        this.length = length;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_item_record, null, true);
        Button playButton = (Button) rowView.findViewById(R.id.play_button);



        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        TextView lengthView = (TextView) rowView.findViewById(R.id.len);
        txtTitle.setText(web[position]);
        lengthView.setText(length[position]);
        return rowView;
    }
}
