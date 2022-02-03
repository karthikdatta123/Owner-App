package com.example.ownerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public class finalAdapter extends RecyclerView.Adapter<finalAdapter.ViewHolder> {


    public  List<String> items=new ArrayList<String>();
    public  List<Integer> quantity=new ArrayList<Integer>();

    public finalAdapter(List<String> items, List<Integer> quantity) {
        this.items=items;
        this.quantity=quantity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView Order,Quantity;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            Order=view.findViewById(R.id.order);
            Quantity=view.findViewById(R.id.quantity);
        }
    }




    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.final_view,viewGroup,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {


            viewHolder.Order.setText(items.get(position));
            viewHolder.Quantity.setText("x"+quantity.get(position));


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }
}
