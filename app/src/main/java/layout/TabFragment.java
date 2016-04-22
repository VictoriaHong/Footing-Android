package layout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cmu.footinguidemo.ui.UI_MedalActivity;
import edu.cmu.footinguidemo.ui.UI_NewJournalActivity;
import edu.cmu.footinguidemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment extends Fragment {
    private static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public TabFragment() {
        // Required empty public constructor
    }

    public static TabFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        TabFragment fragment = new TabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Do not draw every time on tab switch
        setRetainInstance(true);
        View view = null;

        // Map tab
        if (mPage == 1) {
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.tabpage_map, container, false);

            // Add listener on floating button
            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_get_location);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "This is your current location", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
        // Journal tab
        else if (mPage == 2) {
            view = inflater.inflate(R.layout.tabpage_journal, container, false);

            // Add listener on floating button
            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_add_journal);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), UI_NewJournalActivity.class);
                    startActivity(intent);
                }
            });
        }
        // Medal tab
        else if (mPage == 3) {
            view = inflater.inflate(R.layout.tabpage_medal, container, false);

            // Add listener on floating button
            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_add_medal);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), UI_MedalActivity.class);
                    startActivity(intent);
                }
            });
        }

        return view;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
