package edu.cmu.footinguidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.cmu.footinguidemo.R;
import edu.cmu.footinguidemo.model.Medal;
import edu.cmu.footinguidemo.model.MedalLab;

/**
 * Created by os on 4/27/16.
 */
public class MedalListFragment extends Fragment {

    private RecyclerView mMedalRecyclerView;
    private MedalAdapter mAdapter;
    private class MedalHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private ImageView mMedalImageView;

        private Medal mMedal;
        public MedalHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_medal_title_text_view);
            mMedalImageView = (ImageView) itemView.findViewById(R.id.medal_photo);

        }
        public void bindMedal(Medal medal){
            mMedal = medal;
            mTitleTextView.setText(mMedal.getMedalName());
            if(medal.isSolved()) {
                mMedalImageView.setImageResource(R.drawable.medal);
            }
            else{
                mMedalImageView.setImageResource(R.drawable.notget);
            }

        }
        @Override
        public void onClick(View v){
            //check whether can get the medal
            Toast.makeText(getActivity(),mMedal.getMedalName() + "clicked!", Toast.LENGTH_SHORT ).show();
            if(mMedal.getMedalName().equals("10 countries explored")){
                //check statistics
            }
            else if(mMedal.getMedalName().equals("20 countries explored")){

            }
            else if(mMedal.getMedalName().equals("30 countries explored")){

            }
            else if(mMedal.getMedalName().equals("100000 miles traveled")){

            }
            else{

            }
            MedalLab.get(getActivity()).updateMedal(mMedal);

        }
    }
    private class MedalAdapter extends RecyclerView.Adapter<MedalHolder>{
        private List<Medal> mMedals;
        public MedalAdapter(List<Medal> medals){
            mMedals = medals;
        }
        @Override
        public MedalHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_medal, parent, false);
            return new MedalHolder(view);
        }
        @Override
        public void onBindViewHolder(MedalHolder holder, int position){
            Medal medal = mMedals.get(position);//index of the Crime in the array
            holder.bindMedal(medal);

        }

        @Override
        public int getItemCount(){
            return mMedals.size();
        }

        public void setMedals(List<Medal> medals){
            mMedals = medals;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);//tell Fragment Manager to call onCreateOptionsMenu
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_medal_list, container, false);
        mMedalRecyclerView = (RecyclerView) view.findViewById(R.id.medal_recycler_view);
        mMedalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        //updateUI();
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }


    private void updateUI(){
        MedalLab medalLab = MedalLab.get(getActivity());
        List<Medal> medals = medalLab.getMedals();
        if(mAdapter == null) {
            mAdapter = new MedalAdapter(medals);
            mMedalRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setMedals(medals);
            mAdapter.notifyDataSetChanged();
        }

    }
    public static MedalListFragment newInstance(){
        MedalListFragment medalListFragment = new MedalListFragment();
        return medalListFragment;
    }

}
