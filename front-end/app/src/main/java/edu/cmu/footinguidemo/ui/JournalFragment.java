package edu.cmu.footinguidemo.ui;

import android.content.Intent;
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
public class JournalFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private int[] mVoicePath = {R.raw.mayday_voice1, R.raw.mayday_voice2};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Reload list items
        List<Journal> journals = JournalLab.get(getContext()).getJournals();
        JournalAdapter journalAdapter = new JournalAdapter(this.getContext(), R.layout.list_item_journal, journals);
        final ListView journalList = (ListView) getActivity().findViewById(R.id.journalListView);

        journalList.setAdapter(journalAdapter);
    }

    @Override
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
                Journal journal = (Journal) journalList.getItemAtPosition(position);

                Intent intent = new Intent(getContext(), UI_NewJournalActivity.class);
                intent.putExtra(UI_NewJournalActivity.NEW_JOURNAL, false);
                intent.putExtra(UI_NewJournalActivity.JOURNAL_ID, journal.getJournalId());
                intent.putExtra(UI_NewJournalActivity.TITLE, journal.getJournalName());
                intent.putExtra(UI_NewJournalActivity.CONTENT, journal.getJournalContent());
                intent.putExtra(UI_NewJournalActivity.PHOTO_PATH, journal.getPhotoPath());
                intent.putExtra(UI_NewJournalActivity.VOICE_PATH, journal.getVoicePath());
                startActivity(intent);
            }
        });
    }
}
