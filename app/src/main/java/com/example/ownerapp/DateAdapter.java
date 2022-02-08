package com.example.ownerapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {
public  List<Orders> list=new ArrayList<Orders>();
    public DateAdapter(List<Orders> list){
       this.list=list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateView;
        RecyclerView recyclerView1;

        public ViewHolder(View view) {
            super(view);
            dateView=view.findViewById(R.id.date);
            recyclerView1=view.findViewById(R.id.recyclerView2);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.date,viewGroup,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Orders s=list.get(position);
        viewHolder.dateView.setText(s.date);
        viewHolder.recyclerView1.setLayoutManager(new LinearLayoutManager(viewHolder.recyclerView1.getContext()));
        RoomAdapter room_view=new RoomAdapter(s.rooms);
        viewHolder.recyclerView1.setAdapter(room_view);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}

