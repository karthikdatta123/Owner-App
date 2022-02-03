package com.example.ownerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    public  List<Room> list=new ArrayList<Room>();


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView roomNo;

        RecyclerView recyclerView3;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            roomNo=view.findViewById(R.id.room);

            recyclerView3=view.findViewById(R.id.recycle_view3);

        }

    }

    public OrderAdapter(List<Room> a) {
      list=a;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.list_item,viewGroup,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Room s=list.get(position);



        viewHolder.roomNo.setText("Room#"+s.room_no+",   "+s.time);

        viewHolder.recyclerView3.setLayoutManager(new LinearLayoutManager(viewHolder.recyclerView3.getContext()));


        finalAdapter b=new finalAdapter(s.items,s.quantity);
        viewHolder.recyclerView3.setAdapter(b);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }
}
