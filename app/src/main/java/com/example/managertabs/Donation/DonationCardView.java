package com.example.managertabs.Donation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.managertabs.R;

import static com.example.managertabs.R.layout.donations_card_view;


public class DonationCardView extends Activity {
    TextView Category;
    TextView Expire;
    TextView Item;
    TextView Location;
    TextView Quantity;
    TextView DateR;
    TextView Min_Threshold;
    //Donor
    TextView DonorName;
    TextView DonorEmail;
    TextView DonorPhone;
    //allows each the text views in the card view to be set to the appropiate field

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(donations_card_view);
        Category=(TextView)findViewById(R.id.Donation_Category);
        Expire=(TextView)findViewById(R.id.Donation_Expire);
        Item= (TextView)findViewById(R.id.Donation_Item);
        Location=(TextView)findViewById(R.id.Donation_Location);
        Quantity= (TextView)findViewById(R.id.Donation_Quantity);
        DateR= (TextView)findViewById(R.id.Donation_Date_Received);
        Min_Threshold= (TextView)findViewById(R.id.Donation_Min);

        DonorName=(TextView)findViewById(R.id.Donation_Donor_Name);
        DonorPhone=(TextView)findViewById(R.id.Donation_Donor_Phone);
        DonorEmail=(TextView)findViewById(R.id.Donation_Donor_Email);
    }


}