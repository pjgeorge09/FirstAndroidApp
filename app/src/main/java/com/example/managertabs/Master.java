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
    public CollectionReference INVENTORY = db.collection("Inventory");
    public CollectionReference DONATIONS = db.collection("Donations");
    public CollectionReference EMPLOYEES = db.collection("Employees");
    public CollectionReference OTHER = db.collection("Other");

        //THESE TASKS ARE ASSHOLES DO NOT USE THEM
//    Task<DocumentSnapshot> documentSnapshotTask = db.collection("Inventory").document("Green Beans").get();
//    //This one should be a constant field that gets updated.
//    Task<DocumentSnapshot> memoSnapshot = db.collection("Other").document("Message").get() ;
    String TAG = "TAG";
    final DocumentReference messageDocRef = OTHER.document("Message");
    //TODO Create ITEM class, create Array of ITEMS

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);



    }

    // Delete method, its defined in FireStoreMethods.java
    /*
    public String getItemLocation(DocumentReference anItem) {
        // THIS WORKS

        DocumentReference docRef = db.collection("Inventory").document("Green Beans");
        return docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String location = documentSnapshot.get("Location").toString();
            }

        }).toString();
    }
    */


    /* This method changes a FIELD.
        @Param $1  = the COLLECTION NAME
        @Param $2  = the DOCUMENT NAME
        @Param $3  = the FIELD NAME
        @Param $4  = the new information you want there
        TODO LISTENER FOR IF FAIL*/
    public void changeField(CollectionReference collection, String docName,String fieldName, String updatedInfo){
//ex)   INVENTORY . "Green Beans" .
        collection.document(docName).update(
//ex cont)      "Location" ,  "A260A"
                fieldName, updatedInfo
        );
    }

    /* Creating a method to add entirely a new inventory item with null values    */
    public void addNewItem(String documentName){
        Map<String, Object> toAdd = new HashMap<>();
        toAdd.put("Location", "____");
        toAdd.put("Quantity", 0);
        toAdd.put("Threshold", 1);
        toAdd.put("Type", "____");
        toAdd.put("item", "____");

        // CONSIDER ADDING ONSUCCESS LISTENER
        db.collection("Inventory").document(documentName)
                .set(toAdd).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void documentReference) {
                        Log.d("Firebase data added", "doc w ID :");
                    }

        });

    }

    public void addNewWorker(String documentName){
        Map<String, Object> toAdd = new HashMap<>();
        toAdd.put("First Name", "____");
        toAdd.put("Last Name", "____");
        toAdd.put("Contact Number", "____");
        toAdd.put("Email Address", "____");
        toAdd.put("WorkerID", 00);

        db.collection("Employees").document(documentName).set(toAdd);
    }



}
