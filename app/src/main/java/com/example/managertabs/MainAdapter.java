package com.example.managertabs;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
    private ArrayList<String> TestData;

    public MainAdapter(ArrayList<String> testData) {
        TestData = testData;
    }


    @Override
public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
//row
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(v);
    }
@Override
public void onBindViewHolder(MainAdapter.ViewHolder holder, int position){
holder.Title.setText(TestData.get(position));
}

    @Override
    public int getItemCount() {
        //number of data values
        return TestData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Title;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            //text box in row
            Title=itemView.findViewById(R.id.textView16);

        }
    }
}