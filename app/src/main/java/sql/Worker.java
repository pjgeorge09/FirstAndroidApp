package sql;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "workers")
public class Worker {
    // User Id Column, auto-generated
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private int uid;

    // First name column
    @NonNull
    @ColumnInfo(name = "first_name")
    private String firstName;

    // Last name column
    @NonNull
    @ColumnInfo(name = "last_name")
    private String lastName;

    // Email address column
    @NonNull
    private String email;

    // Home address column
    @NonNull
    @Embedded
    private Address address;

    // Date of birth column
    @NonNull
    private String dateOfBirth;

    // Password Column
    @NonNull
    private String password;

    // Chosen security questions ID column
    @NonNull
    @ColumnInfo(name = "security_question_id")
    private int questionId;

    // Security question answer column
    @NonNull
    @ColumnInfo(name = "security_answer")
    private String securityAnswer;

    // Default constructor
    public Worker(String fName, String lName, String emailAddress, Address homeAddress,
                  String birthDate, String loginPassword, int securityQuestionId,
                  String secretAnswer){

        this.firstName = fName;
        this.lastName = lName;
        this.email = emailAddress;
        this.address = homeAddress;
        this.dateOfBirth = birthDate;
        this.password = loginPassword;
        this.questionId = securityQuestionId;
        this.securityAnswer = secretAnswer;
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

    public String getEmail(){
        return this.email;
    }

    public Address getAddress(){
        return this.address;
    }

    public String getDateOfBirth(){
        return this.dateOfBirth;
    }

    public String getPassword(){
        return this.password;
    }

    public int getQuestionId(){
        return this.questionId;
    }

    public String getSecurityAnswer(){
        return this.securityAnswer;
    }

    // Setter methods
    public void setUid(int UserID){
        this.uid = UserID;
    }

    public void setFirstName(String fName){
        this.firstName = fName;
    }

    public void setLastName(String lName){
        this.lastName = lName;
    }

    public void setEmail(String emailAddress){
        this.email = emailAddress;
    }

    public void setAddress(Address homeAddress){
        this.address = homeAddress;
    }

    public void setDateOfBirth(String birthDate){
        this.dateOfBirth = birthDate;
    }

    public void setPassword(String loginPassword){
        this.password = loginPassword;
    }

    public void setQuestionId(int securityQuestionId){
        this.questionId = securityQuestionId;
    }

    public void setSecurityAnswer(String secretAnswer){
        this.securityAnswer = secretAnswer;
    }
}

// Class to declare an address as an object
class Address {
    private String street;
    private String state;
    private String city;

    @ColumnInfo(name = "zip_code")
    private int zipCode;

    // Default Address Constructor
    public Address(String streetAddress, String stateAddress, String cityAddress,
                   int zipCodeAddress){
        this.street = streetAddress;
        this.state = stateAddress;
        this.city = cityAddress;
        this.zipCode = zipCodeAddress;
    }

    // Getter methods
    public String getStreet(){
        return this.street;
    }

    public String getState(){
        return this.state;
    }

    public String getCity(){
        return this.city;
    }

    public int getZipCode(){
        return this.zipCode;
    }

    // Setter methods
    public void setStreet(String streetAddress){
        this.state = streetAddress;
    }

    public void setState(String stateAddress){
        this.state = stateAddress;
    }

    public void setCity(String cityAddress){
        this.city = cityAddress;
    }

    public void setZipCode(int zipCodeAddress){
        this.zipCode = zipCodeAddress;
    }
}

