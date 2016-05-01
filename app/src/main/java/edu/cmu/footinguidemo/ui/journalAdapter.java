package edu.cmu.footinguidemo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.cmu.footinguidemo.R;
import edu.cmu.footinguidemo.model.Journal;

/**
 * Created by XinHong on 5/1/16.
 */
public class JournalAdapter extends ArrayAdapter<Journal> {
    public JournalAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public JournalAdapter(Context context, int resource, List<Journal> medals) {
        super(context, resource, medals);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_journal, null);
        }

        Journal p = getItem(position);

        if (p != null) {
            TextView textView = (TextView) v.findViewById(R.id.list_item_journal_title_text_view);
            ImageView imageView = (ImageView) v.findViewById(R.id.journal_cover);

            if (textView != null) {
                textView.setText(p.getJournalName());
            }

            if (imageView != null) {
                imageView.setImageResource(R.drawable.medal);
            }
        }

        return v;
    }

}
