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
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import FireStoreMethods.FirestoreMethods;

public class Inventory extends MainActivityManager
        implements NavigationView.OnNavigationItemSelectedListener {

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

        // Sets the side navigation to be able to be called and buttons selected. This is the clickable part.
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        //FOR PETE This line creates a FireStoreMethod object which can be used to call its methods
        FirestoreMethods getFirestoreMethod = new FirestoreMethods();

        //Initialize the database, it is linked to my android already
        /* FOR BRYAN - We create an item, a map, that can receive ANY object (int, string etc)  */
        Map<String, Object> item = new HashMap<>();

        // Sample data
        //TODO make this an inferface
//        item.put("item", "Green Beans");
//        item.put("Type", "Can");
//        item.put("Location", "A101");
//        item.put("Quantity", 22);
//        item.put("Threshold", 15);


        //Messages = rows in SQL. It's like a set. so maybe another example  "John Temporary"
//        db.collection("Inventory").document("Green Beans")
//                .set(item)
//                // ON SUCCESS
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void documentReference) {
//                        Log.d("FIREBASE_DATA_ADDED", "Document added with ID: ");
//                    }
//                })
//                //ON FAILURE
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("FIREBASE_DATA_ERROR", "Document failed to add, exception backtrace: " + Arrays.toString(e.getStackTrace()));
//                    }
//                });


        // Sets aString to the item location of Green Beans
        //String aString = getFirestoreMethod.getItemThreshold("Green Beans");
        // Makes a TextView and sets it to textView2
        // Casting here might be unnecessary?
        TextView tv = (TextView)findViewById(R.id.textView2);

        // Sets the text of textView2 to the item location of Green Beans
        tv.setText(documentSnapshotTask.getResult().getString("Location"));
        //tv.setText(documentSnapshotTask.getResult().get("Threshold").toString());
        //Item item1 = new Item();

        // SET LOCATION FROM MASTER METHOD, CURRENTLY SET TO GREEN BEANS ONLY, MODIFIABLE
//        setLocation("Pete's Test");
        // THERE IS A ONE-SCREEN DELAY     TODO
        //ACTUALLY SETTING THE NEW REFERENCE NAME
        tv.setText(documentSnapshotTask.getResult().getString("Location"));

    }



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
        // If DONATIONS is selected by manager, go to Donations.class
        else if (id == R.id.nav_donations) {
            Intent intent = new Intent(getApplicationContext(), Donations.class);
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
