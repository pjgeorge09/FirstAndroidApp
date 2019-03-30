package com.example.managertabs.EmployeeFiles;

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

// Class to link the View for an Employee object to our RecyclerView
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    // The view holder creates a reference to a View so it can be implemented efficiently
    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {

        // Private variables for our View
        CardView cv;
        TextView name;
        TextView email;
        ImageView profilePicture;
        TextView homeAddress;
        TextView birthDate;
        RelativeLayout buttonLayout;
        LinearLayout expandableLayout;

        // Constructor for the EmployeeViewHolder
        // In this case our "View" is the employees list that gets passed to it
        // in the EmployeeActivity
        EmployeeViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView)itemView.findViewById(R.id.employee_name);
            email = (TextView)itemView.findViewById(R.id.employee_email);
            profilePicture = (ImageView)itemView.findViewById(R.id.profile_picture);
            homeAddress = (TextView)itemView.findViewById(R.id.home_address);
            birthDate = (TextView)itemView.findViewById(R.id.birth_date);

            buttonLayout = (RelativeLayout) itemView.findViewById(R.id.buttonDropDown);
            expandableLayout = (LinearLayout) itemView.findViewById(R.id.expandableLayout);
        }
    }

    // SparseBooleanArray maps a list of integers to a corresponding boolean
    List<Employee> employees;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    // Initializes the EmployeeAdapter and sets each employee position in the List
    // to a corresponding false boolean value in the SparseBooleanArray
    EmployeeAdapter(List<Employee> employees){
        this.employees = employees;

        for (int i = 0; i < employees.size(); i++) {
            expandState.append(i, false);
        }
    }

    // Attaches our EmployeeAdapter to our RecyclerView
    // Mandatory Override method for every adapter class
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Creates the view to be implemented for each Employee in the employees list passed to
    // this class in our EmployeeActivity
    // Mandatory Override method for every adapter class
    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_employees, viewGroup, false);
        EmployeeViewHolder employeeViewHolder = new EmployeeViewHolder(v);
        return employeeViewHolder;
    }

    // This method binds assigns data to our View
    // Method will be rewritten to allow data to be pulled from database instead of hardcoded
    // Mandatory Override method for every adapter class
    @Override
    public void onBindViewHolder(final EmployeeViewHolder employeeViewHolder, final int i){
        employeeViewHolder.name.setText(employees.get(i).getFirstName() + " " + employees.get(i).getLastName());
        employeeViewHolder.email.setText(employees.get(i).getEmailAddress());
        employeeViewHolder.profilePicture.setImageResource(employees.get(i).getProfilePicture());
        employeeViewHolder.homeAddress.setText(employees.get(i).getHomeAddress());
        employeeViewHolder.birthDate.setText(employees.get(i).getBirthDate());


        // Check if view is expanded and uses a ternary operation to set the view accordingly
        final boolean isExpanded = expandState.get(i);
        employeeViewHolder.expandableLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);

        // Uses a ternary operator to set the buttons rotation based on the view state
        employeeViewHolder.buttonLayout.setRotation(expandState.get(i) ? 180f : 0f);
        employeeViewHolder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(employeeViewHolder.expandableLayout, employeeViewHolder.buttonLayout,  i);
            }
        });
    }

    // Gets the size of the employees List that was passed
    @Override
    public int getItemCount(){
        return employees.size();
    }

    // Method to handle changing layouts based on clicking the expandable arrow button
    private void onClickButton(final LinearLayout expandableLayout, final RelativeLayout buttonLayout, final  int i) {

        //Simply set View to Gone if not expanded
        //Not necessary but I put simple rotation on button layout
        if (expandableLayout.getVisibility() == View.VISIBLE){
            createRotateAnimator(buttonLayout, 90f, 0f).start();
            expandableLayout.setVisibility(View.GONE);
            expandState.put(i, false);
        }else{
            createRotateAnimator(buttonLayout, 0f, 90f).start();
            expandableLayout.setVisibility(View.VISIBLE);
            expandState.put(i, true);
        }
    }

    //Code to rotate button
    private ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }

}
