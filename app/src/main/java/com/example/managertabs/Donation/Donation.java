package com.example.managertabs.Donation;
// object for the DonationsActivity DAta
public class Donation {
    String Date;
    String Item;
    String Quantity;
    String Size;

public  Donation()
{

}

    public Donation(String date, String item, String quantity, String size) {
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