package com.example.managertabs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.managertabs.R.layout.card_view;


public class donorcardview extends Activity {
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

}
