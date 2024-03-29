package com.example.managertabs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.managertabs.EmployeeFiles.EmployeeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AddWorker extends Master {


    private EditText addEmail;
    private EditText addPassword;
    private Button addButton;
    private EditText FirstName;
    private EditText LastName;
    private EditText PhoneNumber;
    private EditText UID;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);

        addEmail = (EditText)findViewById(R.id.etEmailAdd);
        addPassword = (EditText)findViewById(R.id.etPasswordAdd);
        addButton = (Button)findViewById(R.id.btnAddWorker);
        FirstName = (EditText)findViewById(R.id.etFirstName);
        LastName = (EditText)findViewById(R.id.etLastName);
        PhoneNumber = (EditText)findViewById(R.id.etPhoneNumber);
        UID = (EditText)findViewById(R.id.etUID);
        firebaseAuth = FirebaseAuth.getInstance();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // password and email in boxes on click
                String userEmail = addEmail.getText().toString().trim();
                String userPassword = addPassword.getText().toString().trim();
                String firstName = FirstName.getText().toString().trim();
                String lastName = LastName.getText().toString().trim();
                String phoneNumber = PhoneNumber.getText().toString().trim();
                String uid = UID.getText().toString().trim();


                /**
                 * Adds employee in the acutally data base under its uid
                 */
                addNewEmployee(uid, uid, firstName, lastName, userEmail, phoneNumber);

                /**
                 * WANT  TO ADD BOXES THAT WILL PUT NEW USER IN AUTH (WHICH IT ALREADY DOES)
                 * AND WANT TO ADD HOME ADRESS, FIRSTNAME AND LAST NAME (MAYBE PHONE) THAT WILL PUT EMPLOYEE INTO DATABASE ALSO
                 */
                firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddWorker.this, "New employee added", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddWorker.this, EmployeeActivity.class));
                        }
                        else {
                            Toast.makeText(AddWorker.this, "Employee could not be added", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
