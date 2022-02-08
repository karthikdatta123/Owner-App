package com.example.ownerapp;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
public  List<Orders> list=new ArrayList<Orders>();
    public CustomAdapter(List<Orders> a){

       list=a;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateView;
        Button button;
        RecyclerView recyclerView1;
        public ViewHolder(View view) {
            super(view);
            dateView=view.findViewById(R.id.date);
            button=view.findViewById(R.id.button);
            recyclerView1=view.findViewById(R.id.recyclerView2);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.text_row_item,viewGroup,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Orders s=list.get(position);
        viewHolder.dateView.setText(s.date);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Order confirmed", Toast.LENGTH_SHORT).show();
                viewHolder.button.setBackgroundColor(Color.WHITE);
                viewHolder.button.setTextColor(Color.RED);
                viewHolder.button.setText("Order Confirmed");
            }
        });
        viewHolder.recyclerView1.setLayoutManager(new LinearLayoutManager(viewHolder.recyclerView1.getContext()));
        OrderAdapter second_view=new OrderAdapter(s.rooms);
        viewHolder.recyclerView1.setAdapter(second_view);
       /* LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        viewHolder.recyclerView.setLayoutManager(linearLayoutManager);
        viewHolder.recyclerView.setAdapter(b);*/
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}

