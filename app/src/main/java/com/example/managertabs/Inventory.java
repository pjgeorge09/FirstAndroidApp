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
import android.widget.TextView;
import com.example.managertabs.Donation.DonationsActivity;
import com.example.managertabs.EmployeeFiles.EmployeeActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.Transaction;
import javax.annotation.Nullable;


public class Inventory extends MainActivityManager implements NavigationView.OnNavigationItemSelectedListener {
    //Create the item you will be manipulating here
    Item item = new Item();


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

        // Calls addNewItem method from Master class (Generic item creation, sets values to stock blanks)
        addNewItem("Spaghettios");

        // Calls changeField method from Master class (Sets ANY Field data to whatever you set here. Needs tested for NUMBERS)
        changeField(INVENTORY, "Tomatoes","Location","T117");

        // TODO THIS IS THE WORKING THING. THE METHOD. THIS TRANSACTION WILL BE OUR GETTERS
        // db = database.   runTransaction is the method of getting data from database into variables
        // TODO ===> https://firebase.google.com/docs/firestore/manage-data/transactions
        db.runTransaction(new Transaction.Function<String>(){
            @Override
            public String apply(Transaction transaction) throws FirebaseFirestoreException{
                /* IMPORTANT: This DocumentSnapshot is the item that pulls. This way you can get data from ANYWHERE in the Database.
                 *               <name>     transaction.get(COLLECTION . DOCUMENT("NAME GOES HERE")    */
                DocumentSnapshot snapshot = transaction.get(INVENTORY.document("Canned Tuna"));  //currently set to Canned Tuna needs changed TODO
                // Using Item Class setters, and DocumentSnapshot's "Get String Method"
                // Get String method is a KEY VALUE PAIR. You pass it the Field name and it returns the string
                item.setName(snapshot.getString("item"));
                item.setLocation(snapshot.getString("Location"));
                // Supposedly works with non-string values with get method (REQUIRES CASTING THOUGH)
                item.setQuantity((int)snapshot.get("Quantity"));
                item.setThreshold((int)snapshot.get("Threshold"));
                item.setType(snapshot.getString("Type"));
                //Method gets angry if you change it from String so this line is actually useless. Try setting as void again when get time
                String newPop = snapshot.getString("Memo");
                // Returns to no-where, irrelevant but needed statement
                return newPop;
            }
            // Listeners just add to the log, but I don't have them printing anything helpful. TODO Make better log messages like "Failed at", getLineFault()
        }).addOnSuccessListener(new OnSuccessListener<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        Log.d("Log","Log");
                                    }
                                }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("string","string2");
            }
        });

    }


    //This works now. Button pulls data. Changes Inventory Screen Name to "Can" or whatever you want from database
    public void changeName(View view){

        //live update
        //Currently uses messageDocRef, but it just needs a final variable to manipulate so the initial value of messageDocRef is IRRELEVANT
        // TODO ===> https://firebase.google.com/docs/firestore/query-data/listen
        messageDocRef.addSnapshotListener(MetadataChanges.INCLUDE, new EventListener<DocumentSnapshot>() {
            // On some EVENT (some bit of data changes in the database) do the following actions
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                // Actions being done below (Run new transaction, update Item object class info, then reapply to screen)
                db.runTransaction(new Transaction.Function<String>(){
                    @Override
                    public String apply(Transaction transaction) throws FirebaseFirestoreException{
                        /* IMPORTANT: This DocumentSnapshot is the item that pulls. This way you can get data from ANYWHERE in the Database.
                         *               <name>     transaction.get(COLLECTION . DOCUMENT("NAME GOES HERE")    */
                        DocumentSnapshot snapshot = transaction.get(INVENTORY.document("Canned Tuna"));
                        // Using Item Class setters, and DocumentSnapshot's "Get String Method"
                        // Get String method is a KEY VALUE PAIR. You pass it the Field name and it returns the string
                        item.setName(snapshot.getString("item"));
                        item.setLocation(snapshot.getString("Location"));
                        // Supposedly works with non-string values with get method (REQUIRES CASTING THOUGH)
                        item.setQuantity((int)snapshot.get("Quantity"));
                        item.setThreshold((int)snapshot.get("Threshold"));
                        item.setType(snapshot.getString("Type"));
                        // Irrelevant but required, does nothing hurts nothing. Maybe void later if possible
                        String newPop = snapshot.getString("Memo");
                        return newPop;
                    }
                    // Listeners just add to the log, but I don't have them printing anything helpful. TODO Make better log messages like "Failed at", getLineFault()
                }).addOnSuccessListener(new OnSuccessListener<String>() {
                                            @Override
                                            public void onSuccess(String s) {
                                                Log.d("Log","Log");
                                            }
                                        }
                ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("string","string2");
                    }
                });
            }
        });

        // Create new TextView object based on whatever text box you want to change
        TextView title = (TextView)findViewById(R.id.textView2);
        title.setText(item.getLocation());
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
