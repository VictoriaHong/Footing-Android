package layout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import edu.cmu.footinguidemo.R;
import edu.cmu.footinguidemo.controller.UserConnector;
import edu.cmu.footinguidemo.ui.UI_MedalActivity;
import edu.cmu.footinguidemo.ui.UI_NewJournalActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment extends Fragment implements OnMapReadyCallback {
    private static final String ARG_PAGE = "ARG_PAGE";
    private static Double latitude = 37.0, longitude = -120.0;
    private static Double latitudeP = 40.4443263, longitudeP = -79.9470875;
    private int mPage;
    private SupportMapFragment sMapFragment;
    private GoogleMap mMap;
    private Button startBtn;
    private Button endBtn;
    private Double startLag = Double.MAX_VALUE;
    private Double startLog = Double.MAX_VALUE;
    private Double endLag = Double.MAX_VALUE;
    private Double endLog = Double.MAX_VALUE;
    private Location location;
    private LocationManager locationManager;
    private Calculator calculator;
    private UserConnector userConnector;

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
        sMapFragment = SupportMapFragment.newInstance();
        sMapFragment.getMapAsync(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Do not draw every time on tab switch
//        setRetainInstance(true);
        View view = null;

        // Map tab
        if (mPage == 1) {
            FragmentManager fm = getFragmentManager();
            sMapFragment = SupportMapFragment.newInstance();
            sMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;
                    if(mMap != null) {
                        System.out.println("************* Setup map at inside onMapReady**************");

                        getLocation();
                    }
                }
            });

            if (!sMapFragment.isAdded()) {
                fm.beginTransaction().add(R.id.map, sMapFragment).commit();
            } else {
                fm.beginTransaction().show(sMapFragment).commit();
            }
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.tabpage_map, container, false);

            // Add listener on floating button
            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_get_location);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setUpMap();
                    Snackbar.make(view, "This is your current location", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            startBtn = (Button) view.findViewById(R.id.depBtn);
            startBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (startLag == Double.MAX_VALUE && endLag == Double.MAX_VALUE) {
                        startLag = latitude;
                        startLog = longitude;
                    }
                }
            });
            endBtn = (Button) view.findViewById(R.id.desBtn);
            endBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (startLag != Double.MAX_VALUE && endLag == Double.MAX_VALUE) {
                        /**
                         * Should be current location, hardcode Pittsburgh location as destination
                         */
//                        endLag = latitude;
//                        endLog = longitude;
                        endLag = latitudeP;
                        endLog = longitudeP;
                        float[] distance = new float[1];
                        Location.distanceBetween(startLag, startLog, endLag, endLog, distance);
                        /**
                         * Pop a toast to show the distance which will be stored in the database later
                         */
                        Toast.makeText(getContext(), ""+ distance[0], Toast.LENGTH_SHORT).show();
                        System.out.println("***************" + distance[0]);
                        userConnector = new UserConnector(getContext());
                        userConnector.updateMiles(distance[0]);

                        startLag = Double.MAX_VALUE;
                        startLog = Double.MAX_VALUE;
                        endLag = Double.MAX_VALUE;
                        endLog = Double.MAX_VALUE;
                    }
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

//    @Override
//    public void onConnected(Bundle bundle) {
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
//        latitude = loc.latitude;
//        longitude = loc.longitude;
//        setUpMap();
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult connectionResult) {
//
//    }


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


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.map)).getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        // For showing a move to my loction button
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            System.out.println("************Permission**************");
        }
        LatLng latLng = new LatLng(latitude, longitude);

        // For dropping a marker at a point on the Map
        mMap.addMarker(new MarkerOptions().position(latLng).title("My Home"));
        System.out.println("************add marker**************");
        // For zooming automatically to the Dropped PIN Location
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        calculator = new Calculator();
        calculator.execute(latitude, longitude);
    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
        }

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//            location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);

        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


        if (location != null) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }

        System.out.println("**************" + longitude);
        System.out.println("**************" + latitude);
        setUpMap();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1
                ||requestCode == 2) {
            int grantResult = grantResults[0];
            boolean granted = grantResult == PackageManager.PERMISSION_GRANTED;
        }
    }

    public class Calculator extends AsyncTask<Double, String, String> {
        private BufferedReader br;
        private URLConnection urlConnection;

        public String getCountry(double latitude, double longitude){
            String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng="+latitude+","+longitude+"&sensor=false";
            String country = "";
            try {
                URL Url = new URL(url);
                urlConnection = Url.openConnection();
                urlConnection.connect();

                br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line = "";

                while((line = br.readLine()) != null){
                    sb.append(line);
                }

                JSONObject reader = new JSONObject(sb.toString());
                country = reader.getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(6).getString("long_name");
                System.out.println("******************************" + country);
                return country;

            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return country;
        }

        @Override
        public String doInBackground(Double... params) {
            String country = getCountry(params[0], params[1]);
            return country;
        }

        @Override
        public void onPostExecute(String s) {
            super.onPostExecute(s);
            //write to database;
            userConnector = new UserConnector(getContext());
            String res = userConnector.hasCountry(s);
            if (!res.equals("YES")) {
                userConnector.updateCountry(s, res);
            }
        }
    }

}


