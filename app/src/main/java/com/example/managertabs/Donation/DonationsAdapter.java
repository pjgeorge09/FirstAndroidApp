package com.example.managertabs.Donation;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.managertabs.R;

import java.util.ArrayList;
import java.util.List;

public class DonationsAdapter extends RecyclerView.Adapter<DonationsAdapter.DonationViewHolder>{

    List<Donation> ProperData;
//allow the array lis to pass to the rest of the adapter
    public DonationsAdapter(List<Donation> ProperData) {
        this.ProperData = ProperData;
    }

    //Creates Custom ViewHolder
    @Override
    //links the view holder to the to its card view xml alllows it to generate all the rows
    public DonationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.donations_card_view, viewGroup, false);
        DonationViewHolder pvh = new DonationViewHolder(v);
        return pvh;
    }


    //specify contents of each item
    @Override
    public void onBindViewHolder(DonationViewHolder holder, int position){
   //passes the data to the text views
        holder.Date.setText("Date: "+ ProperData.get(position).Date);
        holder.Item.setText("Item: "+ProperData.get(position).Item);
        holder.Quantity.setText("Quantity: "+ProperData.get(position).Quantity);
        holder.Size.setText("Size: "+ProperData.get(position).Size);

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
    public static class DonationViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView Date;
        TextView Item;
        TextView Quantity;
        TextView Size;
        //links the card view and textboxes to the view holder
        public DonationViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.CV_donation);
            //text box in row
            Date= (TextView)itemView.findViewById(R.id.Date);
            Item= (TextView)itemView.findViewById(R.id.Item);
            Quantity= (TextView)itemView.findViewById(R.id.Quantity);
            Size= (TextView)itemView.findViewById(R.id.Size);


        }
    }
}