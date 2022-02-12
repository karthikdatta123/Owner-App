package com.example.ownerapp;

import java.io.Serializable;

public class LaundryItem implements Serializable {
    private String itemName;
    private int itemPrice;
    private boolean available;

    public LaundryItem(String itemName, int itemPrice, boolean available) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.available = available;
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
