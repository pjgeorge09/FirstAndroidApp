package com.example.managertabs;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class inventoryAdapter extends RecyclerView.Adapter<inventoryAdapter.InventoryViewHolder>{

    List<inventoryData> ProperData;
    //allow the array lis to pass to the rest of the adapter
    public inventoryAdapter(List<inventoryData> ProperData) {
        this.ProperData = ProperData;
    }

    //Creates Custom ViewHolder
    @Override
    //links the view holder to the to its card view xml alllows it to generate all the rows
    public InventoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.donations_card_view, viewGroup, false);
        InventoryViewHolder pvh = new InventoryViewHolder(v);
        return pvh;
    }


    //specify contents of each item
    @Override
    public void onBindViewHolder(InventoryViewHolder holder, int position){
        //passes the data to the text views
        holder.Category.setText("Category: "+ ProperData.get(position).inventoryCategory);
        holder.Expire.setText("Expire: "+ProperData.get(position).inventoryExpire);
        holder.Item.setText("Item: "+ProperData.get(position).inventoryItem);
        holder.Location.setText("Location: "+ProperData.get(position).inventoryLocation);
        holder.Quantity.setText("Quantity: "+ProperData.get(position).inventoryQuantity);
        holder.Size.setText("Size: "+ProperData.get(position).inventorySize);
        holder.Min_Threshold.setText("Minimum Threshold: "+ProperData.get(position).inventoryMin_Threshold);
    }

    @Override
    //returns number of items in the recycler view in this case its just the length of the array passes
    public int getItemCount() {
        //number of data values
        return ProperData.size();
    }

    @Override
    //Called by RecyclerView when it starts observing this Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    //custom view holder class
    public static class InventoryViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView Category;
        TextView Expire;
        TextView Item;
        TextView Location;
        TextView Quantity;
        TextView Size;
        TextView Min_Threshold;
        //links the card view and textboxes to the view holder
        public InventoryViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.inventory);
            //text box in row
            Date= (TextView)itemView.findViewById(R.id.Date);
            Item= (TextView)itemView.findViewById(R.id.Item);
            Quantity= (TextView)itemView.findViewById(R.id.Quantity);
            Size= (TextView)itemView.findViewById(R.id.Size);


        }
    }
}

