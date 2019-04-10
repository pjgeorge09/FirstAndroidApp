package com.example.managertabs.Donation;
// object for the DonationsActivity DAta
public class Donation {
   //Fields
    String Date;
    String Item;
    String Quantity;
    String Size;

public  Donation()
{

}
//constructor to pass the parameters to the object
    public Donation(String date, String item, String quantity, String size) {
        this.Date = date;
        this.Item = item;
        this.Quantity = quantity;
        this.Size = size;
    }
//getters and setter for each field above
    public String getDate() {
    if(Date==null){
        throw new IllegalArgumentException();
    }
    return Date;
    }

    public void setDate(String date) {
        if(date==null){
            throw new IllegalArgumentException();
        }
        Date = date;
    }

    public String getItem() {
        if(Item==null){
            throw new IllegalArgumentException();
        }
        return Item;
    }

    public void setItem(String item) {
        if(item==null){
            throw new IllegalArgumentException();
        }
        Item = item;
    }

    public String getQuantity() {
        if(Quantity==null){
            throw new IllegalArgumentException();
        }

        return Quantity;
    }

    public void setQuantity(String quantity) {
        if(quantity==null){
            throw new IllegalArgumentException();
        }
        Quantity = quantity;
    }

    public String getSize() {
        if(Size==null){
            throw new IllegalArgumentException();
        }
        return Size;
    }

    public void setSize(String size) {
        if(size==null){
            throw new IllegalArgumentException();
        }
        Size = size;
    }
}
