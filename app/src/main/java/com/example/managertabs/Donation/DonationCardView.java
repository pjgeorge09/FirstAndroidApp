package com.example.managertabs.Donation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.managertabs.R;

import static com.example.managertabs.R.layout.donations_card_view;


public class DonationCardView extends Activity {
    //TextView variables
    TextView Date;
    TextView Item;
    TextView Quantity;
    TextView Size;
    @Override
    //allows each the text views in the card view to be set to the appropiate field
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(donations_card_view);
        Date = (TextView)findViewById(R.id.Date);
        Item = (TextView)findViewById(R.id.Item);
        Quantity = (TextView)findViewById(R.id.Quantity);
        Size= (TextView)findViewById(R.id.Size);
    }
}


