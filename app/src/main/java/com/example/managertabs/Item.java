package com.example.managertabs;

public class Item{

    // FIELDS
    private String location;
    private int quantity;
    private int threshold;
    private String type;
    private String name;

    //Standard parameterized constructor
    public Item(String location, int quantity, int threshold, String type, String name) {
        this.location = location;
        this.quantity = quantity;
        this.threshold = threshold;
        this.type = type;
        this.name = name;
    }

    //default constructor
    public Item() {
    }



    //Standard

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

    public void setLocation(String location) {
        this.location = location;
    }

}
