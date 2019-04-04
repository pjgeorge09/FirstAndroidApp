package com.example.managertabs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import static com.example.managertabs.R.layout.inventory_card_view;
public class inventoryCardView extends Activity {
    TextView Category;
    TextView Expire;
    TextView Item;
    TextView Location;
    TextView Quantity;
    TextView Size;
    TextView Min_Threshold;
    //allows each the text views in the card view to be set to the appropiate field

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(inventory_card_view);
        Category= (TextView)findViewById(R.id.Inventory_Category);
        Expire= (TextView)findViewById(R.id.Inventory_Expire);
        Item= (TextView)findViewById(R.id.Inventory_Item);
        Location=(TextView)findViewById(R.id.Inventory_Loacation);
        Quantity= (TextView)findViewById(R.id.Inventory_Quantity);
        Size= (TextView)findViewById(R.id.Inventory_Size);
        Min_Threshold= (TextView)findViewById(R.id.Inventory_Min);

    }


}
