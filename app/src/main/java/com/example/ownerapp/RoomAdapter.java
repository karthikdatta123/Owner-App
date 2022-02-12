package com.example.ownerapp;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    public  List<Room> list=new ArrayList<Room>();
    Orders order;
DatabaseReference mDatabaseReference;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView roomNo,total_cost,time,h1;
        RecyclerView recyclerView3;
        Button button;

        public ViewHolder(View view) {
            super(view);

            roomNo=view.findViewById(R.id.room);
            recyclerView3=view.findViewById(R.id.recycle_view3);
            button=view.findViewById(R.id.button2);
            total_cost=view.findViewById(R.id.total_cost);
            time=view.findViewById(R.id.time);
            h1=view.findViewById(R.id.heading_room);
        }
    }

    public RoomAdapter(List<Room> list) {
      this.list=list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.room_no,viewGroup,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Room s=list.get(position);
        viewHolder.roomNo.setText("#"+s.room_no+",");
        viewHolder.time.setText(s.time);
        viewHolder.total_cost.setText(s.total_price.toString());
        if(s.confirm.equals(true))
        {
            viewHolder.button.setBackgroundColor(Color.WHITE);
            viewHolder.button.setTextColor(Color.parseColor("#278817"));
            viewHolder.button.setText("Order Confirmed");
        }else{
        viewHolder.button.setOnClickListener(new View.OnClickListener() {

           @Override
            public void onClick(View view) {
                   FirebaseDatabase.getInstance().getReference("Orders").addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           list=new ArrayList<Room>();
                           if (snapshot.exists()) {

                               for (DataSnapshot item : snapshot.getChildren()) {
                                   String Time = item.child("time").getValue().toString();
                                   Integer roomid = Integer.parseInt(item.child("roomID").getValue().toString());
                                   if (s.time.equals(Time) && roomid.equals(s.room_no)) {
                                       item.child("confirmation").getRef().setValue((Boolean) true);
                                           viewHolder.button.setBackgroundColor(Color.WHITE);
                                           viewHolder.button.setTextColor(Color.parseColor("#278817"));
                                           viewHolder.button.setText("Order Confirmed");
                                           break;
                                   }
                               }
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {
                           // mDatabaseReference.removeEventListener(myListener);
                       }
                   });
           }

        });}

        viewHolder.recyclerView3.setLayoutManager(new LinearLayoutManager(viewHolder.recyclerView3.getContext()));
        OrderItemAdapter item_view=new OrderItemAdapter(s.items,s.quantity,s.price);
        viewHolder.recyclerView3.setAdapter(item_view);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
