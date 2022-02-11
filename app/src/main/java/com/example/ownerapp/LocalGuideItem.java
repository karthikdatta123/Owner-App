package com.example.ownerapp;

import java.io.Serializable;

public class LocalGuideItem implements Serializable {
    private String itemName;
    private String phoneNumber;
    private boolean available;

    public LocalGuideItem(String itemName, String phoneNumber, boolean available)
    {
        this.itemName = itemName;
        this.phoneNumber=phoneNumber;
        this.available = available;
    }
    public String getName() {
        return itemName;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getPhoneNumber(){return phoneNumber;}

    public void setItemName(String itemName){this.itemName=itemName;}

    public void setPhoneNumber(String phoneNumber){this.phoneNumber=phoneNumber;}
}
