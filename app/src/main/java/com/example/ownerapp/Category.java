package com.example.ownerapp;

import java.util.List;

public class Category {
    private String categoryName;
    private List<SubCategory> subCategoryList;

    public Category(String categoryName, List<SubCategory> subCategoryList)
    {
        this.categoryName=categoryName;
        this.subCategoryList = subCategoryList;
    }
    public String getCategoryName()
    {return categoryName;}
    public List<SubCategory> getCategoryList()
    {return subCategoryList;}
}

