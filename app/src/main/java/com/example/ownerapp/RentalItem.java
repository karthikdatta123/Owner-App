package com.example.ownerapp;

import java.io.Serializable;

public class RentalItem implements Serializable {
    private String itemName;
    private int itemPrice;
    private boolean available;

    public RentalItem(String itemName,  int itemPrice, boolean isActive) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.available = isActive;
    }

    public String getName() {
        return itemName;
    }

    public String  getPrice() { return Integer.toString(itemPrice); }

    public boolean isAvailable() {
        return available;
    }

    public void setName(String name) {this.itemName=name;}

    public void setPrice(int price){this.itemPrice=price;}
}
