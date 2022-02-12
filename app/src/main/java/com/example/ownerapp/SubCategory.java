package com.example.ownerapp;

import java.util.List;

public class SubCategory {
  private String subCategoryName;
  private List<FoodItem> foodItemList;
  private List<LaundryItem> laundryItemList;
  private List<RentalItem> rentalItemList;
  private List<LocalGuideItem> touristItemList;
  private boolean expanded;

  public SubCategory(
      String subCategoryName,
      List<FoodItem> foodItemList,
      List<LaundryItem> laundryItemList,
      List<RentalItem> rentalItemList,
      List<LocalGuideItem> touristItemList) {
    this.subCategoryName = subCategoryName;
    this.foodItemList = foodItemList;
    this.laundryItemList = laundryItemList;
    this.rentalItemList = rentalItemList;
    this.touristItemList = touristItemList;
    this.expanded = false;
  }

  public String getSubCategoryName() {
    return subCategoryName;
  }

  public void setSubCategoryName(String subCategoryName) {
    this.subCategoryName = subCategoryName;
  }

  public List<FoodItem> getFoodItemList() {
    return foodItemList;
  }

  public void setFoodItemList(List<FoodItem> foodItemList) {
    this.foodItemList = foodItemList;
  }

  public List<LaundryItem> getLaundryItemList() {
    return laundryItemList;
  }

  public void setLaundryItemList(List<LaundryItem> laundryItemList) {
    this.laundryItemList = laundryItemList;
  }

  public List<RentalItem> getRentalItemList() {
    return rentalItemList;
  }

  public void setRentalItemList(List<RentalItem> rentalItemList) {
    this.rentalItemList = rentalItemList;
  }

  public List<LocalGuideItem> getTouristItemList() {
    return touristItemList;
  }

  public void setTouristItemList(List<LocalGuideItem> touristItemList) {
    this.touristItemList = touristItemList;
  }

  public boolean isExpanded() {
    return expanded;
  }

  public void setExpanded(boolean expanded) {
    this.expanded = expanded;
  }
}
