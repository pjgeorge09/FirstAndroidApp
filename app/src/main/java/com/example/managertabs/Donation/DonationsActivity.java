package com.example.managertabs.Donation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.managertabs.Donors;
import com.example.managertabs.EmployeeFiles.EmployeeActivity;
import com.example.managertabs.Inventory;
import com.example.managertabs.Item;
import com.example.managertabs.MainActivityManager;
import com.example.managertabs.ManagerHomeScreen;
import com.example.managertabs.R;
import com.example.managertabs.inventoryAdapter;
import com.example.managertabs.inventoryData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DonationsActivity extends MainActivityManager
        implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayoutManager layoutManager= new LinearLayoutManager(this);

    Donation donation = new Donation();
    LinearLayoutManager layoutManager2= new LinearLayoutManager(this);
    // Private variables
    // Employee list holds a list of employee objects
    //
    static ArrayList<Donation> donations = new ArrayList<>();
    public static boolean added = false;
    static Boolean updated = false;
    //Handler/Runnable for listeners
    private Handler handler = new Handler();
    private Runnable runner = new Runnable() {
        @Override
        public void run() {
            // Rerun stuff goes below this line

            donationsAdapter = new DonationsAdapter(donations);
            //links the recycler view to the layout manager
            recyclerView.setLayoutManager(layoutManager);
            //links the recyclerview to the donations adapter
            recyclerView.setAdapter(donationsAdapter);

            if(updated == false){
                handler.postDelayed(this, 1000); //Currently set to update every 10 seconds
            }
            //update textview here
            if(donations.isEmpty() == false) {

                donationsAdapter = new DonationsAdapter(donations);
                //links the recycler view to the layout manager
                recyclerView.setLayoutManager(layoutManager);
                //links the recyclerview to the donations adapter
                recyclerView.setAdapter(donationsAdapter);
                handler.removeCallbacks(runner);
            }
//                }


            //Rerun stuff goes above this line
//            handler.postDelayed(this, 6000); //Currently set to update every 10 seconds

        }
    };



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

        // Recycle Stuff

        //links the java to the recycler view
        recyclerView =(RecyclerView) findViewById(R.id.recycle_donation);


        // Says if the recycler has fixed size probably should set to false if we are going to be adding item otherwise leave @ true to improve preformance
        recyclerView.setHasFixedSize(true);


//         addNewDonation("Box","06/2090","Paper Towels","05/11/2019","Y11",
//                 "4","","Thomas Anderson","","804-986-1234");
//         addNewDonation("Box", "03/03/2019", "Canned Beef Broth", "03/03/2019","J1","6","","Tony Stark","TS@Stark.com","");
//         addNewDonation("Can", "09/04/2022", "Corn (M)", "03/03/2019","J2","11","","Steve Rogers","CapAmerica@aol.com","804-555-3232");
//         addNewDonation("Can", "09/04/2022", "Tuna (M)", "03/03/2019","J3","15","","Thor Odinson","","");
//         addNewDonation("Box", "03/03/2019", "Pizza", "03/03/2011","Top Shelf","22","1","Bob jr they stole my hores","bob@gmsil.com","1234567890");
         //generates the test data

       //Test data cardView
        donations = new ArrayList<>();

        initializeData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //update textview here
                if(!added) {
                    generateDonations();

                }
                else{
                    donationsAdapter = new DonationsAdapter(donations);
                    //links the recycler view to the layout manager
                    recyclerView.setLayoutManager(layoutManager);
                    //links the recyclerview to the donations adapter
                    recyclerView.setAdapter(donationsAdapter);
                }
            }

        },3000);
        Collections.sort(donations);

        handler.postDelayed(runner,3000);
//Using prepackage layout manager
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        //creates a new donations adapter object and passes it the test data donations array


        //input from edit text
        //Inventory
        EditText Item;
        EditText Expire;
        EditText Location;
        EditText Quantity;
        EditText Min;
        EditText DateR;
        //Donor
        EditText DonorName;
        EditText DonorPhoneNumber;
        EditText DonorEmail;
        //Button
        Button addDonationButton =(Button) findViewById(R.id.donation_add_database);

        addDonationButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Donation Added",Toast.LENGTH_LONG).show();// Set your own toast  message
            }
        });}


//fills the donations array with test data
    private void initializeData(){
//        donations.add(new Donation("Box", "03/03/2019", "Canned Beef Broth", "03/03/2019","J1","6","","Tony Stark","TS@Stark.com",""));
//        donations.add(new Donation("Can", "09/04/2022", "Corn (M)", "03/03/2019","J2","11","","Steve Rogers","CapAmerica@aol.com","804-555-3232"));
//        donations.add(new Donation("Can", "09/04/2022", "Tuna (M)", "03/03/2019","J3","15","","Thor Odinson","",""));
//        donations.add(new Donation("Box", "03/03/2019", "Pizza", "03/03/2011","Top Shelf","22","1","Bob jr they stole my hores","bob@gmsil.com","1234567890"));
//        donations.add(new Donation("Box", "03/03/2019", "Pizza", "03/03/2011","","22","1","Bob jr they stole my hores","bob@gmsil.com","1234567890"));

    }
//declarations for above
    private RecyclerView recyclerView;
//    private ArrayList<Donation> donations;
    private DonationsAdapter donationsAdapter;

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
            //DO NOTHING
        }
        // If DONORS is selected by manager, Donors.class
        else if (id == R.id.nav_donors) {
            Intent intent = new Intent(getApplicationContext(), Donors.class);
            startActivity(intent);
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

    public void generateDonations(){
        DONATIONS
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                donations.add(new Donation(document.getString("Category"), document.getString("Expiration"),
                                        document.getString("Item"), document.getString("Date Received"), document.getString("Location"),
                                        document.getString("Quantity"),document.getString("Threshold"), document.getString("Name"),
                                        document.getString("Email"), document.getString("Phone")));
                                Log.d("Temp Tag", document.getId() + " => " + document.getData());

                            }
                        } else {
                            Log.d("Abandon Operation", "Error getting documents: ", task.getException());
                        }
                    }
                });
        added = true;

    }





}
