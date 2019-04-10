package com.example.managertabs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import com.example.managertabs.EmployeeFiles.EmployeeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;
import com.example.managertabs.Donation.DonationsActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import javax.annotation.Nullable;

public class ManagerHomeScreen extends MainActivityManager
        implements NavigationView.OnNavigationItemSelectedListener {

     //Manipulatable objects from anywhere in this class that can be altered over-and-over again (Memo might change 5 times while on screen, only one object
     Other memo = new Other();
     TextView textView;
     public boolean set = false;
     private Handler handler = new Handler();
     private Runnable run = new Runnable() {
         @Override
         public void run() {
             {

                 //Set listener on live update
                 messageDocRef.addSnapshotListener(MetadataChanges.INCLUDE, new EventListener<DocumentSnapshot>() {
                     // On some EVENT (some bit of data changes in the database) do the following actions
                     @Override
                     public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                         // Actions being done below (Run new transaction, update Item object class info, then reapply to screen)
                         db.runTransaction(new Transaction.Function<String>() {
                             @Override
                             public String apply(Transaction transaction) throws FirebaseFirestoreException {
                                 /* IMPORTANT: This DocumentSnapshot is the item that pulls. This way you can get data from ANYWHERE in the Database.
                                  *               <name>     transaction.get(COLLECTION . DOCUMENT("NAME GOES HERE")    */
                                 DocumentSnapshot snapshot = transaction.get(OTHER.document("Message"));
                                 // Using Item Class setters, and DocumentSnapshot's "Get String Method"
                                 // Get String method is a KEY VALUE PAIR. You pass it the Field name and it returns the string
//                        item.setName(snapshot.getString("item"));
                                 memo.setMemo(snapshot.getString("Memo"));
//                                 // This handler might not be needed with postdelay added
//                                 new Handler().postDelayed(new Runnable() {
//                                     @Override
//                                     public void run() {
//                                         //update textview here
//                                         textView.setText(memo.getMemo());
//                                     }
//                                 }, 1000);

                                 // Irrelevant but required, does nothing hurts nothing. Maybe void later if possible
                                 String newPop = snapshot.getString("Memo");
                                 return newPop;
                             }
                             // Listeners just add to the log, but I don't have them printing anything helpful. TODO Make better log messages like "Failed at", getLineFault()
                         }).addOnSuccessListener(new OnSuccessListener<String>() {
                                                     @Override
                                                     public void onSuccess(String s) {
                                                         set = true;
                                                         Log.d("Log", "Log");
                                                     }
                                                 }
                         ).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 Log.w("string", "Listener failed");
                             }
                         });
                     }
                 });
                 // This is inefficient here. Use to update non-clickable data such as two boxes one with current inventory one with update TODO
                 if(set == false){
                     handler.postDelayed(this, 7000); //Currently set to update every 10 seconds
                 }
                 //update textview here
                 if(memo.getMemo() != null) {
                     textView.setText(memo.getMemo());
                     handler.removeCallbacks(run);
                 }
             }
         }
     };

    /* onCreate method creates the screen */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkQuantities();
        // Set the background for the view, includes in the XML links to it's related files and clickables
        setContentView(R.layout.activity_manager_home);

        // Creates the toolbar at the top of the screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        String memoString = memoSnapshot.getResult().getString("Memo");

        // Sets the navigation drawer to still be accessible by the toolbar button. This is the sliding part
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Sets the side navigation to be able to be called and buttons selected. This is the clickable part.
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // OnScreenCreate, set the content of the Manager Home Screen to the contents currently in the database (FOR WORKER TOO)
        textView = (TextView)findViewById(R.id.memoBox);
        textView.setText("Loading...");
//
//        /* runTransaction method is a method to get data from the database and pass the info TO VARIABLES */
//        db.runTransaction(new Transaction.Function<String>(){
//            @Override
//            public String apply(Transaction transaction) throws FirebaseFirestoreException {
//                //Critical setup. Set to messageDocRef from Master but CAN BE ANY DOCUMENT. ex) db.collection("Inventory").document("Corn");
//                DocumentSnapshot snapshot = transaction.get(messageDocRef);
//                // newPop is ultimately irrelevant as we are passing data to the static variables at the top.
//                // todo change to void instead of return String if possible
//                String newPop = snapshot.getString("Memo");
//                // Other item class .setMemo method --> DocumentSnapshot method .getString(FieldName) KEY VALUE. Pass it a string field name, get string value
//                memo.setMemo(snapshot.getString("Memo"));
//                /* Handler method waits for data to finish being gotten from the internet
//                 * If no handler, and only partial data, it will abort the operation with no onfail listener
//                  * https://stackoverflow.com/questions/35734963/update-a-textview-in-real-time-using-a-for
//                  *
//                  * BASICALLY this tells the computer to wait 1 second before updating the textView.
//                  * This handler is doing nothing right now until this RunTransaction is updated with the liveListener*/
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        //update textview here
//                        textView.setText(memo.getMemo());
//                    }
//                },15000);
//                // Irrelevant return, affects nothing atm
//                return newPop;
//            }
//            //Standard S/F Listeners.
//        }).addOnSuccessListener(new OnSuccessListener<String>() {
//                                    @Override
//                                    public void onSuccess(String s) {
//                                        Log.d("Create Memo Status"," Succeeded");
//                                    }
//                                }
//        ).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.w("Create Memo Status", " Failed utterly and miserably.");
//            }
//        });


        //***** should CONTINUOUSLY LISTEN

        //*****


        /* Handler method waits for data to finish being gotten from the internet
         * If no handler, and only partial data, it will abort the operation with no onfail listener
         * https://stackoverflow.com/questions/35734963/update-a-textview-in-real-time-using-a-for
         * DO NOT SET LESS THAN 1000 MILLIS
         * BASICALLY this tells the computer to wait 1 second before updating the textView.*/
        handler.postDelayed(run,7000);


    }//End OnCreate

    /* Sets the back button to revert to the last screen. In the case that the drawer is open, it simply closes the drawer instead. */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // If drawer (tabs) are open, close them
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        // If drawer (tabs) are closed, revert to login screen, CLEAR MEMORY
        else {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            // The below code clears the stack so the activity cannot be reached. (Security bug cleared) (I.E. ERASE STACK MEMORY)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
//            super.onBackPressed();
        }
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
//            Intent intent = new Intent(getApplicationContext(), ManagerHomeScreen.class);
//            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /* A method to update in the database what the memo should be set to */
    public void updateMemo(View view){

        //Pull the TextView object from content_manager_home.xml
        EditText editText = (EditText)findViewById(R.id.memoBox);
        // Must convert from Object --> String
        String update = editText.getText().toString();
        // Master.java changeField method updates this field (PERMANENT NAME / FIELD POINTER)
        changeField(OTHER, "Message","Memo", update);
    }


    void checkQuantities(){
        INVENTORY
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String docToName = document.getId();
                                String quan = document.getString("Quantity");
                                String min = document.getString("Threshold");
                                int current = Integer.parseInt(quan);
                                int minimum = Integer.parseInt(min);
                                if(current < minimum){
                                    pushNotification(docToName);
                                }
                                Log.d("Temp Tag", document.getId() + " => " + document.getData());

                            }
                        } else {
                            Log.d("Abandon Operation", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    void pushNotification(String aLowInventoryItem){

    }
}
