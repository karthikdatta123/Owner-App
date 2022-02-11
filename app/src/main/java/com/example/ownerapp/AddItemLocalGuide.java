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

public class AddItemLocalGuide extends BottomSheetDialogFragment {
    private LocalGuideItem item;
    String categoryName;
    private String subCategoryName;

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
                item = (LocalGuideItem) this.getArguments().getSerializable("item");
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
        TextInputEditText textInputEditText1 = view.findViewById(R.id.textInput1);
        TextInputEditText textInputEditText2 = view.findViewById(R.id.textInput2);

        ImageView delete = view.findViewById(R.id.imageView1);

        if (item != null) {
            String itemName = item.getName();
            String phoneNumber = item.getPhoneNumber();
            textInputEditText1.setText(itemName);
            textInputEditText2.setText(phoneNumber);
        }
        else{
            delete.setVisibility(View.GONE);
        }
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=textInputEditText1.getText().toString();
                String phoneNumber=textInputEditText2.getText().toString();
                //Edit Item
                if(item!=null)
                {
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance()
                                                                 .getReference("categories")
                                                                 .child(categoryName)
                                                                 .child(subCategoryName);
                    databaseReference.child(name).child("name").setValue(name);
                    databaseReference.child(name).child("phoneNumber").setValue(phoneNumber);
                }
                else {
                    LocalGuideItem localGuideItem = new LocalGuideItem(name, phoneNumber, true);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                                                                  .getReference("categories")
                                                                  .child(categoryName)
                                                                  .child(subCategoryName);
                    DatabaseReference newItemRef = databaseReference.child(name);
                    newItemRef.setValue(localGuideItem);
                }
                //Add Item
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
            View view = inflater.inflate(R.layout.fragment_addlocalguide, container, false);
            view.setBackgroundResource(R.drawable.rounded_backgound);
            return view;
        }

    }
