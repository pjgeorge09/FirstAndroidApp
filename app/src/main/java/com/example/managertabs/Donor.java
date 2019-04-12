package com.example.managertabs;

public class Donor {

    // Class variables
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String contactNumber;
    private Boolean allowContact;


    // Parameterized constructor
    public Donor(String firstName, String lastName, String emailAddress, String contactNumber,
                 Boolean allowContact){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.contactNumber = contactNumber;
        this.allowContact = allowContact;
    }

    // Getter methods
    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getEmailAddress(){
        return this.emailAddress;
    }

    public String getContactNumber(){
        return this.contactNumber;
    }

    public Boolean getAllowContact(){
        return this.allowContact;
    }

    // Setter methods
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }

    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }

    public void setAllowContact(Boolean allowContact){
        this.allowContact = allowContact;
    }
}
