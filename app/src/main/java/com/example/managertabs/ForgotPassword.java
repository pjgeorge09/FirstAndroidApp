package com.example.managertabs;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText forgotEmail;
    private Button forgotButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Initializng
        forgotEmail = (EditText)findViewById(R.id.etForgotEmail);
        forgotButton = (Button)findViewById(R.id.btnForgotPW);
        mAuth = FirebaseAuth.getInstance();

        // OnClickListener for forgot password BTN
        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // convert text to string
                String userEmail = forgotEmail.getText().toString().trim();

                // if string is empty
                if(forgotEmail.equals("")) {
                    Toast.makeText(ForgotPassword.this, "Enter registered Email", Toast.LENGTH_SHORT).show();
                }
                else {

                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        // If the process is completed
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(ForgotPassword.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgotPassword.this, Login.class));
                            }

                            else {
                                Toast.makeText(ForgotPassword.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }


}
