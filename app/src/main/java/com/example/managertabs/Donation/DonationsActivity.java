package com.example.managertabs.Donation;

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
import android.view.MenuItem;

import com.example.managertabs.Donors;
import com.example.managertabs.EmployeeFiles.EmployeeActivity;
import com.example.managertabs.Inventory;
import com.example.managertabs.ManagerHomeScreen;
import com.example.managertabs.R;

import java.util.ArrayList;
import java.util.List;

public class DonationsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    /* Unsure if needed, should be declared elsewhere?
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

<<<<<<< HEAD:app/src/main/java/com/example/managertabs/Donations.java
public class Donations extends Master implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private     RecyclerView.LayoutManager layoutManager;
    private  RecyclerView.Adapter adapter;
=======
>>>>>>> dummy:app/src/main/java/com/example/managertabs/Donation/DonationsActivity.java
    //test data
    private ArrayList<String> TestData;
    //Formal Test Data wtih Object
    private ArrayList<Donation> ProperData;
    */

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

        //generates the test data

       //Test data cardView
        donations = new ArrayList<>();

        initializeData();

//Using prepackage layout manager
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        //creates a new donations adapter object and passes it the test data donations array
        donationsAdapter = new DonationsAdapter(donations);
        //links the recycler view to the layout manager
        recyclerView.setLayoutManager(layoutManager);
        //links the recyclerview to the donations adapter
        recyclerView.setAdapter(donationsAdapter);
        }
//fills the donations array with test data
    private void initializeData(){
        donations.add(new Donation("Box", "03/03/2019", "Pizza", "03/03/2011","Top Shelf","22","1","Bob jr they stole my hores","bob@gmsil.com","1234567890"));
        donations.add(new Donation("Box", "03/03/2019", "Pizza", "03/03/2011","Top Shelf","22","1","Bob jr they stole my hores","bob@gmsil.com","1234567890"));
        donations.add(new Donation("Box", "03/03/2019", "Pizza", "03/03/2011","Top Shelf","22","1","Bob jr they stole my hores","bob@gmsil.com","1234567890"));
        donations.add(new Donation("Box", "03/03/2019", "Pizza", "03/03/2011","Top Shelf","22","1","Bob jr they stole my hores","bob@gmsil.com","1234567890"));
        donations.add(new Donation("Box", "03/03/2019", "Pizza", "03/03/2011","Top Shelf","22","1","Bob jr they stole my hores","bob@gmsil.com","1234567890"));
        donations.add(new Donation("Box", "03/03/2019", "Pizza", "03/03/2011","Top Shelf","22","1","Bob jr they stole my hores","bob@gmsil.com","1234567890"));
        donations.add(new Donation("Box", "03/03/2019", "Pizza", "03/03/2011","Top Shelf","22","1","Bob jr they stole my hores","bob@gmsil.com","1234567890"));
        donations.add(new Donation("Box", "03/03/2019", "Pizza", "03/03/2011","Top Shelf","22","1","Bob jr they stole my hores","bob@gmsil.com","1234567890"));
        donations.add(new Donation("Box", "03/03/2019", "Pizza", "03/03/2011","Top Shelf","22","1","Bob jr they stole my hores","bob@gmsil.com","1234567890"));
        donations.add(new Donation("Box", "03/03/2019", "Pizza", "03/03/2011","Top Shelf","22","1","Bob jr they stole my hores","bob@gmsil.com","1234567890"));
        donations.add(new Donation("Box", "03/03/2019", "Pizza", "03/03/2011","Top Shelf","22","1","Bob jr they stole my hores","bob@gmsil.com","1234567890"));

    }
//declarations for above
    private RecyclerView recyclerView;
    private List<Donation> donations;
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
}
