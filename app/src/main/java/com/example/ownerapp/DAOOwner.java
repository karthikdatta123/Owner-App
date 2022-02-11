package com.example.ownerapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOOwner {
  public static FirebaseDatabase db ;

  public static FirebaseDatabase getFirebaseDatabase() {
    if (db == null) {
      db = FirebaseDatabase.getInstance();
    }
    return db;
  }
  public static void deleteItem(String categoryName, String subCategoryName, String itemName)
  {
    DatabaseReference databaseReference= DAOOwner.getFirebaseDatabase()
                                                 .getReference("categories")
                                                 .child(categoryName)
                                                 .child(subCategoryName);
    databaseReference.child(itemName).removeValue();
  }
}
