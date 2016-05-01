package edu.cmu.footinguidemo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import edu.cmu.footinguidemo.R;
import edu.cmu.footinguidemo.model.Medal;
import edu.cmu.footinguidemo.model.MedalLab;

/**
 * Created by XinHong on 5/1/16.
 */

public class MedalFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MedalLab medalLab = MedalLab.get(getContext());
        List<Medal> medals = medalLab.getMedals();
        MedalAdapter medalAdapter = new MedalAdapter(this.getContext(), R.layout.list_item_medal, medals);
        final ListView medalList = (ListView) getActivity().findViewById(R.id.medalListView);
        medalList.setAdapter(medalAdapter);
        medalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Medal o = (Medal)medalList.getItemAtPosition(position);
                Toast.makeText(getContext(),o.getMedalName(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}