package com.example.managertabs.Donation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.managertabs.R;

import static com.example.managertabs.R.layout.card_view;


public class DonationCardView extends Activity {
    TextView Date;
    TextView Item;
    TextView Quantity;
    TextView Size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(card_view);
        Date = (TextView)findViewById(R.id.Date);
        Item = (TextView)findViewById(R.id.Item);
        Quantity = (TextView)findViewById(R.id.Quantity);
        Size= (TextView)findViewById(R.id.Size);
    }
}


