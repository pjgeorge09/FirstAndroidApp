package sql;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "workers")
public class Worker {
    // User Id Column, auto-generated
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    public int uid;

    // First name column
    @ColumnInfo(name = "first_name")
    public String firstName;

    // Last name column
    @ColumnInfo(name = "last_name")
    public String lastName;

    // Email address column
    public String email;

    // Home address column
    @Embedded
    public Address address;

    // Date of birth column
    public String dob;

    // Chosen security questions ID column
    @ColumnInfo(name = "security_question_id")
    public int questionId;

    // Security question answer column
    @ColumnInfo(name = "security_answer")
    public String securityAnswer;

    // Empty constructor
    public Worker(){

    }
}

// Class to declare an address as an object
class Address {
    public String street;
    public String state;
    public String city;

    @ColumnInfo(name = "zip_code")
    public int zipCode;
}



