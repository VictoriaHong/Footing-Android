package edu.cmu.footinguidemo.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.cmu.footinguidemo.R;
import edu.cmu.footinguidemo.controller.UserConnector;

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
        public static final int num_countries = 224;  // 224 countries and regions in the world
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
        menu.findItem(R.id.item_percentage_explored).setTitle(getString(R.string.percentage_explored) + String.format("%.1f%%", 100.0 * GlobalClass.usr_numCountries / GlobalClass.num_countries));
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
            // Settings (change username)
            // Display a dialog for entering username

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Change Username");
            // Set up the input
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
            input.setText(GlobalClass.usr_username);
            builder.setView(input, 60, 40, 60, 0);

            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String newUsername = input.getText().toString().trim();

                    if (!newUsername.isEmpty()) {
                        // Change the username in the header
                        GlobalClass.usr_username = newUsername;
                        ((TextView) findViewById(R.id.text_username)).setText(newUsername);

                        // Write the username into database
                        UserConnector db = new UserConnector(input.getContext());
                        db.updateUsername(GlobalClass.usr_email, newUsername);
                    }
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();

        } else if (id == R.id.nav_logout) {
            // Log out

            new AlertDialog.Builder(this)
                    .setTitle("Log Out")
                    .setMessage("Do you really want to log out?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        // Go back to LoginActivity
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Intent intent = new Intent(UI_MainActivity.this, UI_LoginActivity.class);
                            finish();
                            startActivity(intent);
                        }})
                    .setNegativeButton(android.R.string.no, null).show();

        } else {
            return false;
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


