package com.example.ownerapp;


import java.util.ArrayList;
import java.util.List;

public class SubCategory {
    private String categoryName;
    //list of Food/laundry/rental items
    private List<Item> itemList;

    //list of local guide items
    private List<LocalGuideItem> localGuideItems;

    private boolean expanded;
    public SubCategory(String categoryName, List<Item> itemList)
    {
        this.categoryName=categoryName;
        this.itemList=itemList;
        expanded=false;
    }

    public String getName(){return categoryName;}
    public List<Item> getItems(){return itemList;}
    public boolean getExpanded(){return expanded;}
    public void setExpanded(boolean expanded)
    {
        this.expanded=expanded;
    }
}
