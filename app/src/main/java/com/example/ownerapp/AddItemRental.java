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

public class AddItemRental extends BottomSheetDialogFragment {
    RentalItem item;
    private String subCategoryName;
    private String categoryName;

    public AddItemRental() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddItemRental newInstance(String param1, String param2) {
        AddItemRental fragment = new AddItemRental();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryName="Other Services";
        if (this.getArguments() != null) {

            // Add Item button clicked from SubCategoryAdapter
            if (this.getArguments().getString("subCategoryName") != null)
                this.subCategoryName = this.getArguments().getString("subCategoryName");

            // Edit item icon Clicked from RowAdapter
            if (this.getArguments().getSerializable("item") != null)
                item = (RentalItem) this.getArguments().getSerializable("item");
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
                int price = Integer.parseInt(
                        textInputEditText2.getText().toString());
                if (item != null) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                                                                  .getReference("categories")
                                                                  .child(categoryName)
                                                                  .child(subCategoryName);
                    databaseReference.child(name).child("name").setValue(name);
                    databaseReference.child(name).child("price").setValue(price);

                } else {
                    RentalItem rentalItem = new RentalItem(name, price, true);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                                                                  .getReference("categories")
                                                                  .child(categoryName)
                                                                  .child(subCategoryName);
                    DatabaseReference newItemRef = databaseReference.child(name);
                    newItemRef.setValue(rentalItem);
                }
                //Add Item
                Toast.makeText(getContext(), "Changes Applied", Toast.LENGTH_SHORT).show();
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
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance()
                                                                 .getReference("categories")
                                                                 .child(categoryName)
                                                                 .child(subCategoryName);
                    databaseReference.child(item.getName()).removeValue();
                }
                dismiss();
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_addrental, container, false);
        view.setBackgroundResource(R.drawable.rounded_backgound);
        return view;
    }

}
