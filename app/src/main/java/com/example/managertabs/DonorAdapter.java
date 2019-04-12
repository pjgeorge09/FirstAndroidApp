package com.example.managertabs;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.SparseBooleanArray;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;

import com.example.managertabs.R;

import java.util.List;

// Class to link the View for an Donor object to our RecyclerView
public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.DonorViewHolder> {

    // The view holder creates a reference to a View so it can be implemented efficiently
    public static class DonorViewHolder extends RecyclerView.ViewHolder {

        // Private variables for our View
        CardView cv;
        TextView name;
        TextView email;
        TextView birthDate;
        TextView allowContact;

        // Constructor for the DonorViewHolder
        // In this case our "View" is the donors list that gets passed to it
        // in the Donor Activity
        DonorViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView)itemView.findViewById(R.id.employee_name);
            email = (TextView)itemView.findViewById(R.id.employee_email);
            birthDate = (TextView)itemView.findViewById(R.id.birth_date);
            allowContact = (TextView)itemView.findViewById(R.id.allowContact);
        }
    }

    // SparseBooleanArray maps a list of integers to a corresponding boolean
    List<Donor> donors;

    // Initializes the DonorAdapter and sets each donors position in the List
    // to a corresponding false boolean value in the SparseBooleanArray
    DonorAdapter(List<Donor> donors){
        this.donors = donors;

    }

    // Attaches our DonorAdapter to our RecyclerView
    // Mandatory Override method for every adapter class
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Creates the view to be implemented for each Donor in the donors list passed to
    // this class in our Donor Activity
    // Mandatory Override method for every adapter class
    @Override
    public DonorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_donors, viewGroup, false);
        DonorViewHolder donorViewHolder = new DonorViewHolder(v);
        return donorViewHolder;
    }

    // This method binds assigns data to our View
    // Method will be rewritten to allow data to be pulled from database instead of hardcoded
    // Mandatory Override method for every adapter class
    @Override
    public void onBindViewHolder(final DonorViewHolder donorViewHolder, final int i){
        donorViewHolder.name.setText(donors.get(i).getFirstName() + " " + donors.get(i).getLastName());
        donorViewHolder.email.setText(donors.get(i).getEmailAddress());
    }

    // Gets the size of the donors List that was passed
    @Override
    public int getItemCount(){
        return donors.size();
    }
}
