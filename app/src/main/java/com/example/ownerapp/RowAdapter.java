package com.example.ownerapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RowAdapter extends RecyclerView.Adapter<ViewHolder1> {
    private List<Item> itemList;
    private static Context context;

    private int CATEGORY_FOOD_LAUNDRY=1;
    private int CATEGORY_RENTALS=0;

    public RowAdapter(List<Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;

    }
    public static Context getContext()
    {return context;}

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        Item item=itemList.get(position);
        if(item.getSubCategory().equals("LocalGuides"))return CATEGORY_RENTALS;
        else return CATEGORY_FOOD_LAUNDRY;
    }

    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view;

        //if food then row_item.xml
//        if(viewType==CATEGORY_FOOD_LAUNDRY)
//        view=layoutInflater.inflate(R.layout.row_item, parent, false);
//        else if(viewType==CATEGORY_RENTALS)
//        view=layoutInflater.inflate(R.layout.row_item_others, parent, false);

        view=layoutInflater.inflate(R.layout.row_item_food, parent, false);

        ViewHolder1 viewHolder=new ViewHolder1(view);
        return viewHolder;//manages the rows, keeps track of individual rows-all img views,text views etc
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, int position) {
        Item item=itemList.get(position);
        holder.textView.setText(item.getName());
        holder.textView2.setText(item.getPrice());

        if(item.getCategoryName().equals("Laundry"))
        {
            holder.imageView.setVisibility(View.GONE);
        }
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }}

class ViewHolder1 extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView textView,textView2;
    ImageButton imageButton;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switch1;
    public ViewHolder1(@NonNull View itemView) {
        super(itemView);

        imageView=itemView.findViewById(R.id.imageView);
        textView=itemView.findViewById(R.id.textView);
        textView2=itemView.findViewById(R.id.textView2);
        imageButton=itemView.findViewById(R.id.imageButton);
        switch1=itemView.findViewById(R.id.switch1);

        String itemActive="Item is active";
        String itemInactive="Item is inactive";

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {

                if(isChecked)
                    Toast.makeText(RowAdapter.getContext(), itemActive, Toast.LENGTH_SHORT).show();
                else Toast.makeText(RowAdapter.getContext(), itemInactive, Toast.LENGTH_SHORT).show();
            }
        });


    }
}

