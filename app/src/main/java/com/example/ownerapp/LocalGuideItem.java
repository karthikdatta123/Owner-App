package com.example.ownerapp;

public class LocalGuideItem {
    private String itemName, categoryName, subCategory;
    //private int categoryId,itemPrice;
    private boolean active;
    private String phoneNumber;
    public String getName() {
        return itemName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public boolean isActive() {
        return active;
    }

    public String getPhoneNumber(){return phoneNumber;}
}
