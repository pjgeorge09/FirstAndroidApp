package com.example.managertabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginTemp extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_temp);

        Email = (EditText)findViewById(R.id.etEmail1);
        Password = (EditText)findViewById(R.id.etPassword1);
        Info = (TextView)findViewById(R.id.textView2);
        Login = (Button)findViewById(R.id.btnLogin1);

        Info.setText("No of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Email.getText().toString(), Password.getText().toString());
            }
        });


    }

    /*
        Needs to pull valid user names and passwords from data base
        Needs to direct to correct page (manager or users) if valid login
     */
    private void validate(String userEmail, String userPassword) {
        if(userEmail.equals("manager") && userPassword.equals("manager")) {
            Intent intent = new Intent(LoginTemp.this, MainActivity.class);
            startActivity(intent);
        }
        else if(userEmail.equals("worker") && userPassword.equals("worker")){
            Intent intent1 = new Intent(LoginTemp.this, WorkerHomeScreen.class);
            startActivity(intent1);
        }
        else {
            counter--;

            Info.setText("Number of attempts reamining: " + String.valueOf(counter));

            if(counter == 0) {
                Login.setEnabled(false);
            }
        }

    }
}
