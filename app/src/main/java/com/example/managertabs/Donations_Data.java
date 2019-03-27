package com.example.managertabs;
// object for the Donations DAta
public class Donations_Data {
    String Date;
    String Item;
    int Quantity;
    String Size;



    public Donations_Data(String date, String item, int quantity, String size) {
        this.Date = date;
        this.Item = item;
        this.Quantity = quantity;
        this.Size = size;
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

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }
}
