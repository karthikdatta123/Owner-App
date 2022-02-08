package com.example.ownerapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {
    public  List<String> items=new ArrayList<String>();
    public  List<Integer> quantity=new ArrayList<Integer>();
    public  List<Integer> price=new ArrayList<Integer>();

    public OrderItemAdapter(List<String> items, List<Integer> quantity, List<Integer> price) {
        this.items=items;
        this.quantity=quantity;
        this.price=price;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView Order,Quantity;
        public ViewHolder(View view) {
            super(view);
            Order=view.findViewById(R.id.order);
            Quantity=view.findViewById(R.id.quantity);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.order_item,viewGroup,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            viewHolder.Order.setText(items.get(position));
            viewHolder.Quantity.setText(" x"+quantity.get(position)+"                     Rs"+price.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
