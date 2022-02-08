package com.example.ownerapp;

import android.content.Context;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class UpdateMenuMain extends Fragment {

    View view;
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    //Food
    List<Item> itemList1,itemList2,itemList3;
    List<SubCategory> subCategoryList;

    //Laundry
    List<Item> itemList4,itemList5,itemList6;
    List<SubCategory> subCategoryList1;

    //Other Services
    //Should actually be List<LocalGuideItem>, but hardcoding it in the adapter for now
    List<Item> itemList7,itemList8;
    List<SubCategory> subCategoryList2;

    List<Category> categoryList;
    Button button;
    TextView textView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.update_menu_main,container,false);
        super.onCreate(savedInstanceState);

        button=view.findViewById(R.id.button);
        textView=view.findViewById(R.id.textView1);

        recyclerView =view.findViewById(R.id.recyclerView);
        //recyclerView2.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        itemList1 = new ArrayList<>();
        itemList2 = new ArrayList<>();
        itemList3 = new ArrayList<>();
        subCategoryList =new ArrayList<>();

        itemList4 = new ArrayList<>();
        itemList5 = new ArrayList<>();
        itemList6 = new ArrayList<>();
        subCategoryList1 =new ArrayList<>();

        itemList7=new ArrayList<>();
        itemList8=new ArrayList<>();
        subCategoryList2 =new ArrayList<>();

        categoryList =new ArrayList<>();
        loadItems();
        recyclerView.setAdapter(categoryAdapter);
        return view;
    }
    private void loadItems() {
        Item item1=new Item("North Indian Thali","Food","Thali",80,true);
        Item item2=new Item("South Indian Thali","Food","Thali",60,true);
        Item item3=new Item("Chicken Biryani","Food","Rice And Biryani",120,true);
        Item item4=new Item("Egg Fried Rice","Food","Indo-Chinese",90,true);

        itemList1.add(item1);
        itemList1.add(item2);
        SubCategory subCategory1 =new SubCategory("Thali",itemList1);
        subCategoryList.add(subCategory1);

        itemList2.add(item3);
        SubCategory subCategory2 =new SubCategory("Rice & Biryani",itemList2);

        itemList3.add(item4);
        SubCategory subCategory3 =new SubCategory("Indo-Chinese",itemList3);

        //sub categories under Food
        subCategoryList.add(subCategory2);
        subCategoryList.add(subCategory3);

        Category categoryFood=new Category("Food", subCategoryList);


        Item item5=new Item("T-shirt","Laundry","Wash & Fold",5,true);
        Item item6=new Item("Pants","Laundry","Wash & Fold",5,true);
        Item item7=new Item("Jackets/Shirts","Laundry","Wash & Fold",5,true);
        itemList4.add(item5);
        itemList4.add(item6);
        itemList4.add(item7);

        SubCategory subCategory4 =new SubCategory("Wash & Fold",itemList4);
        SubCategory subCategory5 =new SubCategory("Dry Cleaning",itemList4);
        SubCategory subCategory6 =new SubCategory("Ironing",itemList4);

        //sub categories under Laundry
        subCategoryList1.add(subCategory4);
        subCategoryList1.add(subCategory5);
        subCategoryList1.add(subCategory6);

        Category categoryLaundry=new Category("Laundry", subCategoryList1);

        //Others

        Item item8=new Item("Bike","Other Services","Rentals",1100,true);
        itemList7.add(item8);

        Item item9=new Item("Person1","Other Services","Local Guides",0,true);
        itemList8.add(item9);

        SubCategory subCategory7=new SubCategory("Rentals",itemList7);
        SubCategory subCategory8 =new SubCategory("Local Guides",itemList8);

        subCategoryList2.add(subCategory7);
        subCategoryList2.add(subCategory8);
        Category categoryOthers=new Category("Other Services", subCategoryList2);


        categoryList.add(categoryFood);
        categoryList.add(categoryLaundry);
        categoryList.add(categoryOthers);

        categoryAdapter=new CategoryAdapter(categoryList,getApplicationContext());
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