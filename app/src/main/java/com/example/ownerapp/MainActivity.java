package com.example.ownerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    //Food
    List<Item> itemList1,itemList2,itemList3;
    List<SubCategory> subCategoryList;

    //Laundry
    List<Item> itemList4,itemList5,itemList6;
    List<SubCategory> subCategoryList1;

    List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =findViewById(R.id.recyclerView);
        //recyclerView2.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList1 = new ArrayList<>();
        itemList2 = new ArrayList<>();
        itemList3 = new ArrayList<>();
        subCategoryList =new ArrayList<>();

        itemList4 = new ArrayList<>();
        itemList5 = new ArrayList<>();
        itemList6 = new ArrayList<>();
        subCategoryList1 =new ArrayList<>();

        categoryList =new ArrayList<>();
        loadItems();
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

        categoryList.add(categoryFood);
        categoryList.add(categoryLaundry);
        categoryAdapter=new CategoryAdapter(categoryList,getApplicationContext());
        recyclerView.setAdapter(categoryAdapter);
    }
}
