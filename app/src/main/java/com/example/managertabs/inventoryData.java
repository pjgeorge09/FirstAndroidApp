package com.example.managertabs;

import java.util.Comparator;
//object for the inventory tab
public class inventoryData implements Comparable {
    String inventoryCategory;
    String inventoryExpire;
    String inventoryItem;
    String inventoryDateRecived;
    String inventoryLocation;
    String inventoryQuantity;
    String inventoryMin_Threshold;

    //general constructor
    public inventoryData(){

    }

    public inventoryData(String inventoryCategory, String inventoryExpire, String inventoryItem, String inventoryDateRecived, String inventoryLocation, String inventoryQuantity, String inventoryMin_Threshold) {
        this.inventoryCategory = inventoryCategory;
        this.inventoryExpire = inventoryExpire;
        this.inventoryItem = inventoryItem;
        this.inventoryDateRecived = inventoryDateRecived;
        this.inventoryLocation = inventoryLocation;
        this.inventoryQuantity = inventoryQuantity;
        this.inventoryMin_Threshold = inventoryMin_Threshold;
    }


    public String getInventoryCategory() {
        return inventoryCategory;
    }

    public void setInventoryCategory(String inventoryCategory) {
        this.inventoryCategory = inventoryCategory;
    }

    public String getInventoryExpire() {
        return inventoryExpire;
    }

    public void setInventoryExpire(String inventoryExpire) {
        this.inventoryExpire = inventoryExpire;
    }

    public String getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(String inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public String getInventoryDateRecived() {
        return inventoryDateRecived;
    }

    public void setInventoryDateRecived(String inventoryDateRecived) {
        this.inventoryDateRecived = inventoryDateRecived;
    }

    public String getInventoryLocation() {
        return inventoryLocation;
    }

    public void setInventoryLocation(String inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }

    public String getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(String inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public String getInventoryMin_Threshold() {
        return inventoryMin_Threshold;
    }

    public void setInventoryMin_Threshold(String inventoryMin_Threshold) {
        this.inventoryMin_Threshold = inventoryMin_Threshold;


    }

    @Override
    public int compareTo(Object object) {
        String compareField = ((inventoryData)object).getInventoryLocation();
        return 0;
    }

    /*Comparator for sorting the list by LOCATION*/
    public static Comparator<inventoryData> locationComparator = new Comparator<inventoryData>() {
        public int compare(inventoryData s1, inventoryData s2) {
            String item1 = s1.getInventoryLocation().toUpperCase();
            String item2 = s2.getInventoryLocation().toUpperCase();

            //ascending order
            return item1.compareTo(item2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }};

    @Override
    public String toString() {
        String toString = this.inventoryItem + " ";
        return toString;
    }


}

