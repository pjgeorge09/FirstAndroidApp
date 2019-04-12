package com.example.managertabs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.managertabs.Donation.DonationsActivity;
import com.example.managertabs.EmployeeFiles.EmployeeActivity;
import com.example.managertabs.Inventory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Donors extends MainActivityManager
        implements NavigationView.OnNavigationItemSelectedListener {

    // Private variables
    // Donor list holds a list of donor objects
    //
    static ArrayList<Donor> donors = new ArrayList<>();
    static Boolean added = false;
    private RecyclerView donorrv;
    private DonorAdapter donorAdapter;

    //Handler/Runnable for listeners
    private Handler handler = new Handler();
    private Runnable runner = new Runnable() {
        @Override
        public void run() {
            // Rerun stuff goes below this line
            donorAdapter = new DonorAdapter(donors);

            // Sets the adapter to the recycler view
            donorrv.setAdapter(donorAdapter);
            if(added == false){
                handler.postDelayed(this, 3000); //Currently set to update every 10 seconds
            }
            //update textview here
            if(donors.isEmpty() == false) {
                donorAdapter = new DonorAdapter(donors);

                // Sets the adapter to the recycler view
                donorrv.setAdapter(donorAdapter);
                handler.removeCallbacks(runner);
            }

            //Rerun stuff goes above this line
        }
    };

    /* onCreate method creates the screen */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the background for the view, includes in the XML links to it's related files and clickables
        setContentView(R.layout.activity_donors);

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


        // Declares recycler view object and sets size to be fixed
        donorrv = (RecyclerView) findViewById(R.id.donorrv);
        donorrv.setHasFixedSize(true);

        // Declares the layout manager for our recycler view
        // Linear layout chosen for this view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        donorrv.setLayoutManager(linearLayoutManager);
        // Calls method to add donors to the donor list

        // This handler might not be needed with postdelay added
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //update textview here
                if(!added) {
                    generateDonors();
                    added = true;

                }
            }
        },3000);

//        handler.postDelayed(runner,1000);
        // Initialization of our employee adapter
        donorAdapter = new DonorAdapter(donors);

        // Sets the adapter to the recycler view
        donorrv.setAdapter(donorAdapter);
        handler.postDelayed(runner,2000);

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
        // If DONATIONS is selected by manager, go to DonationsActivity.class
        else if (id == R.id.nav_donations) {
            Intent intent = new Intent(getApplicationContext(), DonationsActivity.class);
            startActivity(intent);
        }
        // If DONORS is selected by manager, Donors.class
        else if (id == R.id.nav_donors) {
            //DO NOTHING
        }
        // If STAFF is selected by manager, go to EmployeeActivity.class
        else if (id == R.id.nav_staff) {
            Intent intent = new Intent(getApplicationContext(), EmployeeActivity.class);
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


    /* Method to populate the ArrayList with employee objects in NO PARTICULAR ORDER (Maybe autosorted by order online) */
    public void generateDonors(){
        DONORS
              .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String localFN = document.getString("firstName");
                                String localLN = document.getString("lastName");
                                String localEmail = document.getString("email");
                                String localCN = document.getString("phoneNumber");
                                Boolean localAC = document.getBoolean("allowContact");

                                donors.add(new Donor(localFN,localLN,localEmail,localCN, localAC));
                                Log.d("Temp Tag", document.getId() + " => " + document.getData());

                            }
                        } else {
                            Log.d("Abandon Operation", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

}
