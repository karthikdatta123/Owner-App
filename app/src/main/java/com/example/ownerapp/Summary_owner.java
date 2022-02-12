package com.example.ownerapp;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Summary_owner extends Fragment {
View view;
List<Orders> list=new ArrayList<Orders>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        view=inflater.inflate(R.layout.summary_owner,container,false);
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
            }
        };
        //requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        RecyclerView recyclerView;
        ImageView back;
        RecyclerView.LayoutManager layoutManager;
        recyclerView=view.findViewById(R.id.Recycle_view);
        back=view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new UpdateMenuMain();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.current, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Orders");
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));



        myRef.addValueEventListener(new ValueEventListener() {
           @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               list=new ArrayList<Orders>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {  Orders order =new Orders();
                    Room room=new Room();
                    String name=snapshot.getValue().toString();
                    Log.d("firebase",name);
                    room.room_no=Integer.parseInt(snapshot.child("roomID").getValue().toString());
                    order.date=snapshot.child("date").getValue().toString();
                    Log.d("f",order.date);
                    room.time=snapshot.child("time").getValue().toString();

                    for(DataSnapshot itemSnapshot: snapshot.child("items").getChildren())
                    {
                        String rec = itemSnapshot.child("name").getValue().toString();
                        room.items.add(rec);
                        Log.d("k",rec);
                        room.quantity.add(Integer.parseInt(itemSnapshot.child("quantity").getValue().toString()));
                        room.price.add(Integer.parseInt(itemSnapshot.child("totalItemPrice").getValue().toString()));

                    }
                    room.total_price=Integer.parseInt(snapshot.child("billingPrice").getValue().toString());
                    room.confirm= Boolean.parseBoolean(snapshot.child("confirmation").getValue().toString());

                   int flag=0;
                    for(int i=0;i<list.size();i++)
                    {
                        Orders order1=new Orders();
                        order1.date=list.get(i).date;
                        if(order1.date.equals(order.date))
                        {  Log.d("m","hi");
                            list.get(i).rooms.add(0,room);
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0)
                    {order.rooms.add(room);
                        list.add(0,order);
                    }
                }
               DateAdapter date_view=new DateAdapter(list);
               recyclerView.setAdapter(date_view);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        return view;
    }
}