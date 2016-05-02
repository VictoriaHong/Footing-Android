package edu.cmu.footinguidemo.ui;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.cmu.footinguidemo.R;
import edu.cmu.footinguidemo.model.Medal;

/**
 * Created by XinHong on 5/1/16.
 */
public class MedalAdapter extends ArrayAdapter<Medal>{
        private List<Medal> mMedals;

        public MedalAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public MedalAdapter(Context context, int resource, List<Medal> medals) {
            super(context, resource, medals);
            mMedals = medals;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.list_item_medal, null);
            }

            Medal p = getItem(position);

            if (p != null) {
                TextView textView = (TextView) v.findViewById(R.id.list_item_medal_title_text_view);
                ImageView imageView = (ImageView) v.findViewById(R.id.medal_photo);

                if (textView != null) {
                    textView.setText(p.getMedalName());
                }

                if (imageView != null) {
                    if(p.isSolved()) {
                        imageView.setImageResource(R.drawable.medal);
                    }else{
                        imageView.setImageResource(R.drawable.notget);
                    }
                }
            }

            return v;
        }
        public void setMedals(List<Medal> medals){
        mMedals = medals;
    }

}

