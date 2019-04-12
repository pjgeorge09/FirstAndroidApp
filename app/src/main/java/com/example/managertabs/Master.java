package com.example.managertabs;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.StructuredQuery;
import com.squareup.okhttp.internal.DiskLruCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Master extends AppCompatActivity {
    //I want these to be accessible from EVERYWHERE - Pete    temporary note
    public FirebaseFirestore db = FirebaseFirestore.getInstance();

    /*---------------------COLLECTION DECLARATIONS-----------------*/
    public final CollectionReference INVENTORY = db.collection("Inventory");
    public CollectionReference DONATIONS = db.collection("Donations");
    public CollectionReference EMPLOYEES = db.collection("Employees");
    public CollectionReference DONORS = db.collection("Donors");
    public CollectionReference OTHER = db.collection("Other");

    final DocumentReference messageDocRef = OTHER.document("Message");
    final DocumentReference cornDocRef = INVENTORY.document("Corn");

    //TODO Create ITEM class, create Array of ITEMS

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
    }


    /* This method changes a FIELD.
        @Arg $1  = the COLLECTION NAME
        @Arg $2  = the DOCUMENT NAME
        @Arg $3  = the FIELD NAME
        @Arg $4  = the new information you want there
        TODO LISTENER FOR IF FAIL*/
    public void changeField(CollectionReference collection, String docName,String fieldName, String updatedInfo){
//ex)   INVENTORY . "Green Beans" .
        collection.document(docName).update(
//ex cont)      "Location" ,  "A260A"
                fieldName, updatedInfo
        );
    }

    /* Creating a method to add entirely a new inventory item with null values
      * @Arg documentName = This gives the Document a readable name instead of the terrible hash values Firestore auto assigns in the database */
    public void addNewItem(String documentName){

        //Create a map object of EXACTLY <String, Object> = HashMap (Best speed)
        Map<String, Object> toAdd = new HashMap<>();
        //Set null values (Not necessarily needed but helps)
        toAdd.put("Location", "____");
        toAdd.put("Quantity", 0);
        toAdd.put("Threshold", 1);
        toAdd.put("Type", "____");
        toAdd.put("item", "____");

        db.collection("Inventory").document(documentName)
                // .set(toAdd) adds it to the database
                .set(toAdd).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void documentReference) {
                        // Decently-written Log. Should implement this everywhere tbh
                        Log.d("Firebase data added", "doc w ID :");
                    }
                });
    }

    /* Method to create a new worker with null value fields at first
     * @Arg documentName = This gives the Document a readable name instead of the terrible hash values Firestore auto assigns in the database
     * Example: addNewWorker("Sarah Cole")      Document name = Sarah Cole*/
    public void addNewWorkerUtil(String documentName){

        //Create a map object of EXACTLY <String, Object> = HashMap (Best speed)
        Map<String, Object> toAdd = new HashMap<>();
        //Set null values (Not necessarily needed but helps)
        toAdd.put("First Name", "____");
        toAdd.put("Last Name", "____");
        toAdd.put("Contact Number", "____");
        toAdd.put("Email", "____");
        toAdd.put("uid", 00);
        //
        db.collection("Employees").document(documentName)
                // .set(toAdd) adds it to the database
                .set(toAdd).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void documentReference) {
                // Decently-written Log. Should implement this everywhere tbh
                Log.d("Firebase Employee added", "doc w ID :" );
            }
        });
    }

    public void addNewEmployee(String documentName, String aUID, String aFirstName, String aLastName,
                               String anEmail, String aContactNumber){
        // Name the document so it is not some zd83g8ba8s84df garbage
        addNewWorkerUtil(documentName);


        changeField(EMPLOYEES, documentName,"First Name", aFirstName);
        changeField(EMPLOYEES, documentName,"Last Name", aLastName);
        changeField(EMPLOYEES, documentName,"uid", aUID);
        changeField(EMPLOYEES, documentName,"Email", anEmail);
        changeField(EMPLOYEES, documentName,"Contact Number", aContactNumber);

    }




}
