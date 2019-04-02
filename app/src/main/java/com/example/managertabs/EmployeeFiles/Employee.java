package com.example.managertabs.EmployeeFiles;

import android.util.Log;

import com.example.managertabs.Master;
import java.util.HashMap;
import java.util.Map;
// FOLLOWING IMPORT CAN BE DELETED WHEN ADD METHODS ARE MOVED
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;

// CLASS DOES NOT NEED TO EXTEND MASTER
// extends Master CAN BE DELETED WHEN THREE ADD METHODS ARE MOVED OUT OF CLASS
public class Employee extends Master {

    // Class variables
    private String uid;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String contactNumber;


    private int profilePicture;

    public Employee(){

        /*
        toAdd.put("First Name", "____");
        toAdd.put("Last Name", "____");
        toAdd.put("Contact Number", "____");
        toAdd.put("Email Address", "____");
        toAdd.put("WorkerID", 00);
         */
    }

    // Parameterized constructor
    public Employee(String uid, String firstName, String lastName, String emailAddress,
                    String homeAddress, String birthDate, int profilePicture){

        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.contactNumber = homeAddress;
        this.profilePicture = profilePicture;
    }

    // Parameterized constructor
    public Employee(String uid, String firstName, String lastName, String emailAddress,
                    String aContactNumber){

        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.contactNumber = aContactNumber;
    }



    // FOLLOWING THREE METHODS NEED TO BE MOVED OUT OF EMPLOYEE CLASS

       /* This method changes a FIELD.
        @Param $1  = the COLLECTION NAME
        @Param $2  = the DOCUMENT NAME
        @Param $3  = the FIELD NAME
        @Param $4  = the new information you want there
        TODO LISTENER FOR IF FAIL*/
    public void changeField(CollectionReference collection, String docName,String fieldName, String updatedInfo){
//ex)   INVENTORY . "Green Beans" .
        collection.document(docName).update(
//ex cont)      "Location" ,  "A260A"
                fieldName, updatedInfo
        );

    }

    // Getter methods
    public String getUid(){
        return this.uid;
    }

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

    public int getProfilePicture(){
        return this.profilePicture;
    }

    // Setter methods
    public void setUid(String uid){
        this.uid = uid;
    }

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

    public void setProfilePicture(int profilePicture){
        this.profilePicture = profilePicture;
    }


}
