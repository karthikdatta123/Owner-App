package com.example.ownerapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DAOOwner {
    private DatabaseReference databaseReference;
    public DAOOwner()
    {
//        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        databaseReference = db.getReference(Item.class.getSimpleName());
////        databaseReference = db.getReference(categories.class.getSimpleName());
        Log.d("firebase testing","dgfhdgfh");
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("categories");
        Task<DataSnapshot> d= myRef.get();

        //for(DataSnapshot d1:d)
//        for(Task<DataSnapshot> d:myRef.get())
//        {
//
//        }

        FirebaseDatabase.getInstance().getReference("categories")
                .addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("firebase testing","dgfhdgfh");
                        if(snapshot.exists())
                        {
                            for(DataSnapshot d:snapshot.getChildren())
                            //for(int i=0;i<snapshot.getChildrenCount();i++)
                            {
                                String name=d.getKey().toString();
                                Log.d("firebase testing",name);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                } );


}}
