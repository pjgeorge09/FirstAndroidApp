package com.example.managertabs;

public class Donations_Data {
    private String Date;
    private String Item;
    private String Quantity;
    private String Size;

    public Donations_Data() {
    }

    public Donations_Data(String date, String item, String quantity, String size) {
        Date = date;
        Item = item;
        Quantity = quantity;
        Size = size;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }
}
