package com.example.ownerapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class RowAdapter extends RecyclerView.Adapter {
    private SubCategory subCategory;
    private String categoryName;

    private static Context context;

    private final int CATEGORY_FOOD =0;
    private final int CATEGORY_LAUNDRY =1;
    private final int CATEGORY_RENTALS=2;
    private final int CATEGORY_LOCALGUIDES =3;
    private int CATEGORY;

    public RowAdapter(String categoryName,SubCategory subCategory, Context context) {
        Log.d("Row Adapter ",subCategory.getSubCategoryName()+" "+
                                     subCategory.getFoodItemList().size()+" "+
                                     subCategory.getLaundryItemList().size()+" "+
                                     subCategory.getRentalItemList().size()+"  "+
                                     subCategory.getTouristItemList().size()+"\n");
        this.categoryName=categoryName;
        this.subCategory=subCategory;
        this.context = context;
        setCategoryType();
    }
    public void setCategoryType()
    {
        this.CATEGORY=CATEGORY_FOOD;
        if(categoryName.equals("Food"))
            CATEGORY= ((int) CATEGORY_FOOD);
        else if(categoryName.equals("Laundry"))
            CATEGORY=CATEGORY_LAUNDRY;
        else if(subCategory.getSubCategoryName().equals("Rentals"))
            CATEGORY=CATEGORY_RENTALS;
        else if(subCategory.getSubCategoryName().equals("Tourist Guide"))
            CATEGORY=CATEGORY_LOCALGUIDES;
    }
    public static Context getContext()
    {return context;}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view;

        //if food then row_item.xml
        if(CATEGORY== CATEGORY_FOOD)
        {
            view=layoutInflater.inflate(R.layout.row_item_food, parent, false);
            ViewHolder1 viewHolder=new ViewHolder1(view);
            return  viewHolder;
        }
        else if(CATEGORY==CATEGORY_LAUNDRY)
        {
            view=layoutInflater.inflate(R.layout.row_item_laundry, parent, false);
            ViewHolder2 viewHolder=new ViewHolder2(view);
            return viewHolder;
        }
        else if(CATEGORY==CATEGORY_RENTALS)
        {
            view=layoutInflater.inflate(R.layout.row_item_rentals, parent, false);
            ViewHolder3 viewHolder=new ViewHolder3(view);
            return viewHolder;
        }
        else if(CATEGORY == CATEGORY_LOCALGUIDES)
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
        if(CATEGORY== CATEGORY_FOOD)
        {
            ViewHolder1 holder1=(ViewHolder1)holder;
            FoodItem item=subCategory.getFoodItemList().get(position);
            holder1.switch1.setChecked(item.isAvailable());
            holder1.textView.setText(item.getName());
            holder1.textView2.setText(item.getPrice());
            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("download.jpeg");
            try {
                final File localFile = File.createTempFile("download", "jpeg");
                storageRef.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Log.d("Image", ""+taskSnapshot);
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                holder1.imageView.setImageBitmap(bitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(CATEGORY== CATEGORY_LAUNDRY)
        {
            ViewHolder2 holder1=(ViewHolder2) holder;
            LaundryItem item=subCategory.getLaundryItemList().get(position);
            holder1.switch1.setChecked(item.isAvailable());
            holder1.textView.setText(item.getName());
            holder1.textView2.setText(item.getPrice());
        }
        if(CATEGORY== CATEGORY_RENTALS)
        {
            ViewHolder3 holder1=(ViewHolder3) holder;
            RentalItem item=subCategory.getRentalItemList().get(position);
            holder1.switch1.setChecked(item.isAvailable());
            holder1.textView.setText(item.getName());
            holder1.textView2.setText(item.getPrice()+"/day");
        }
        if(CATEGORY==CATEGORY_LOCALGUIDES)
        {
            ViewHolder4 holder1=(ViewHolder4)holder;
            LocalGuideItem item=subCategory.getTouristItemList().get(position);
            holder1.switch1.setChecked(item.isAvailable());
            holder1.textView.setText(item.getName());
            holder1.textView1.setText(item.getPhoneNumber());
        }
    }
    @Override
    public int getItemCount() {
        if(CATEGORY==CATEGORY_FOOD)return subCategory.getFoodItemList().size();
        if(CATEGORY==CATEGORY_LAUNDRY)return subCategory.getLaundryItemList().size();
        if(CATEGORY==CATEGORY_LOCALGUIDES)return subCategory.getTouristItemList().size();
        if(CATEGORY==CATEGORY_RENTALS)return subCategory.getRentalItemList().size();
        return subCategory.getFoodItemList().size();

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
                FoodItem item;
                item = subCategory.getFoodItemList().get(getAdapterPosition());

                if (isChecked) {
                    FirebaseDatabase.getInstance()
                            .getReference("categories")
                            .child(categoryName)
                            .child(subCategory.getSubCategoryName())
                            .addListenerForSingleValueEvent(
                                    new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                for (DataSnapshot items : snapshot.getChildren()) {
                                                    String name = items.child("name").getValue().toString();
                                                    Boolean available = Boolean.parseBoolean(items.child("available").getValue().toString());
                                                    if(item.getName().equals(name)){
                                                        items.child("available").getRef().setValue(true);
                                                    }
                                                }
                                            }}
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    }
                            );
                    Toast.makeText(RowAdapter.getContext(), itemActive, Toast.LENGTH_SHORT).show();
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.BLACK));
                }
                else
                {
                    FirebaseDatabase.getInstance()
                            .getReference("categories")
                            .child(categoryName)
                            .child(subCategory.getSubCategoryName())
                            .addListenerForSingleValueEvent(
                                    new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                for (DataSnapshot items : snapshot.getChildren()) {
                                                    String name = items.child("name").getValue().toString();
                                                    Boolean available = Boolean.parseBoolean(items.child("available").getValue().toString());
                                                    if(item.getName().equals(name)){
                                                        items.child("available").getRef().setValue(false);
                                                        Log.d("Snapshot", ""+items.child("available").getValue().toString());
                                                    }
                                                }
                                            }}
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    }
                            );
                    imageView.setForeground(Drawable.createFromPath("#961C2229"));
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.LTGRAY));
                    Toast.makeText(RowAdapter.getContext(), itemInactive, Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodItem item=subCategory.getFoodItemList().get(getAdapterPosition());
//                send the item data
                AddItemFood addItemFood= new AddItemFood();
//                pass arguments to fragment
                Bundle bundle = new Bundle();

//                item we want to populate
                bundle.putSerializable("item", (Serializable) item);
                bundle.putString("subCategoryName", subCategory.getSubCategoryName());
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
                LaundryItem item;
                item = subCategory.getLaundryItemList().get(getAdapterPosition());

                if (isChecked) {
                    FirebaseDatabase.getInstance()
                            .getReference("categories")
                            .child(categoryName)
                            .child(subCategory.getSubCategoryName())
                            .addListenerForSingleValueEvent(
                                    new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                for (DataSnapshot items : snapshot.getChildren()) {
                                                    String name = items.child("name").getValue().toString();
                                                    Boolean available = Boolean.parseBoolean(items.child("available").getValue().toString());
                                                    if(item.getName().equals(name)){
                                                        items.child("available").getRef().setValue(true);
                                                        Log.d("Snapshot", ""+items.child("available").getValue().toString());
                                                    }
                                                }
                                            }}
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    }
                            );
                    Toast.makeText(RowAdapter.getContext(), itemActive, Toast.LENGTH_SHORT).show();
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.BLACK));
                }
                else
                {
                    FirebaseDatabase.getInstance()
                            .getReference("categories")
                            .child(categoryName)
                            .child(subCategory.getSubCategoryName())
                            .addListenerForSingleValueEvent(
                                    new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                for (DataSnapshot items : snapshot.getChildren()) {
                                                    String name = items.child("name").getValue().toString();
                                                    Boolean available = Boolean.parseBoolean(items.child("available").getValue().toString());
                                                    if(item.getName().equals(name)){
                                                        items.child("available").getRef().setValue(false);
                                                        Log.d("Snapshot", ""+items.child("available").getValue().toString());
                                                    }
                                                }
                                            }}


                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    }
                            );
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.LTGRAY));
                    Toast.makeText(RowAdapter.getContext(), itemInactive, Toast.LENGTH_SHORT).show();
                }
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LaundryItem item=subCategory.getLaundryItemList().get(getAdapterPosition());
                LaundryItem item1=new LaundryItem(item.getName(), Integer.parseInt(item.getPrice()),true);

                AddItemLaundry addItemLaundry=new AddItemLaundry();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", (Serializable) item1);
                bundle.putString("subCategoryName", subCategory.getSubCategoryName());

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
                RentalItem item;
                item = subCategory.getRentalItemList().get(getAdapterPosition());

                if (isChecked) {
                    FirebaseDatabase.getInstance()
                            .getReference("categories")
                            .child(categoryName)
                            .child(subCategory.getSubCategoryName())
                            .addListenerForSingleValueEvent(
                                    new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                for (DataSnapshot items : snapshot.getChildren()) {
                                                    String name = items.child("name").getValue().toString();
                                                    Boolean available = Boolean.parseBoolean(items.child("available").getValue().toString());
                                                    if(item.getName().equals(name)){
                                                        items.child("available").getRef().setValue(true);
                                                        Log.d("Snapshot", ""+items.child("available").getValue().toString());
                                                    }
                                                }
                                            }}
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    }
                            );
                    Toast.makeText(RowAdapter.getContext(), itemActive, Toast.LENGTH_SHORT).show();
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.BLACK));
                }
                else
                {
                    FirebaseDatabase.getInstance()
                            .getReference("categories")
                            .child(categoryName)
                            .child(subCategory.getSubCategoryName())
                            .addListenerForSingleValueEvent(
                                    new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                for (DataSnapshot items : snapshot.getChildren()) {
                                                    String name = items.child("name").getValue().toString();
                                                    Boolean available = Boolean.parseBoolean(items.child("available").getValue().toString());
                                                    if(item.getName().equals(name)){
                                                        items.child("available").getRef().setValue(false);
                                                        Log.d("Snapshot", ""+items.child("available").getValue().toString());
                                                    }
                                                }
                                            }}


                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    }
                            );
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.LTGRAY));
                    Toast.makeText(RowAdapter.getContext(), itemInactive, Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                RentalItem item=subCategory.getRentalItemList().get(getAdapterPosition());
                RentalItem item1=new RentalItem(item.getName(),Integer.parseInt(item.getPrice()),true);

                AddItemRental addItemRental=new AddItemRental();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", (Serializable) item1);
                bundle.putString("subCategoryName",subCategory.getSubCategoryName());
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
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                LocalGuideItem item;
                item = subCategory.getTouristItemList().get(getAdapterPosition());

                if (isChecked) {
                    FirebaseDatabase.getInstance()
                            .getReference("categories")
                            .child(categoryName)
                            .child(subCategory.getSubCategoryName())
                            .addListenerForSingleValueEvent(
                                    new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                for (DataSnapshot items : snapshot.getChildren()) {
                                                    String name = items.child("name").getValue().toString();
                                                    Boolean available = Boolean.parseBoolean(items.child("available").getValue().toString());
                                                    if(item.getName().equals(name)){
                                                        items.child("available").getRef().setValue(true);
                                                        Log.d("Snapshot", ""+items.child("available").getValue().toString());
                                                    }
                                                }
                                            }}
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    }
                            );
                    Toast.makeText(RowAdapter.getContext(), itemActive, Toast.LENGTH_SHORT).show();
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.BLACK));
                }
                else
                {
                    FirebaseDatabase.getInstance()
                            .getReference("categories")
                            .child(categoryName)
                            .child(subCategory.getSubCategoryName())
                            .addListenerForSingleValueEvent(
                                    new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                for (DataSnapshot items : snapshot.getChildren()) {
                                                    String name = items.child("name").getValue().toString();
                                                    Boolean available = Boolean.parseBoolean(items.child("available").getValue().toString());
                                                    if(item.getName().equals(name)){
                                                        items.child("available").getRef().setValue(false);
                                                        Log.d("Snapshot", ""+items.child("available").getValue().toString());
                                                    }
                                                }
                                            }}


                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    }
                            );
                    switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                    switch1.setTrackTintList(ColorStateList.valueOf(Color.LTGRAY));
                    Toast.makeText(RowAdapter.getContext(), itemInactive, Toast.LENGTH_SHORT).show();
                }
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LocalGuideItem item=subCategory.getTouristItemList().get(getAdapterPosition());
//                LocalGuideItem item1=new LocalGuideItem(item.getName(),item.getCategoryName(),item.getSubCategory(),
//                        item.getPrice(),true);
                LocalGuideItem item1=new LocalGuideItem(item.getName(),item.getPhoneNumber(),true);

                AddItemLocalGuide addItemRental=new AddItemLocalGuide();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", (Serializable) item1);
                bundle.putString("subCategoryName", subCategory.getSubCategoryName());

                addItemRental.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentManager manager=activity.getSupportFragmentManager();
                addItemRental.show(manager,addItemRental.getTag());
                notifyItemChanged(getAdapterPosition());
            }
        });
    }
}}

