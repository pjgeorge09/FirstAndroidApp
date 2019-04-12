package com.example.managertabs.Donation;

import android.animation.ObjectAnimator;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.managertabs.R;
import com.example.managertabs.inventoryData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DonationsAdapter extends RecyclerView.Adapter<DonationsAdapter.DonationViewHolder>{

    List<Donation> ProperData;
//allow the array list to pass to the rest of the adapter

    SparseBooleanArray DonationExpandState = new SparseBooleanArray();
    // SparseBooleanArray maps a list of integers to a corresponding boolean


    //allow the array list to pass to the rest of the adapter
    public DonationsAdapter(List<Donation> ProperData) {
        this.ProperData = ProperData;
        for (int i = 0; i < ProperData.size(); i++) {
            DonationExpandState.append(i, false);
        }

    }

    //Creates Custom ViewHolder
    @Override
    //links the view holder to the to its card view xml alllows it to generate all the rows
    public DonationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.donations_card_view, viewGroup, false);
        DonationViewHolder pvh = new DonationViewHolder(v);
        return pvh;
    }
    //Code to rotate button
    private ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }
    // Method to handle changing layouts based on clicking the expandable arrow button
    private void onClickButton(final LinearLayout expandableLayout, final RelativeLayout buttonLayout, final  int i) {

        //Simply set View to Gone if not expanded
        //Not necessary but I put simple rotation on button layout
        if (expandableLayout.getVisibility() == View.VISIBLE){
            createRotateAnimator(buttonLayout, 90f, 0f).start();
            expandableLayout.setVisibility(View.GONE);
            DonationExpandState.put(i, false);
        }else{
            createRotateAnimator(buttonLayout, 0f, 90f).start();
            expandableLayout.setVisibility(View.VISIBLE);
            DonationExpandState.put(i, true);
        }
    }


    //specify contents of each item
    @Override
    public void onBindViewHolder(final DonationViewHolder holder, final int position){
   //passes the data to the text views
        //inventory
        holder.Category.setText("Category: "+ProperData.get(position).inventoryCategory);
        holder.Expire.setText("Expires"+ProperData.get(position).inventoryExpire);
        holder.Item.setText("Item: "+ProperData.get(position).inventoryItem);
        holder.Location.setText("Size: "+ProperData.get(position).inventoryLocation);
        holder.Quantity.setText("Quantity: "+ProperData.get(position).inventoryQuantity);
        holder.DateR.setText("Date: "+ ProperData.get(position).inventoryDateRecived);
        holder.Min_Threshold.setText("Minimum Threshold: "+ProperData.get(position).inventoryMin_Threshold);
        //Donor
        holder.DonorName.setText("Name: "+ProperData.get(position).DonorName);
        holder.DonorPhone.setText("Phone:"+ProperData.get(position).DonorPhone);
        holder.DonorEmail.setText("Email:"+ProperData.get(position).DonorEmail);


        // Check if view is expanded and uses a ternary operation to set the view accordingly

        final boolean isExpanded = DonationExpandState.get(position);
        holder.inventoryExpandableLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);

        final boolean isExpanded2 = DonationExpandState.get(position);
        holder.DonorExpandavleLayour.setVisibility(isExpanded2?View.VISIBLE:View.GONE);
//Inventory-----------------------------------------------------------------------------------------
        // Uses a ternary operator to set the buttons rotation based on the view state
        holder.inventoryButtonLayout.setRotation(DonationExpandState.get(position) ? 180f : 0f);
        holder.inventoryButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(holder.inventoryExpandableLayout, holder.inventoryButtonLayout, position);
            }
        });
        //Donor---------------------------------------------------------------------------------------
        // Uses a ternary operator to set the buttons rotation based on the view state
        holder.DonorButtonLayout.setRotation(DonationExpandState.get(position) ? 180f : 0f);
        holder.DonorButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(holder.DonorExpandavleLayour, holder.DonorButtonLayout, position);
            }
        });
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
    //inventory
    TextView Category;
    TextView Expire;
    TextView Item;
    TextView Location;
    TextView Quantity;
    TextView DateR;
    TextView Min_Threshold;
    //Donor
    TextView DonorName;
    TextView DonorPhone;
    TextView DonorEmail;

    RelativeLayout inventoryButtonLayout;
    RelativeLayout DonorButtonLayout;

    LinearLayout inventoryExpandableLayout;
    LinearLayout DonorExpandavleLayour;
        //links the card view and textboxes to the view holder
        public DonationViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.CV_donation);
            //text box in row
            //Inventory
            Category=(TextView)itemView.findViewById(R.id.Donation_Category);
            Expire=(TextView)itemView.findViewById(R.id.Donation_Expire);
            Item= (TextView)itemView.findViewById(R.id.Donation_Item);
            Location=(TextView)itemView.findViewById(R.id.Donation_Location);
            Quantity= (TextView)itemView.findViewById(R.id.Donation_Quantity);
            DateR= (TextView)itemView.findViewById(R.id.Donation_Date_Received);
            Min_Threshold= (TextView)itemView.findViewById(R.id.Donation_Min);
            //button stuff
            inventoryButtonLayout = (RelativeLayout) itemView.findViewById(R.id.Donation_buttonDropDown_Inventory);
            inventoryExpandableLayout = (LinearLayout) itemView.findViewById(R.id.Donation_expand);
            //Donor
            DonorName=(TextView)itemView.findViewById(R.id.Donation_Donor_Name);
            DonorPhone=(TextView)itemView.findViewById(R.id.Donation_Donor_Phone);
            DonorEmail=(TextView)itemView.findViewById(R.id.Donation_Donor_Email);
            //ButtonStuff
            DonorButtonLayout=(RelativeLayout) itemView.findViewById(R.id.Donation_buttonDropDown_Donors);
            DonorExpandavleLayour= (LinearLayout) itemView.findViewById(R.id.Donation_Donor_Expand);

        }
    }
}