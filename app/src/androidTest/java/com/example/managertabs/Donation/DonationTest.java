package com.example.managertabs.Donation;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DonationTest {
    //makes a donation object to test getters and setter
    Donation test =new Donation("03/22/1901", "Beans", "500", "Small");

    @Before
    //makes a donation object to test getters and setter
    public void setUp()  {
      Donation test =new Donation("03/22/1901", "Beans", "500", "Small");

    }

    @Test
    public void getDate() {
        String getDate=test.getDate();
        assertEquals(getDate,"03/22/1901");
    }

    @Test
    public void setDate() {
        test.setDate("01/01/1920");
        String getDate=test.getDate();
        assertEquals(getDate,"01/01/1920");
    }

    @Test
    public void getItem() {
        String getItem=test.getItem();
        assertEquals(getItem,"Beans");

    }

    @Test
    public void setItem() {
        test.setItem("Pizza");
        String getItem=test.getItem();
        assertEquals(getItem,"Pizza");
    }

    @Test
    public void getQuantity() {
        String getItem=test.getQuantity();
        assertEquals(getItem,"500");
    }

    @Test
    public void setQuantity() {
        test.setQuantity("50");
        String getItem=test.getQuantity();
        assertEquals(getItem,"50");
    }

    @Test
    public void getSize() {
        String getItem=test.getSize();
        assertEquals(getItem,"Small");
    }

    @Test
    public void setSize() {
        test.setSize("Large");
        String getItem=test.getSize();
        assertEquals(getItem,"Large");

    }
}