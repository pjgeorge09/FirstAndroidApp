package com.example.managertabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.managertabs.Donation.Donation;
import com.example.managertabs.Donation.DonationsActivity;
import com.example.managertabs.Inventory;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Staff extends MainActivityManager
        implements NavigationView.OnNavigationItemSelectedListener {

        // ImageView declarations for profile pictures
        private ImageView profile1;
        private ImageView profile2;
        private ImageView profile3;
        private ImageView profile4;
        private ImageView profile5;

        // Button declaration for adding worker
        private Button addWorkButton;

    /* onCreate method creates the screen */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the background for the view, includes in the XML links to it's related files and clickables
        setContentView(R.layout.activity_staff);

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

        // Sets ImageViews variables to layout imageviews
        profile1 = (ImageView) findViewById(R.id.imageView2);
        profile2 = (ImageView) findViewById(R.id.imageView3);
        profile3 = (ImageView) findViewById(R.id.imageView4);
        profile4 = (ImageView) findViewById(R.id.imageView5);
        profile5 = (ImageView) findViewById(R.id.imageView6);
        // Assigns profile .png pictures to variables.
        profile1.setImageResource(R.drawable.girl1);
        profile2.setImageResource(R.drawable.boy);
        profile3.setImageResource(R.drawable.girl2);
        profile4.setImageResource(R.drawable.man);
        profile5.setImageResource(R.drawable.girl3);
        // Set Button variables to button layout
        addWorkButton = findViewById(R.id.btnAddWork);

        //Initialize the database, it is linked to my android already
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        /* FOR BRYAN - We create an item, a map, that can receive ANY object (int, string etc)  */
        Map<String, Object> worker = new HashMap<>();
        // Sample data
        worker.put("firstName", "Peter");
        worker.put("lastName", "George");
        worker.put("eid", 12456);

        //Messages = rows in SQL. It's like a set. so maybe another example  "John Temporary"
        db.collection("Employees")
                .add(worker)
                // ON SUCCESS
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("FIREBASE_DATA_ADDED", "Document added with ID: " + documentReference.getId());
                    }
                })
                //ON FAILURE
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("FIREBASE_DATA_ERROR", "Document failed to add, exception backtrace: " + Arrays.toString(e.getStackTrace()));
                    }
                });

        // Created listener for Add Worker button to bring new activity
        addWorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Staff.this, AddWorker.class);
                startActivity(intent);
            }
        });

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
            finish();
            startActivity(intent);
        }
        // If DONATIONS is selected by manager, go to DonationsActivity.class
        else if (id == R.id.nav_donations) {
            Intent intent = new Intent(getApplicationContext(), Donation.class);
            finish();
            startActivity(intent);
        }
        // If DONORS is selected by manager, Donors.class
        else if (id == R.id.nav_donors) {
            Intent intent = new Intent(getApplicationContext(), Donors.class);
            finish();
            startActivity(intent);
        }
        // If STAFF is selected by manager, go to Staff.class
        else if (id == R.id.nav_staff) {
            //DO NOTHING
        }
        // If ______ is selected by manager, go to ______
        else if (id == R.id.nav_email) {
        }
        // If home is selected by manager, go to home
        else if (id == R.id.nav_home) {
            Intent intent = new Intent(getApplicationContext(), ManagerHomeScreen.class);
            finish();
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
