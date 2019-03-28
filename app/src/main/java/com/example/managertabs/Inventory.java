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

import com.example.managertabs.Donation.Donation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.Transaction;

import javax.annotation.Nullable;


public class Inventory extends MainActivityManager




        implements NavigationView.OnNavigationItemSelectedListener {
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



        //FOR PETE This line creates a FireStoreMethod object which can be used to call its methods
//        FirestoreMethods getFirestoreMethod = new FirestoreMethods();

        //works
        addNewItem("Spaghettios");

        //Initialize the database, it is linked to my android already
        /* FOR BRYAN - We create an item, a map, that can receive ANY object (int, string etc)  */
//        Map<String, Object> Soup = new HashMap<>();
//
//        // Sample data
//        //TODO make this an inferface
//        Soup.put("Type", "Can");
//        Soup.put("Location", "S5");
//        Soup.put("Quantity", 300);
//        Soup.put("Threshold", 100);

//
//        //Messages = rows in SQL. It's like a set. so maybe another example  "John Temporary"
//        db.collection("Inventory").document("Soup")
//                .set(Soup)
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


//        public void changeField(CollectionReference collection, String docName,String fieldName, String updatedInfo){
////ex)   INVENTORY . "Green Beans" .
//            collection.document(docName).update(
////ex cont)      "Location" ,  "A260A"
//                    fieldName, updatedInfo
//            );
//        }
//        final Item item = new Item();
        //Works
        changeField(INVENTORY, "Tomatoes","Location","T117");

        // TODO THIS IS THE WORKING THING. THE METHOD. tHIS TRANSACTION WILL BE OUR GETTERS
        db.runTransaction(new Transaction.Function<String>(){
            @Override
            public String apply(Transaction transaction) throws FirebaseFirestoreException{
                DocumentSnapshot snapshot = transaction.get(INVENTORY.document("Canned Tuna"));
//                item = snapshot.toObject(Item.class);
                item.setName(snapshot.getString("item"));
                item.setLocation(snapshot.getString("Location"));
                item.setQuantity((int)snapshot.get("Quantity"));
                item.setThreshold((int)snapshot.get("Threshold"));
                item.setType(snapshot.getString("Type"));
                String newPop = snapshot.getString("Memo");
//                item.setName(newPop); //todo THIS IS A PRE INIT OBJECT
                return newPop;
            }
        }).addOnSuccessListener(new OnSuccessListener<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        Log.d("Dick","Ass");
                                    }
                                }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("string","string2");
            }
        });





        // Sets aString to the item location of Green Beans
        //String aString = getFirestoreMethod.getItemThreshold("Green Beans");
        // Makes a TextView and sets it to textView2
        // Casting here might be unnecessary?
//        TextView tv = (TextView)findViewById(R.id.textView2);
//        // Sets the text of textView2 to the item location of Green Beans
//        tv.setText(documentSnapshotTask.getResult().getString("Location"));
//        //tv.setText(documentSnapshotTask.getResult().get("Threshold").toString());
//        //Item item1 = new Item();
//
//        // SET LOCATION FROM MASTER METHOD, CURRENTLY SET TO GREEN BEANS ONLY, MODIFIABLE
////        setLocation("Pete's Test");
//        // THERE IS A ONE-SCREEN DELAY     TODO
//        //ACTUALLY SETTING THE NEW REFERENCE NAME
//        tv.setText(documentSnapshotTask.getResult().getString("Location"));

    }


    //This works now. Button pulls data. Changes Inventory Screen Name to "Can" or whatever you want from database
    public void changeName(View view){
        messageDocRef.addSnapshotListener(MetadataChanges.INCLUDE, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                // ON EVENT UPDATE ITEM
                db.runTransaction(new Transaction.Function<String>(){
                    @Override
                    public String apply(Transaction transaction) throws FirebaseFirestoreException{
                        DocumentSnapshot snapshot = transaction.get(INVENTORY.document("Canned Tuna"));
//                item = snapshot.toObject(Item.class);
                        item.setName(snapshot.getString("item"));
                        item.setLocation(snapshot.getString("Location"));
                        item.setQuantity((int)snapshot.get("Quantity"));
                        item.setThreshold((int)snapshot.get("Threshold"));
                        item.setType(snapshot.getString("Type"));
                        String newPop = snapshot.getString("Memo");

//                item.setName(newPop); //todo THIS IS A PRE INIT OBJECT
                        return newPop;
                    }
                }).addOnSuccessListener(new OnSuccessListener<String>() {
                                            @Override
                                            public void onSuccess(String s) {
                                                Log.d("Dick","Ass");
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
        TextView title = (TextView)findViewById(R.id.textView2);
//        title.setText(documentSnapshotTask.getResult().getString("Type"));
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
            Intent intent = new Intent(getApplicationContext(), Donation.class);
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
