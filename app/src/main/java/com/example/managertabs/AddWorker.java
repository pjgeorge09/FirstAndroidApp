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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AddWorker extends AppCompatActivity {


    private EditText addEmail;
    private EditText addPassword;
    private Button addButton;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);

        addEmail = (EditText)findViewById(R.id.etEmailAdd);
        addPassword = (EditText)findViewById(R.id.etPasswordAdd);
        addButton = (Button)findViewById(R.id.btnAddWorker);
        firebaseAuth = FirebaseAuth.getInstance();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = addEmail.getText().toString().trim();
                String userPassword = addPassword.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddWorker.this, "New employee added", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddWorker.this, Staff.class));
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
