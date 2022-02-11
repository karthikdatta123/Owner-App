package com.example.ownerapp;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

public class AddItemFood extends BottomSheetDialogFragment {
    private FoodItem item;
    private String categoryName;
    private String subCategoryName;

    public AddItemFood() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddItemFood newInstance(String param1, String param2) {
        AddItemFood fragment = new AddItemFood();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

  @Override
  public void onCreate(Bundle savedInstanceState) {
        categoryName="Food";
    super.onCreate(savedInstanceState);
    if (this.getArguments() != null) {

      // Add Item button clicked from SubCategoryAdapter
      if (this.getArguments().getString("subCategoryName") != null)
        this.subCategoryName = this.getArguments().getString("subCategoryName");

      // Edit item icon Clicked from RowAdapter
      if (this.getArguments().getSerializable("item") != null)
        item = (FoodItem) this.getArguments().getSerializable("item");
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
    ImageView editPicture = view.findViewById(R.id.imageView1);
    ImageView delete = view.findViewById(R.id.imageView2);
      SwitchMaterial switch1=view.findViewById(R.id.switch1);

    if (item != null) {
      String itemName = item.getName();
      String itemPrice = item.getPrice();
      textInputEditText1.setText(itemName);
      textInputEditText2.setText(itemPrice);
        switch1.setChecked(item.isAvailable());
        if(switch1.isChecked()){
            switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
            switch1.setTrackTintList(ColorStateList.valueOf(Color.BLACK));
        }
        else{
            switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
            switch1.setTrackTintList(ColorStateList.valueOf(Color.LTGRAY));
        }
    }
    //else- Add Item option- so make the delete button invisible
    else{
        delete.setVisibility(View.GONE);
    }

      switch1.setOnCheckedChangeListener(
              new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                      if(isChecked){
                          switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                          switch1.setTrackTintList(ColorStateList.valueOf(Color.BLACK));
                      }
                      else{
                          switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
                          switch1.setTrackTintList(ColorStateList.valueOf(Color.LTGRAY));
                      }
                  }
              }

      );

    applyButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String name=textInputEditText1.getText().toString();
              int price=Integer.parseInt(
                      textInputEditText2.getText().toString());
              boolean available=switch1.isChecked();
              //Edit Item
              if(item!=null)
              {
                  DatabaseReference databaseReference= DAOOwner.getFirebaseDatabase()
                                                               .getReference("categories")
                                                               .child("Food")
                                                               .child(subCategoryName);
                  databaseReference.child(name).child("name").setValue(name);
                  databaseReference.child(name).child("price").setValue(price);
                  databaseReference.child(name).child("available").setValue(available);
              }
              //Add Item
              else {
                  FoodItem foodItem = new FoodItem(name, price, "", available);
                  DatabaseReference databaseReference = DAOOwner.db
                                                                .getReference("categories")
                                                                .child("Food")
                                                                .child(subCategoryName);
                  DatabaseReference newItemRef = databaseReference.child(name);
                  newItemRef.setValue(foodItem);
              }
              Toast.makeText(getContext(), "Changes Applied", Toast.LENGTH_SHORT).show();
            dismiss();
          }
        });
    cancelButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            dismiss();
          }
        });
    delete.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(item!=null)
              {
                  DatabaseReference databaseReference= DAOOwner.getFirebaseDatabase()
                                                               .getReference("categories")
                                                               .child("Food")
                                                               .child(subCategoryName);
                  databaseReference.child(item.getName()).removeValue();
                  dismiss();
              }
          }
        });
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_addfood, container, false);
    view.setBackgroundResource(R.drawable.rounded_backgound);
    return view;
  }
}
