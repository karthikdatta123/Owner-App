package com.example.ownerapp;
import java.io.Serializable;

public class Item implements Serializable {
    private String itemName;
    String categoryName, subCategory;
    private String imageURL;
    private int itemPrice;
    private boolean active;

    public Item(String itemName, String categoryName, String subCategory,  int itemPrice, boolean isActive) {
        this.itemName = itemName;
        this.categoryName = categoryName;
        this.subCategory = subCategory;
        this.itemPrice = itemPrice;
        this.active = isActive;
    }

    public String getName() {
        return itemName;
    }

    public String getImageURL(){ return imageURL; }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String  getPrice() { return Integer.toString(itemPrice); }

    public boolean isActive() {
        return active;
    }

    public void setName(String name) {this.itemName=name;}

    public void setPrice(int price){this.itemPrice=price;}
}
