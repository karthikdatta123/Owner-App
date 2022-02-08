package com.example.ownerapp;


import java.util.List;

public class SubCategory {
    private String subCategoryName;
    private List<Item> itemList;
    private boolean expanded;

    public SubCategory(String subCategoryName, List<Item> itemList)
    {
        this.subCategoryName = subCategoryName;
        this.itemList=itemList;
        expanded=false;
    }
    public SubCategory(String subCategoryName, String categoryName, List<Item> itemList)
    {
        this.subCategoryName = subCategoryName;
        this.itemList=itemList;
        expanded=false;
    }

    public String getName(){return subCategoryName;}
    public List<Item> getItems(){return itemList;}
    public boolean getExpanded(){return expanded;}
    public void setExpanded(boolean expanded)
    {
        this.expanded=expanded;
    }
}
