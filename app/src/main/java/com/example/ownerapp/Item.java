package com.example.ownerapp;


public class Item {
    private String itemName, categoryName, subCategory;
    //private int categoryId,itemPrice;
    private int itemPrice;
    private boolean active;
    //private boolean expanded;

    public Item(String itemName, String categoryName, String subCategory,  int itemPrice, boolean isActive) {
        this.itemName = itemName;
        this.categoryName = categoryName;
        this.subCategory = subCategory;
        this.itemPrice = itemPrice;
        this.active = isActive;
        //expanded=true;
    }

    public String getName() {
        return itemName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSubCategory() {
        return subCategory;
    }

    //public int getCategoryId() {return categoryId;}

    public String  getPrice() { return Integer.toString(itemPrice); }

    public boolean isActive() {
        return active;
    }

    //public boolean isExpanded(){return expanded;}

}
