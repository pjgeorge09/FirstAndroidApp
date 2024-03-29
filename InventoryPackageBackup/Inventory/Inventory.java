package com.example.managertabs.Inventory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
<<<<<<< HEAD:InventoryPackageBackup/Inventory/Inventory.java
=======
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
>>>>>>> pete:app/src/main/java/com/example/managertabs/Donations.java
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

<<<<<<< HEAD:InventoryPackageBackup/Inventory/Inventory.java
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import FireStoreMethods.FirestoreMethods;

import com.example.managertabs.Donation.DonationsActivity;
import com.example.managertabs.Donors;
import com.example.managertabs.MainActivityManager;
import com.example.managertabs.ManagerHomeScreen;
import com.example.managertabs.R;
import com.example.managertabs.Staff;

public class Inventory extends MainActivityManager
        implements NavigationView.OnNavigationItemSelectedListener {

=======
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Donations extends Master implements NavigationView.OnNavigationItemSelectedListener {

    public ArrayList<QueryDocumentSnapshot> AllDocuments;


    private RecyclerView recyclerView;
    private     RecyclerView.LayoutManager layoutManager;
    private  RecyclerView.Adapter adapter;
    //test data
    private ArrayList<String> TestData;
>>>>>>> pete:app/src/main/java/com/example/managertabs/Donations.java
    /* onCreate method creates the screen */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the background for the view, includes in the XML links to it's related files and clickables
        setContentView(R.layout.activity_manager_inventory);

        // Creates the toolbar at the top of the screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Sets the navigation drawer to still be accessible by the toolbar button. This is the sliding part
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Pete getting an ArrayList of all the documents
        DONATIONS
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                AllDocuments.add(document);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });



        // Sets the side navigation to be able to be called and buttons selected. This is the clickable part.
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
<<<<<<< HEAD:InventoryPackageBackup/Inventory/Inventory.java
    }
=======
// REcycle STUFf
        recyclerView =(RecyclerView) findViewById(R.id.recycle_donation);
        //generates the test data
        TestData = new ArrayList<>();
        int length = 0;
        for (int i=0; i<100; i++) {
            TestData.add("Donation" + i);

        }
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        adapter = new MainAdapter(AllDocuments);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        }


>>>>>>> pete:app/src/main/java/com/example/managertabs/Donations.java



    /* Method used when drawer (tabs) layout is open, listens for button clicks (tab selected) and
    does a screen transition based on received */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // If INVENTORY is selected by manager, go to Inventory.class
        if (id == R.id.nav_inventory) {
            //DO NOTHING
        }
        // If DONATIONS is selected by manager, go to DonationsActivity.class
        else if (id == R.id.nav_donations) {
            Intent intent = new Intent(getApplicationContext(), DonationsActivity.class);
            startActivity(intent);
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
}
