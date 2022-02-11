package com.example.ownerapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.ownerapp.databinding.FragmentFirstBinding;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class UpdateMenuMain extends Fragment {

    private List<FoodItem> foodItemList = new ArrayList<>();
    private List<LaundryItem> laundryItemList = new ArrayList<>();
    private List<RentalItem> rentalItemList = new ArrayList<>();
    private List<LocalGuideItem> touristItemList = new ArrayList<>();
    private List<SubCategory> subcategoryList = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();

    View view;
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    Button button;
    TextView textView,orders;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.update_menu_main,container,false);
        super.onCreate(savedInstanceState);
        button=view.findViewById(R.id.button);
        textView=view.findViewById(R.id.textView1);
        orders = view.findViewById(R.id.textView1);

        recyclerView =view.findViewById(R.id.recyclerView);
        //recyclerView2.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        loadItems();
        return view;
    }
    private void loadItems() {
        FirebaseDatabase.getInstance().getReference("categories")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        categoryList = new ArrayList<>();
                        if (snapshot.exists()) {
                            for (DataSnapshot category : snapshot.getChildren()) {
                                if (category.exists()) {
                                    String categoryName = category.getKey().toString();
                                    subcategoryList = new ArrayList<>();
                                    for (DataSnapshot subcategory : category.getChildren()) {
                                        if (subcategory.exists()) {
                                            foodItemList = new ArrayList<>();
                                            laundryItemList = new ArrayList<>();
                                            rentalItemList = new ArrayList<>();
                                            touristItemList = new ArrayList<>();

                                            String subcategoryName = subcategory.getKey().toString();
                                            for (DataSnapshot item : subcategory.getChildren()) {
                                                if (item.exists()) {
                                                    try {
                                                        Log.d("Item names ", item.child("name").getValue().toString());
                                                        if (categoryName.equals("Food")) {
                                                            FoodItem foodItem = new FoodItem(
                                                                    item.child("name").getValue().toString(),
                                                                    Integer.parseInt(item.child("price").getValue().toString()),
                                                                    item.child("imageURL").getValue().toString(),
                                                                    Boolean.parseBoolean(item.child("available").getValue().toString())
                                                            );
                                                            foodItemList.add(foodItem);
                                                        } else if (categoryName.equals("Laundry")) {
                                                            laundryItemList.add(new LaundryItem(
                                                                    item.child("name").getValue().toString(),
                                                                    Integer.parseInt(item.child("price").getValue().toString()),
                                                                    Boolean.parseBoolean(item.child("available").getValue().toString())
                                                            ));
                                                        } else {
                                                            if (subcategoryName.equals("Rentals")) {
                                                                rentalItemList.add(new RentalItem(
                                                                        item.child("name").getValue().toString(),
                                                                        Integer.parseInt(item.child("price").getValue().toString()),
                                                                        Boolean.parseBoolean(item.child("available").getValue().toString())
                                                                ));
                                                            } else {
                                                                touristItemList.add(new LocalGuideItem(
                                                                        item.child("name").getValue().toString(),
                                                                        item.child("phoneNumber").getValue().toString(),
                                                                        Boolean.parseBoolean(item.child("available").getValue().toString())
                                                                ));
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                            subcategoryList.add(new SubCategory(subcategoryName, foodItemList, laundryItemList, rentalItemList, touristItemList));
                                        }
                                    }
                                    categoryList.add(new Category(categoryName, subcategoryList));
                                }
                            }
                        }
                        categoryAdapter = new CategoryAdapter(categoryList, getApplicationContext());
                        recyclerView.setAdapter(categoryAdapter);
                        categoryAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }

    private Context getApplicationContext() {
        return this.getContext();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}