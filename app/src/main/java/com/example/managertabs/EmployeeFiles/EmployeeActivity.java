package com.example.managertabs.EmployeeFiles;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.managertabs.Donation.Donation;
import com.example.managertabs.Donation.DonationsActivity;
import com.example.managertabs.Donors;
import com.example.managertabs.Inventory;
import com.example.managertabs.MainActivityManager;
import com.example.managertabs.ManagerHomeScreen;
import com.example.managertabs.Master;
import com.example.managertabs.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// NOTE: Many unnecessary imports, talk to group and make sure about the non-needed ones.

/**
 * Class to manage the activity for the Employee page
 */
public class EmployeeActivity extends MainActivityManager
        implements NavigationView.OnNavigationItemSelectedListener {

    // Private variables
    // Employee list holds a list of employee objects
    //
    static ArrayList<Employee> employees = new ArrayList<>();
    static Boolean added = false;
    Employee tempEmployee = new Employee();
    private RecyclerView rv;
    private EmployeeAdapter employeeAdapter;

    //Handler/Runnable for listeners
    private Handler handler = new Handler();
    private Runnable runner = new Runnable() {
        @Override
        public void run() {
            // Rerun stuff goes below this line


            //Rerun stuff goes above this line
            handler.postDelayed(this, 15000); //Currently set to update every 10 seconds
        }
    };

    /* onCreate method creates the screen */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the background for the view, includes in the XML links to it's related files and clickables
        setContentView(R.layout.activity_employees);

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
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        // Declares the layout manager for our recycler view
        // Linear layout chosen for this view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        // Calls method to add employees to the employee list
//        employees = new ArrayList<>();

        // This handler might not be needed with postdelay added
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //update textview here
                if(!added) {
                    generateEmployees();
                    added = true;

                }
            }
        },0);
        initializeData();




//        handler.postDelayed(runner,1000);
        // Initialization of our employee adapter
        employeeAdapter = new EmployeeAdapter(employees);

        // Sets the adapter to the recycler view
        rv.setAdapter(employeeAdapter);
        // todo addNewEmployee method works as below perfectly, and in real time
//        addNewEmployee("103", "103", "Paul","Guller","Paul@gmail.com","804-555-3456");




    }


        // Initializes employee list as an ArrayList and adds hardcoded employee objects to it
        // Data initialized is hard coded and needs to be changed to data pulled from database
        private void initializeData() {

//            employees = new ArrayList

//            employees.add(new Employee(9, "John", "Temporary", "JohnTemporary@gmail.com",
//                    "2468 Cary St. Richmond, VA 23220", "03/03/03", R.drawable.boy));
//
//            employees.add(new Employee(10, "Bryan", "Hilldrup", "hilldrupbf@vcu.edu",
//                    "12345 Main St. Richmond, VA 23220", "07/07/07", R.drawable.man));
//
//            employees.add(new Employee(11, "Peter", "George", "petergeorge@vcu.edu",
//                    "9889 Broad St. Richmond, Va 23220", "12/31/00", R.drawable.girl1));
        }




        /* PETES CODE, find a way to implement for database implementation

        //Initialize the database, it is linked to my android already
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // FOR BRYAN - We create an item, a map, that can receive ANY object (int, string etc)
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



    } PETES END OF ONCREATE FUNCTION
        */

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
            Intent intent = new Intent(getApplicationContext(), DonationsActivity.class);
            startActivity(intent);
        }
        // If DONORS is selected by manager, Donors.class
        else if (id == R.id.nav_donors) {
            Intent intent = new Intent(getApplicationContext(), Donors.class);
            startActivity(intent);
        }
        // If STAFF is selected by manager, go to EmployeeActivity.class
        else if (id == R.id.nav_staff) {
            //DO NOTHING
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
    public void generateEmployees(){
        EMPLOYEES
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String localFN = document.getString("First Name");
                                String localLN = document.getString("Last Name");
                                String localEmail = document.getString("Email");
                                String localUID = document.getString("uid");
                                String localCN = document.getString("Contact Number");
//                                Employee newEmployee = new Employee(localUID,localFN,localLN,localEmail,localCN);
//                                tempEmployee.setFirstName(document.getString("First Name"));
//                                tempEmployee.setLastName(document.getString("Last Name"));
//                                tempEmployee.setEmailAddress(document.getString("Email"));
//                                tempEmployee.setUid(document.getString("uid"));
//                                tempEmployee.setContactNumber(document.getString("Contact Number"));

                                employees.add(new Employee(localUID,localFN,localLN,localEmail,localCN));
                                Log.d("Temp Tag", document.getId() + " => " + document.getData());

                            }
                        } else {
                            Log.d("Abandon Operation", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
