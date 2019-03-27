package com.example.managertabs.EmployeeFiles;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.managertabs.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView name;
        TextView email;
        ImageView profilePicture;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView)itemView.findViewById(R.id.employee_name);
            email = (TextView)itemView.findViewById(R.id.employee_email);
            profilePicture = (ImageView)itemView.findViewById(R.id.profile_picture);
        }
    }

    List<Employee> employees;

    EmployeeAdapter(List<Employee> employees){
        this.employees = employees;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_employees, viewGroup, false);
        EmployeeViewHolder employeeViewHolder = new EmployeeViewHolder(v);
        return employeeViewHolder;
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder employeeViewHolder, int i){
        employeeViewHolder.name.setText(employees.get(i).getFirstName() + employees.get(i).getLastName());
        employeeViewHolder.email.setText(employees.get(i).getEmailAddress());
        employeeViewHolder.profilePicture.setImageResource(employees.get(i).getProfilePicture());
    }

    @Override
    public int getItemCount(){
        return employees.size();
    }


}
