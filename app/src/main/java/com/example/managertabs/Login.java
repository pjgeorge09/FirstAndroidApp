package com.example.managertabs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

public class Login extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private Button Login;
    private TextView Forgot;
    private int counter = 5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);

        Email = (EditText)findViewById(R.id.etEmail1);
        Password = (EditText)findViewById(R.id.etPassword1);
        Login = (Button)findViewById(R.id.btnLogin1);
        Forgot = (TextView)findViewById(R.id.tvForgot);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Email.getText().toString(), Password.getText().toString());
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
        //manager, manager goes to manager tab
        if(userEmail.equals("manager") && userPassword.equals("manager")) {
            Intent intent = new Intent(com.example.managertabs.Login.this, ManagerHomeScreen.class);
            startActivity(intent);
        }

        //worker, worker goes to worker tab
        else if(userEmail.equals("worker") && userPassword.equals("worker")){
            Intent intent1 = new Intent(com.example.managertabs.Login.this, WorkerHomeScreen.class);
            startActivity(intent1);
        }
        //counter for wrong attempts
        else {
            counter--;

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

    /*
        Forgotten user or name or password button
        Will link to SQL later
     */
    public void pressForgot(View v) {
        Intent intent = new Intent(com.example.managertabs.Login.this, WorkerHomeScreen.class);
        startActivity(intent);
    }
}
