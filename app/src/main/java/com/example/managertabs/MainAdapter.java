package com.example.managertabs;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
    ArrayList<Donations_Data> ProperData;
    public MainAdapter(ArrayList<Donations_Data> testData) {
        this.ProperData = testData;
    }

//Creates Custom ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }
    //specify contents of each item
    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position){
      //  holder.Title.setText(TestData.get(position));
        holder.Date.setText(ProperData.get(position).Date);
        holder.Item.setText(ProperData.get(position).Item);
        holder.Quantity.setText(ProperData.get(position).Quantity);
        holder.Size.setText(ProperData.get(position).Size);

    }

    @Override
    public int getItemCount() {
        //number of data values
        return ProperData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView Date;
        TextView Item;
        TextView Quantity;
        TextView Size;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //text box in row
            cardView = (CardView)itemView.findViewById(R.id.CV_donation);
            Date= (TextView)itemView.findViewById(R.id.Date);
            Item= (TextView)itemView.findViewById(R.id.Item);
            Quantity= (TextView)itemView.findViewById(R.id.Quantity);
            Size= (TextView)itemView.findViewById(R.id.Size);


        }
    }
}