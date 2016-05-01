package edu.cmu.footinguidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import edu.cmu.footinguidemo.R;

public class UI_MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    ViewPager viewPager;
    TabLayout tabLayout;

    // User information
    public static class GlobalClass {
        // Database values
        public static String usr_email = "puff@footing.com";
        public static String usr_username = "Puff";
        public static int usr_numMiles = 0;
        public static String usr_countries_csv = "";
        public static String usr_journalId_csv = "";
        public static String usr_medalId_csv = "";

        // Calculated values
        public static int usr_numCountries = 0;
        public static int usr_numJournals = 0;
        public static int usr_numMedals = 0;
        public static final int num_countries = 224;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new UI_TabFragmentPagerAdapter(getSupportFragmentManager(),
                UI_MainActivity.this));

        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);


        // Get user information from Login Activity
        Intent intent = getIntent();
        GlobalClass.usr_email = intent.getStringExtra(UI_LoginActivity.EMAIL);
        GlobalClass.usr_username = intent.getStringExtra(UI_LoginActivity.USERNAME);
        GlobalClass.usr_numMiles = intent.getIntExtra(UI_LoginActivity.NUM_MILES, 0);
        GlobalClass.usr_countries_csv = intent.getStringExtra(UI_LoginActivity.COUNTRIES_CSV);
        GlobalClass.usr_journalId_csv = intent.getStringExtra(UI_LoginActivity.JOURNAL_ID_CSV);
        GlobalClass.usr_medalId_csv = intent.getStringExtra(UI_LoginActivity.MEDAL_ID_CSV);

        // For debug purpose (if starting Activity is set to MainActivity)
        /*
        if (GlobalClass.usr_email == null) GlobalClass.usr_email = "puff@footing.com";
        if (GlobalClass.usr_username == null) GlobalClass.usr_username = "Puff";
        if (GlobalClass.usr_countries_csv == null) GlobalClass.usr_countries_csv = "";
        if (GlobalClass.usr_journalId_csv == null) GlobalClass.usr_journalId_csv = "";
        if (GlobalClass.usr_medalId_csv == null) GlobalClass.usr_medalId_csv = "";
        */

        // Calculate statistics
        GlobalClass.usr_numCountries = GlobalClass.usr_countries_csv.isEmpty() ? 0 : GlobalClass.usr_countries_csv.split(",").length;
        GlobalClass.usr_numJournals = GlobalClass.usr_journalId_csv.isEmpty() ? 0 : GlobalClass.usr_journalId_csv.split(",").length;
        GlobalClass.usr_numMedals = GlobalClass.usr_medalId_csv.isEmpty() ? 0 : GlobalClass.usr_medalId_csv.split(",").length;

        // Display statistics in the menu
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.item_num_miles).setTitle(getString(R.string.total_distance) + GlobalClass.usr_numMiles + " miles");
        menu.findItem(R.id.item_num_countries).setTitle(getString(R.string.num_countries) + GlobalClass.usr_numCountries);
        menu.findItem(R.id.item_num_journals).setTitle(getString(R.string.num_journals) + GlobalClass.usr_numJournals);
        menu.findItem(R.id.item_percentage_explored).setTitle(getString(R.string.percentage_explored) + String.format("%.1f%%", GlobalClass.usr_numCountries / 1.0 / GlobalClass.num_countries));
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (super.onPrepareOptionsPanel(view, menu)) {
            // Display username and email in the drawer
            ((TextView) findViewById(R.id.text_username)).setText(GlobalClass.usr_username);
            ((TextView) findViewById(R.id.text_email)).setText(GlobalClass.usr_email);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settings) {
            Toast.makeText(UI_MainActivity.this, "Settings are not available", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(this, UI_LoginActivity.class);
            finish();
            startActivity(intent);
        }

        if (id != 0) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    public void deleteItem(View view) {
        Snackbar.make(view, "Permission denied", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    public void enterPhotoList(View view) {
        Intent intent = new Intent(view.getContext(), UI_PhotoActivity.class);
        startActivity(intent);
    }

    public void enterRecordingList(View view) {
        Intent intent = new Intent(view.getContext(), UI_RecordingActivity.class);
        startActivity(intent);
    }

}


