package com.example.managertabs;
//object for the inventory tab
public class inventoryData {
    String inventoryCategory;
    String inventoryExpire;
    String inventoryItem;
    String inventorySize;
    String inventoryLocation;
    String inventoryQuantity;
    String inventoryMin_Threshold;

    public inventoryData(String inventoryCategory, String inventoryExpire, String inventoryItem, String inventorySize, String inventoryLocation, String inventoryQuantity, String inventoryMin_Threshold) {
        this.inventoryCategory = inventoryCategory;
        this.inventoryExpire = inventoryExpire;
        this.inventoryItem = inventoryItem;
        this.inventorySize = inventorySize;
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

    public String getInventorySize() {
        return inventorySize;
    }

    public void setInventorySize(String inventorySize) {
        this.inventorySize = inventorySize;
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
}

