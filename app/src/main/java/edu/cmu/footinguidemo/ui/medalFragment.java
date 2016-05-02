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
                Toast.makeText(getActivity(),"Checking", Toast.LENGTH_SHORT ).show();
                Medal mMedal = (Medal)medalList.getItemAtPosition(position);
                if(mMedal.getMedalName().equals("1 countries explored")){
                    //check statistics
                    boolean explore = true;
                    if(explore){
                        mMedal.setSolved(true);
                    }
                }
                else if(mMedal.getMedalName().equals("5 countries explored")){

                }
                else if(mMedal.getMedalName().equals("10 countries explored")){

                }
                else if(mMedal.getMedalName().equals("20 countries explored")){

                }
                else if(mMedal.getMedalName().equals("30 countries explored")){

                }
                else if(mMedal.getMedalName().equals("100000 miles traveled")){

                }
                else if(mMedal.getMedalName().equals("200000 miles traveled")){

                }
                else if(mMedal.getMedalName().equals("United State")){
                    boolean explore = true;
                    if(explore){
                        mMedal.setSolved(true);
                    }
                }
                else if(mMedal.getMedalName().equals("China")){

                }
                else{

                }
                MedalLab medalLab = MedalLab.get(getActivity());
                medalLab.updateMedal(mMedal);

            }
        });
    }
}