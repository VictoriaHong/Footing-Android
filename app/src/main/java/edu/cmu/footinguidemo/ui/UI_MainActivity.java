package edu.cmu.footinguidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import edu.cmu.footinguidemo.R;

public class UI_MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    TabLayout tabLayout;

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

        if (id == R.id.nav_share) {
            Toast.makeText(UI_MainActivity.this, "Routes on map has been shared to Facebook", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_settings) {
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
        Snackbar.make(view, "Permission denied", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void enterPhotoList(View view) {
        Intent intent = new Intent(view.getContext(), UI_PhotoActivity.class);
        startActivity(intent);
    }

    public void enterRecordingList(View view) {
        Intent intent = new Intent(view.getContext(), UI_RecordingActivity.class);
        startActivity(intent);
    }

    public void shareMedal(View view) {
        Snackbar.make(view, "Medal has been shared to Facebook", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
