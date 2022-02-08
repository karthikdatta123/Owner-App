package com.example.ownerapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
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
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.io.Serializable;
import java.util.List;

public class RowAdapter extends RecyclerView.Adapter {
    private List<Item> itemList;

    private static Context context;

    private int CATEGORY_FOOD =0;
    private int CATEGORY_LAUNDRY =1;
    private int CATEGORY_RENTALS=2;
    private int CATEGORY_LOCALGUIDES =3;

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
        if(item.getCategoryName().equals("Food"))return CATEGORY_FOOD;
        if(item.getCategoryName().equals("Laundry"))return CATEGORY_LAUNDRY;
        if(item.getSubCategory().equals("Rentals"))return CATEGORY_RENTALS;
        if(item.getSubCategory().equals("Local Guides"))return CATEGORY_LOCALGUIDES;
        else return CATEGORY_FOOD;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view;

        //if food then row_item.xml
        if(viewType== CATEGORY_FOOD)
        {
            view=layoutInflater.inflate(R.layout.row_item_food, parent, false);
            ViewHolder1 viewHolder=new ViewHolder1(view);
            return  viewHolder;
        }
        else if(viewType==CATEGORY_LAUNDRY)
        {
            view=layoutInflater.inflate(R.layout.row_item_laundry, parent, false);
            ViewHolder2 viewHolder=new ViewHolder2(view);
            return viewHolder;
        }
        else if(viewType==CATEGORY_RENTALS)
        {
            view=layoutInflater.inflate(R.layout.row_item_rentals, parent, false);
            ViewHolder3 viewHolder=new ViewHolder3(view);
            return viewHolder;
        }
        else if(viewType==CATEGORY_LOCALGUIDES)
        {
            view=layoutInflater.inflate(R.layout.row_item_localguide, parent, false);
            ViewHolder4 viewHolder=new ViewHolder4(view);
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType=getItemViewType(position);
        if(viewType== CATEGORY_FOOD)
        {
            ViewHolder1 holder1=(ViewHolder1)holder;
            Item item=itemList.get(position);
            holder1.textView.setText(item.getName());
            holder1.textView2.setText(item.getPrice());
        }
        else if(viewType== CATEGORY_LAUNDRY)
        {
            ViewHolder2 holder1=(ViewHolder2) holder;
            Item item=itemList.get(position);
            holder1.textView.setText(item.getName());
            holder1.textView2.setText(item.getPrice());
        }
        else if(viewType== CATEGORY_RENTALS)
        {
            ViewHolder3 holder1=(ViewHolder3) holder;
            holder1.textView.setText("Bike");
            holder1.textView2.setText("1100"+"/day");
//            Item item=itemList.get(position);
//            holder1.textView.setText(item.getName());
//            holder1.textView2.setText(item.getPrice());
        }
        else if(viewType==CATEGORY_LOCALGUIDES)
        {
            ViewHolder4 holder2=(ViewHolder4)holder;

            //Hard coded data for now

            holder2.textView.setText("Person1");
            holder2.textView1.setText("9834275490");
//          RentalItem item=itemList.get(Position);
//          holder2.textView.setText(item.getName());
        }

    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }

//FOOD-row_item_food.xml
class ViewHolder1 extends RecyclerView.ViewHolder{

    ConstraintLayout constraintLayout;
    ImageView imageView;
    TextView textView,textView2;
    ImageButton imageButton;
    SwitchMaterial switch1;

    public ViewHolder1(@NonNull View itemView) {
        super(itemView);

        constraintLayout=itemView.findViewById(R.id.constraintLayout);
        imageView=itemView.findViewById(R.id.imageView);
        textView=itemView.findViewById(R.id.textView);
        textView2=itemView.findViewById(R.id.textView2);
        imageButton=itemView.findViewById(R.id.imageButton);
        switch1=itemView.findViewById(R.id.switch1);

        String itemActive="Item is active";
        String itemInactive="Item is inactive";

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked) {
//                  imageView.setForeground(Drawable.createFromPath(null));
                    Toast.makeText(RowAdapter.getContext(), itemActive, Toast.LENGTH_SHORT).show();
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.BLACK));
                }
                else
                {
                    //imageView.setForeground(Drawable.createFromPath("#961C2229"));
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.LTGRAY));
                    Toast.makeText(RowAdapter.getContext(), itemInactive, Toast.LENGTH_SHORT).show();
                }
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Item item=itemList.get(getAdapterPosition());
//                //send the item data
               AddItemFood addItemFood= new AddItemFood();
//                // pass arguments to fragment
                Bundle bundle = new Bundle();

//                // item we want to populate
               bundle.putSerializable("item", (Serializable) item);

                addItemFood.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentManager manager=activity.getSupportFragmentManager();
                addItemFood.show(manager,addItemFood.getTag());
                notifyItemChanged(getAdapterPosition());

            }
        });
    }
}

//Laundry-row_item_laundry.xml
class ViewHolder2 extends RecyclerView.ViewHolder{
    ConstraintLayout constraintLayout;
    TextView textView,textView2;
    ImageButton imageButton;
    SwitchMaterial switch1;

    public ViewHolder2(@NonNull View itemView) {
        super(itemView);

        constraintLayout=itemView.findViewById(R.id.constraintLayout);
        textView=itemView.findViewById(R.id.textView);
        textView2=itemView.findViewById(R.id.textView2);
        imageButton=itemView.findViewById(R.id.imageButton);
        switch1=itemView.findViewById(R.id.switch1);

        String itemActive="Item is active";
        String itemInactive="Item is inactive";

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked) {
                    Toast.makeText(RowAdapter.getContext(), itemActive, Toast.LENGTH_SHORT).show();
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.BLACK));
                }
                else
                {
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.LTGRAY));
                    Toast.makeText(RowAdapter.getContext(), itemInactive, Toast.LENGTH_SHORT).show();
                }
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Item item=itemList.get(getAdapterPosition());
                LaundryItem item1=new LaundryItem(item.getName(),item.getCategoryName(),item.getSubCategory(),
                Integer.parseInt(item.getPrice()),true);

                AddItemLaundry addItemLaundry=new AddItemLaundry();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", (Serializable) item1);

                addItemLaundry.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentManager manager=activity.getSupportFragmentManager();
                addItemLaundry.show(manager,addItemLaundry.getTag());
                notifyItemChanged(getAdapterPosition());
            }
        });
    }
}

//Rentals-row_item_rentals.xml
class ViewHolder3 extends RecyclerView.ViewHolder{

    ConstraintLayout constraintLayout;
    TextView textView,textView2;
    ImageButton imageButton;
    SwitchMaterial switch1;

    public ViewHolder3(@NonNull View itemView) {
        super(itemView);
        constraintLayout=itemView.findViewById(R.id.constraintLayout);
        textView=itemView.findViewById(R.id.textView);
        textView2=itemView.findViewById(R.id.textView2);
        imageButton=itemView.findViewById(R.id.imageButton);
        switch1=itemView.findViewById(R.id.switch1);

        String itemActive="Item is active";
        String itemInactive="Item is inactive";

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked) {
                    Toast.makeText(RowAdapter.getContext(), itemActive, Toast.LENGTH_SHORT).show();
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.BLACK));
                }
                else
                {
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.LTGRAY));
                    Toast.makeText(RowAdapter.getContext(), itemInactive, Toast.LENGTH_SHORT).show();
                }
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Item item=itemList.get(getAdapterPosition());
                RentalItem item1=new RentalItem(item.getName(),item.getCategoryName(),item.getSubCategory(),
                        Integer.parseInt(item.getPrice()),true);

                AddItemRental addItemRental=new AddItemRental();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", (Serializable) item1);
                addItemRental.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentManager manager=activity.getSupportFragmentManager();
                addItemRental.show(manager,addItemRental.getTag());
                notifyItemChanged(getAdapterPosition());
            }
        });
    }
}
//LocalGuides-row_item_localguides.xml

class ViewHolder4 extends RecyclerView.ViewHolder{


    TextView textView,textView1;
    ImageButton imageButton;
    SwitchMaterial switch1;

    public ViewHolder4(@NonNull View itemView) {
        super(itemView);

        textView=itemView.findViewById(R.id.textView);
        textView1=itemView.findViewById(R.id.textView1);
        imageButton=itemView.findViewById(R.id.imageButton);
        switch1=itemView.findViewById(R.id.switch1);

        String itemActive="Item is active";
        String itemInactive="Item is inactive";

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                if(isChecked) {
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.BLACK));
                    Toast.makeText(RowAdapter.getContext(), itemActive, Toast.LENGTH_SHORT).show();
                }
                else {
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.LTGRAY));
                    Toast.makeText(RowAdapter.getContext(), itemInactive, Toast.LENGTH_SHORT).show();
                }
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Item item=itemList.get(getAdapterPosition());
                LocalGuideItem item1=new LocalGuideItem(item.getName(),item.getCategoryName(),item.getSubCategory(),
                        item.getPrice(),true);

                AddItemLocalGuide addItemRental=new AddItemLocalGuide();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", (Serializable) item1);

                addItemRental.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentManager manager=activity.getSupportFragmentManager();
                addItemRental.show(manager,addItemRental.getTag());
                notifyItemChanged(getAdapterPosition());
            }
        });
    }
}}

