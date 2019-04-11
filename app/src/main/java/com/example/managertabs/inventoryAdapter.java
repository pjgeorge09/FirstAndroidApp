package com.example.managertabs;

import android.animation.ObjectAnimator;
import android.graphics.Color;
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

import java.util.List;

public class inventoryAdapter extends RecyclerView.Adapter<inventoryAdapter.InventoryViewHolder>{

    List<inventoryData> ProperData;
    SparseBooleanArray inventoryExpandState = new SparseBooleanArray();
    // SparseBooleanArray maps a list of integers to a corresponding boolean


    //allow the array lis to pass to the rest of the adapter
    public inventoryAdapter(List<inventoryData> ProperData) {
        this.ProperData = ProperData;
        for (int i = 0; i < ProperData.size(); i++) {
            inventoryExpandState.append(i, false);
        }

    }







    //Creates Custom ViewHolder
    @Override
    //links the view holder to the to its card view xml alllows it to generate all the rows
    public InventoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inventory_card_view, viewGroup, false);
        InventoryViewHolder pvh = new InventoryViewHolder(v);
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
            inventoryExpandState.put(i, false);
        }else{
            createRotateAnimator(buttonLayout, 0f, 90f).start();
            expandableLayout.setVisibility(View.VISIBLE);
            inventoryExpandState.put(i, true);
        }
    }
    //specify contents of each item
    @Override
    public void onBindViewHolder(final InventoryViewHolder holder, final int position){
        //passes the data to the text views
        holder.Category.setText("Category: "+ this.ProperData.get(position).inventoryCategory);
        holder.Expire.setText("Expire: "+ this.ProperData.get(position).inventoryExpire);
        holder.Item.setText("Item: "+ this.ProperData.get(position).inventoryItem);
        holder.Location.setText("Location: "+ this.ProperData.get(position).inventoryLocation);
        holder.Quantity.setText("Quantity: "+ this.ProperData.get(position).inventoryQuantity);
        holder.DateR.setText("Date Received: "+ this.ProperData.get(position).inventoryDateRecived);
        holder.Min_Threshold.setText("Minimum Threshold: "+ this.ProperData.get(position).inventoryMin_Threshold);

        // Check if view is expanded and uses a ternary operation to set the view accordingly

        final boolean isExpanded = inventoryExpandState.get(position);
        holder.inventoryExpandableLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);


        // Uses a ternary operator to set the buttons rotation based on the view state
        holder.inventoryButtonLayout.setRotation(inventoryExpandState.get(position) ? 180f : 0f);
        holder.inventoryButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(holder.inventoryExpandableLayout, holder.inventoryButtonLayout, position);
            }
        });

        //what is this for?
        int test = Integer.parseInt(this.ProperData.get(position).inventoryQuantity.toString());
        int bar = Integer.parseInt(this.ProperData.get(position).inventoryMin_Threshold.toString());
        if(test < bar){
            holder.Quantity.setTextColor(Color.RED);
        }
    }

    @Override
    //returns number of items in the recycler view in this case its just the length of the array passes
    public int getItemCount() {
        //number of data values
        return this.ProperData.size();
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
        TextView DateR;
        TextView Min_Threshold;
        RelativeLayout inventoryButtonLayout;
        LinearLayout inventoryExpandableLayout;
        //links the card view and text boxes to the view holder

        public InventoryViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.CV_inventory);
            cardView.setCardBackgroundColor(Color.LTGRAY);

            //text box in row
            Category= (TextView)itemView.findViewById(R.id.Inventory_Category);
            Expire= (TextView)itemView.findViewById(R.id.Inventory_Expire);
            Item= (TextView)itemView.findViewById(R.id.Inventory_Item);
            Location=(TextView)itemView.findViewById(R.id.Inventory_Loacation);
            Quantity= (TextView)itemView.findViewById(R.id.Inventory_Quantity);
            DateR= (TextView)itemView.findViewById(R.id.Inventory_Date_Received);
            Min_Threshold= (TextView)itemView.findViewById(R.id.Inventory_Min);
            inventoryButtonLayout = (RelativeLayout) itemView.findViewById(R.id.inventory_buttonDropDown);
            inventoryExpandableLayout = (LinearLayout) itemView.findViewById(R.id.inventory_expand);

            }

        }


    }


