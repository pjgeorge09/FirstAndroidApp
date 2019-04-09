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
import android.widget.TextView;

import com.example.managertabs.Donation.WorkerDonations;
import com.example.managertabs.WorkerInventory;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.Transaction;

import javax.annotation.Nullable;

public class WorkerHomeScreen extends MainActivityWorker
        implements NavigationView.OnNavigationItemSelectedListener {

    static String workerMemo = "";
    TextView textView;
    Other memo = new Other();
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
                                                        Log.d("Log", "Log");
                                                    }
                                                }
                        ).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("string", "string2");
                            }
                        });
                    }
                });
                // This is inefficient here. Use to update non-clickable data such as two boxes one with current inventory one with update TODO
                handler.postDelayed(this, 10000); //Currently set to update every 10 seconds
                //update textview here
                if(memo.getMemo() != null) {
                    textView.setText(memo.getMemo());
                }
            }
        }
    };


    /* onCreate method creates the screen */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the background for the view, includes in the XML links to it's related files and clickables
        setContentView(R.layout.activity_worker_home);

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
        //Set listener on live update
        textView = (TextView)findViewById(R.id.memoWorker);
        textView.setText("Loading...");


        handler.postDelayed(run, 1000); //Currently set to update every 10 seconds
//        //update textview here
//        if(memo.getMemo() != null) {
//            textView.setText(memo.getMemo());
//        }

    }

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
            //super.onBackPressed();
        }
    }

    /* Method used when drawer (tabs) layout is open, listens for button clicks (tab selected) and
    does a screen transition based on received */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // If INVENTORY is selected by worker, go to WorkerInventory.class
        if (id == R.id.nav_inventory) {
            Intent intent = new Intent(getApplicationContext(), WorkerInventory.class);
            startActivity(intent);
        }
        // If INVENTORY is selected by worker, go to WorkerDonations.class
        else if (id == R.id.nav_donations) {
            Intent intent = new Intent(getApplicationContext(), WorkerDonations.class);
            startActivity(intent);
        }
        // If home is selected by worker, go to home
        else if (id == R.id.nav_home) {
//            Intent intent = new Intent(getApplicationContext(), WorkerHomeScreen.class);
//            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
