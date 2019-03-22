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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Master extends AppCompatActivity {
    //I want these to be accessible from EVERYWHERE - Pete    temporary note
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference INVENTORY = db.collection("Inventory");
    CollectionReference EMPLOYEES = db.collection("Employees");
    ArrayList<DocumentReference> ITEM = new ArrayList<>();
    ArrayList<DocumentReference> EMPLOYEE = new ArrayList<>();

    String TAG = "TAG";

    //TODO Create ITEM class, create Array of ITEMS

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        generateInventoryDocuments(INVENTORY);
        generateEmployeeDocuments(EMPLOYEES);
    }

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

    public void generateInventoryDocuments(CollectionReference someCollection) {
        someCollection
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                ITEM.add(document.getReference());

                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    public void generateEmployeeDocuments(CollectionReference someCollection) {
        someCollection
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                EMPLOYEE.add(document.getReference());

                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}
