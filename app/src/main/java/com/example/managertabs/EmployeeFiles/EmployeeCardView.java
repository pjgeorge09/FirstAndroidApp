package com.example.managertabs.EmployeeFiles;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.managertabs.R;

public class EmployeeCardView extends Activity {

    TextView name;
    TextView email;
    ImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cardview_employees);
        name = (TextView)findViewById(R.id.employee_name);
        email = (TextView)findViewById(R.id.employee_email);
        profilePicture = (ImageView)findViewById(R.id.profile_picture);
    }
}
