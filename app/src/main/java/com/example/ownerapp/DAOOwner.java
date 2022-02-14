package com.example.ownerapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class DAOOwner {
  public static FirebaseDatabase db ;
  public static FirebaseStorage firebaseStorage;

  public static FirebaseDatabase getFirebaseDatabase() {
    if (db == null) {
      db = FirebaseDatabase.getInstance();
    }
    return db;
  }
  public static FirebaseStorage getFirebaseStorage() {
    if(firebaseStorage == null){
      firebaseStorage = FirebaseStorage.getInstance();
    }
    return firebaseStorage;
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
