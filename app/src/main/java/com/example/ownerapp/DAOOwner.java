package com.example.ownerapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAOOwner {
  String TAG = "Firebase testing";
  public static FirebaseDatabase db ;

  public static FirebaseDatabase getFirebaseDatabase() {
    if (db == null) {
      db = FirebaseDatabase.getInstance();
    }
    return db;
  }
}
