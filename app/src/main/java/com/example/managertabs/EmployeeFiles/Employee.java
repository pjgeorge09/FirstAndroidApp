package com.example.managertabs.EmployeeFiles;

import com.example.managertabs.Master;
import java.util.HashMap;
import java.util.Map;
// FOLLOWING IMPORT CAN BE DELETED WHEN ADD METHODS ARE MOVED
import com.google.firebase.firestore.CollectionReference;

// CLASS DOES NOT NEED TO EXTEND MASTER
// extends Master CAN BE DELETED WHEN THREE ADD METHODS ARE MOVED OUT OF CLASS
public class Employee extends Master {

    // Class variables
    private int uid;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String homeAddress;
    private String birthDate;
    private int profilePicture;

    // Default constructor
    public Employee(int uid, String firstName, String lastName, String emailAddress,
                    String homeAddress, String birthDate, int profilePicture){

        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.homeAddress = homeAddress;
        this.birthDate = birthDate;
        this.profilePicture = profilePicture;
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

    /* Creating a method to add entirely a new inventory item with null values    */
    // PROBABLY NOT NEEDED FOR THIS CLASS
    public void addNewItem(String documentName){
        Map<String, Object> toAdd = new HashMap<>();
        toAdd.put("Location", "____");
        toAdd.put("Quantity", 0);
        toAdd.put("Threshold", 1);
        toAdd.put("Type", "____");
        toAdd.put("item", "____");

        // CONSIDER ADDING ONSUCCESS LISTENER
        db.collection("Inventory").document(documentName)
                .set(toAdd);

    }

    // Method to add new worker, PROBABLY NOT NEEDED IN THIS CLASS
    public void addNewWorker(String documentName){
        Map<String, Object> toAdd = new HashMap<>();
        toAdd.put("First Name", "____");
        toAdd.put("Last Name", "____");
        toAdd.put("Contact Number", "____");
        toAdd.put("Email Address", "____");
        toAdd.put("WorkerID", 00);

    }




    // Getter methods
    public int getUid(){
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

    public String getHomeAddress(){
        return this.homeAddress;
    }

    public String getBirthDate(){
        return this.birthDate;
    }

    public int getProfilePicture(){
        return this.profilePicture;
    }

    // Setter methods
    public void setUid(int uid){
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

    public void setHomeAddress(String homeAddress){
        this.homeAddress = homeAddress;
    }

    public void setBirthDate(String birthDate){
        this.birthDate = birthDate;
    }

    public void setProfilePicture(int profilePicture){
        this.profilePicture = profilePicture;
    }
}
