package com.example.managertabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class Donations extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    //test data
    private ArrayList<String> TestData;
    //Formal Test Data wtih Object
    ArrayList<Donations_Data> ProperData;
    /* onCreate method creates the screen */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the background for the view, includes in the XML links to it's related files and clickables
        setContentView(R.layout.activity_donations);

        // Creates the toolbar at the top of the screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Sets the navigation drawer to still be accessible by the toolbar button. This is the sliding part
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Sets the side navigation to be able to be called and buttons selected. This is the clickable part.
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
// REcycle STUFf
        recyclerView =(RecyclerView) findViewById(R.id.recycle_donation);
        //generates the test data
        TestData = new ArrayList<>();
        for (int i=0; i<100; i++) {
            TestData.add("Donation" + i);

        }
        ProperData = new ArrayList<>();
        for(int i=0; i<25; i++){
            ProperData.add(new Donations_Data("03/22/1901","Beans",500, "Small"));
            ProperData.add(new Donations_Data("03/21/1904","Peas",500, "Medium"));
            ProperData.add(new Donations_Data("03/13/1903","Green Peas",500, "Large"));
            ProperData.add(new Donations_Data("03/01/1902","Canned Tomtatoes",500, "Small"));
        }
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(false);
        adapter = new MainAdapter(ProperData);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        }





    /* Method used when drawer (tabs) layout is open, listens for button clicks (tab selected) and
    does a screen transition based on received */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // If INVENTORY is selected by manager, go to Inventory.class
        if (id == R.id.nav_inventory) {
            Intent intent = new Intent(getApplicationContext(), Inventory.class);
            startActivity(intent);
        }
        // If DONATIONS is selected by manager, go to Donations.class
        else if (id == R.id.nav_donations) {
            //DO NOTHING
        }
        // If DONORS is selected by manager, Donors.class
        else if (id == R.id.nav_donors) {
            Intent intent = new Intent(getApplicationContext(), Donors.class);
            startActivity(intent);
        }
        // If STAFF is selected by manager, go to Staff.class
        else if (id == R.id.nav_staff) {
            Intent intent = new Intent(getApplicationContext(), Staff.class);
            startActivity(intent);
        }
        // If ______ is selected by manager, go to ______
        else if (id == R.id.nav_email) {
        }
        // If home is selected by manager, go to home
        else if (id == R.id.nav_home) {
            Intent intent = new Intent(getApplicationContext(), ManagerHomeScreen.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //Fill with test Data   Date, Item, Quantity, Size

}
