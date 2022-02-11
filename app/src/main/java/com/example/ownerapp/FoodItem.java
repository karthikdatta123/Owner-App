package com.example.ownerapp;
import java.io.Serializable;

public class FoodItem implements Serializable {
	private String itemName;
	private String imageURL;
	private Integer itemPrice;
	private boolean available;

	public FoodItem(String itemName, int itemPrice, String imageURL ,boolean isActive) {
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.imageURL = imageURL;
		this.available = isActive;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getName() {
		return itemName;
	}

	public String getImageURL(){ return imageURL; }

	public String  getPrice() { return Integer.toString(itemPrice); }

	public boolean isAvailable() {
		return available;
	}

	public void setName(String name) {this.itemName=name;}

	public void setPrice(int price){this.itemPrice=price;}
}
