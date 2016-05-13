package edu.cmu.footinguidemo.ui;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import edu.cmu.footinguidemo.R;
import edu.cmu.footinguidemo.model.Journal;

/**
 * Created by XinHong on 5/1/16.
 */
public class JournalAdapter extends ArrayAdapter<Journal> {

    private List<Journal> mJournals;
    private int[] mPhotoPath = {R.drawable.golden_gate_bridge, R.drawable.mountain_view};
    public JournalAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public JournalAdapter(Context context, int resource, List<Journal> journals) {
        super(context, resource, journals);
        mJournals = journals;
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
                if (p.getJournalName().equals("San Francisco")) {
                    imageView.setImageResource(mPhotoPath[0]);
                } else if (p.getJournalName().equals("Mountain View")) {
                    imageView.setImageResource(mPhotoPath[1]);
                } else {
                    // Use photo path saved in journal
                    String photoPath = p.getPhotoPath();
                    if (photoPath.isEmpty()) {
                        // No image
                        imageView.setImageResource(R.drawable.no_photo);
                    } else {
                        // There is image in the journal
                        imageView.setImageURI(Uri.fromFile(new File(photoPath)));
                    }
                }
            }
        }

        return v;
    }

}
