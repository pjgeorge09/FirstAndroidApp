package com.example.managertabs;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Item extends Master{


    // FIELDS
    private String location;
    private int quantity;
    private int threshold;
    private String type;
    private String name;
    private Map data;

    private Map<String, Object> thisMap = new HashMap<>();



//    String test = documentSnapshotTask.getResult().getString("Location");

    public Item(String location, int quantity, int threshold, String type, String name) {
        this.location = location;
        this.quantity = quantity;
        this.threshold = threshold;
        this.type = type;
        this.name = name;
    }

    public Item() {
    }

// Peter's version
//    public String getLocation(){
//        return documentSnapshotTask.getResult().getString("Location");
//    }


    //PREMADE GET SETS SUPPOSEDLY UPDATE IN REAL TIME

    public String getLocation() {
        return location;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public void setType(String type) {
        this.type = type;
    }

}
