package com.example.ownerapp;

public class LocalGuideItem {
    private String itemName, categoryName, subCategory;
    private String phoneNumber;
    //private int categoryId,itemPrice;
    private boolean active;

    public LocalGuideItem(String itemName, String categoryName, String subCategory,  String phoneNumber, boolean isActive)
    {
        this.itemName = itemName;
        this.categoryName = categoryName;
        this.subCategory = subCategory;
        this.phoneNumber=phoneNumber;
        this.active = isActive;
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

    public boolean isActive() {
        return active;
    }

    public String getPhoneNumber(){return phoneNumber;}
}
