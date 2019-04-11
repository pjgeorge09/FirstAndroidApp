package com.example.managertabs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends Master {

    private EditText Email;
    private EditText Password;
    private Button Login;
    private Button Forgot;
    private int counter = 5;
    private ImageView ii;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);

        Email = (EditText)findViewById(R.id.etEmail1);
        Password = (EditText)findViewById(R.id.etPassword1);
        Login = (Button)findViewById(R.id.btnLogin1);
        Forgot = (Button)findViewById(R.id.btnForgotPassword);
        ii = (ImageView)findViewById(R.id.IVii);


        // link to firebase authentication
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        // Checks if a user is already logged in
        FirebaseUser user = firebaseAuth.getCurrentUser();

        // When user clicks Login
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Email.getText().toString(), Password.getText().toString());
            }
        });

        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressForgot();
            }
        });
    }

    /* Finishes the app, closes it, clears the memory so back button cannot login, and moves to background */
    @Override
    public void onBackPressed() {
        finish();
        moveTaskToBack(true);
    }




    /*
        Needs to pull valid user names and passwords from data base
        Needs to direct to correct page (manager or users) if valid login
     */
    private void validate(String userEmail, String userPassword) {
        //Dialog to show when login is reading data from database
        progressDialog.setMessage("WORKING ON IT!");
        progressDialog.show();

        //Signin
        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //If data is read from firebase
                if(task.isSuccessful()) {
                    //remove dialog
                    progressDialog.dismiss();
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    String userID = user.getUid();
                    // UID FOR MANAGER SEND TO MANAGER SCREEN
                    if(userID.equals("udWXeYDnJnQo1ZuV9eYloBdOfUM2")) {
                        startActivity(new Intent(Login.this, ManagerHomeScreen.class));
                    }
                    // SEND TO EMPLOYEE
                    else {
                        startActivity(new Intent(Login.this, WorkerHomeScreen.class));
                    }
                }
                else {
                    counter--;
                    progressDialog.dismiss();

                    //Setting for Toast notification
                    Context context = getApplicationContext();
                    CharSequence numberOfIncorrect = "Number of attempts remaining: " + String.valueOf(counter);
                    int duration = Toast.LENGTH_SHORT;

                    //Implication of toast
                    Toast.makeText(context, numberOfIncorrect, duration).show();

                    //If use of all login
                    if(counter == 0) {

                        //Setting for toast notification for too many attempts
                        Context context1 = getApplicationContext();
                        CharSequence tooManyAttempts = "Too many incorrect attempts";
                        int duration1 = Toast.LENGTH_SHORT;

                        Toast.makeText(context1, tooManyAttempts, duration1).show();

                        Login.setEnabled(false);
                        Login.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Login.setEnabled(true);
                            }
                        }, 5000);

                        counter = 5;
                    }
                }
            }
        });

    }


    public void pressForgot() {
        Intent intent = new Intent(com.example.managertabs.Login.this, ForgotPassword.class);
        startActivity(intent);
    }

// NOTE
    //        ////// HOW TO ENCRYPT OUR PASSWORDS
//        String newString = "y";
//        MessageDigest digest;
//        try {
//            //Creating the hash using SHA256
//            digest = MessageDigest.getInstance("SHA-256");
//            // Encoding "y" to a byteArray
//            byte[] byteArray = digest.digest(newString.getBytes(StandardCharsets.UTF_8));
//            // Decoding back, this is what we store in SQL
//            String tempPass = new String(byteArray, StandardCharsets.UTF_8);
//            Log.i("Test", tempPass);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        //////




}
