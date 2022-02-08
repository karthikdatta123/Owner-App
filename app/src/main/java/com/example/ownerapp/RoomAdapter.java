package com.example.ownerapp;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    public  List<Room> list=new ArrayList<Room>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView roomNo,total_cost;
        RecyclerView recyclerView3;
        Button button;

        public ViewHolder(View view) {
            super(view);
            roomNo=view.findViewById(R.id.room);
            recyclerView3=view.findViewById(R.id.recycle_view3);
            button=view.findViewById(R.id.button);
            total_cost=view.findViewById(R.id.total);
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
        viewHolder.roomNo.setText("Room#"+s.room_no+",   "+s.time);
        viewHolder.total_cost.setText("Total Price to pay:     "+s.total_price);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Order confirmed", Toast.LENGTH_SHORT).show();
                viewHolder.button.setBackgroundColor(Color.WHITE);
                viewHolder.button.setTextColor(Color.RED);
                viewHolder.button.setText("Order Confirmed");
            }
        });
        viewHolder.recyclerView3.setLayoutManager(new LinearLayoutManager(viewHolder.recyclerView3.getContext()));
        OrderItemAdapter item_view=new OrderItemAdapter(s.items,s.quantity,s.price);
        viewHolder.recyclerView3.setAdapter(item_view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
