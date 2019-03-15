package com.example.managertabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class Staff extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        private ImageView profile1;
        private ImageView profile2;
        private ImageView profile3;
        private ImageView profile4;
        private ImageView profile5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        profile1 = (ImageView) findViewById(R.id.imageView2);
        profile2 = (ImageView) findViewById(R.id.imageView3);
        profile3 = (ImageView) findViewById(R.id.imageView4);
        profile4 = (ImageView) findViewById(R.id.imageView5);
        profile5 = (ImageView) findViewById(R.id.imageView6);
        profile1.setImageResource(R.drawable.profileicon);
        profile2.setImageResource(R.drawable.profileicon);
        profile3.setImageResource(R.drawable.profileicon);
        profile4.setImageResource(R.drawable.profileicon);
        profile5.setImageResource(R.drawable.profileicon);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inventory) { //Inventory
            Intent intent = new Intent(getApplicationContext(), Inventory.class);
            startActivity(intent);
        } else if (id == R.id.nav_donations) {  //Donations
            Intent intent = new Intent(getApplicationContext(), Donations.class);
            startActivity(intent);
        } else if (id == R.id.nav_donors) {  //Donors
            Intent intent = new Intent(getApplicationContext(), Donors.class);
            startActivity(intent);
        } else if (id == R.id.nav_staff) {  //
//            Intent intent = new Intent(getApplicationContext(), Staff.class);
//            startActivity(intent);
        } else if (id == R.id.nav_email) {
        } else if (id == R.id.nav_send) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
