package edu.cmu.footinguidemo.ui;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import edu.cmu.footinguidemo.R;
import edu.cmu.footinguidemo.model.Journal;
import edu.cmu.footinguidemo.model.JournalLab;

/**
 * Created by XinHong on 5/1/16.
 */
public class JournalFragment extends Fragment{
    private MediaPlayer mMediaPlayer;
    private int[] mVoicePath = {R.raw.mayday_voice1, R.raw.mayday_voice2};

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            JournalLab journalLab = JournalLab.get(getContext());
            List<Journal> journals = journalLab.getJournals();
            JournalAdapter journalAdapter = new JournalAdapter(this.getContext(), R.layout.list_item_journal, journals);
            final ListView journalList = (ListView) getActivity().findViewById(R.id.journalListView);
            journalList.setAdapter(journalAdapter);
            journalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Journal journal = (Journal)journalList.getItemAtPosition(position);
                    Toast.makeText(getContext(), "playing voice", Toast.LENGTH_SHORT).show();

                    if(journal.getJournalName().equals("San Francisco") || journal.getJournalName().equals("Mountain View")) {
                        if (mMediaPlayer != null) {
                            mMediaPlayer.stop();
                        }
                        if(journal.getJournalName().equals("San Francisco")) {
                            mMediaPlayer = MediaPlayer.create(getActivity(), mVoicePath[0]);
                        }
                        else{
                            mMediaPlayer = MediaPlayer.create(getActivity(), mVoicePath[1]);
                        }
                        mMediaPlayer.start();
                    }

                }
            });
        }
}
