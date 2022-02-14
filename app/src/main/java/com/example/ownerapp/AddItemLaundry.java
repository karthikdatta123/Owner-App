package com.example.ownerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItemLaundry extends BottomSheetDialogFragment {
    LaundryItem item;
    private String categoryName;
    private String subCategoryName;

    public AddItemLaundry() {
        // Required empty public constructor
    }

    public static AddItemLaundry newInstance(String param1, String param2) {
        AddItemLaundry fragment = new AddItemLaundry();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryName="Laundry";
        if (this.getArguments() != null) {

            // Add Item button clicked from SubCategoryAdapter
            if (this.getArguments().getString("subCategoryName") != null)
                this.subCategoryName = this.getArguments().getString("subCategoryName");

            // Edit item icon Clicked from RowAdapter
            if (this.getArguments().getSerializable("item") != null)
                item = (LaundryItem) this.getArguments().getSerializable("item");
        }
    }
    @Override
    public int getTheme() {
        return R.style.NoBackgroundDialogTheme;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button cancelButton = view.findViewById(R.id.button);
        Button applyButton = view.findViewById(R.id.button1);
        TextInputEditText textInputEditText1=view.findViewById(R.id.textInput1);
        TextInputEditText textInputEditText2= view.findViewById(R.id.textInput2);

        ImageView delete=view.findViewById(R.id.imageView1);

        if(item!=null)
        {
            String itemName = item.getName();
            String itemPrice = item.getPrice();
            textInputEditText1.setText(itemName);
            textInputEditText2.setText(itemPrice);
        }
        else{
            delete.setVisibility(View.GONE);
        }
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = textInputEditText1.getText().toString();
                String priceString=textInputEditText2.getText().toString();
                int price = Integer.parseInt(textInputEditText2.getText().toString());

                if(item!=null) {
                    DatabaseReference databaseReference = DAOOwner.getFirebaseDatabase()
                                                                  .getReference("categories")
                                                                  .child(categoryName)
                                                                  .child(subCategoryName);
                    // If name is changed
                    if(!item.getName().equals(name))
                    {
                        databaseReference.child(name).child("available").setValue(item.isAvailable());
                        //delete old item
                        DAOOwner.deleteItem(categoryName, subCategoryName, item.getName());
                    }
                    databaseReference.child(name).child("name").setValue(name);
                    databaseReference.child(name).child("price").setValue(priceString);
//                  databaseReference.child(name).child("price").setValue(price);
                }
                else{
                    LaundryItem laundryItem = new LaundryItem(name, price, true);
                    DatabaseReference databaseReference = DAOOwner.getFirebaseDatabase()
                                                                  .getReference("categories")
                                                                  .child(categoryName)
                                                                  .child(subCategoryName);
                    DatabaseReference newItemRef = databaseReference.child(name);
                    newItemRef.setValue(laundryItem);
//                    newItemRef.child("price").setValue(price);
                    newItemRef.child("price").setValue(priceString);
                }
                Toast.makeText(getContext(),"Changes Applied",Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item!=null)
                {
                    DAOOwner.deleteItem(categoryName,subCategoryName,item.getName());
                }
                dismiss();
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_addlaundry, container, false);
        view.setBackgroundResource(R.drawable.rounded_backgound);
        return view;
    }


}
