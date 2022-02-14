package com.example.ownerapp;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.provider.MediaStore;
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
  public String imageURL;

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
    categoryName = "Food";
    imageURL = "";
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
    ImageView uploadImage = view.findViewById(R.id.imageView2);
    ImageView delete = view.findViewById(R.id.imageView1);
    SwitchMaterial switch1 = view.findViewById(R.id.switch1);

    if (item != null) {
      String itemName = item.getName();
      String itemPrice = item.getPrice();
      textInputEditText1.setText(itemName);
      textInputEditText2.setText(itemPrice);
      switch1.setChecked(item.isAvailable());

      if (switch1.isChecked()) {
        switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
        switch1.setTrackTintList(ColorStateList.valueOf(Color.BLACK));
      } else {
        switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
        switch1.setTrackTintList(ColorStateList.valueOf(Color.LTGRAY));
      }
    }
    // else- Add Item option- so make the delete button invisible
    else {
      delete.setVisibility(View.GONE);
    }

    switch1.setOnCheckedChangeListener(
        new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (isChecked) {
              switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
              switch1.setTrackTintList(ColorStateList.valueOf(Color.BLACK));
            } else {
              switch1.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
              switch1.setTrackTintList(ColorStateList.valueOf(Color.LTGRAY));
            }
          }
        });
    uploadImage.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            openFileChooser();
          }
        });

    applyButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            String name = textInputEditText1.getText().toString();
            int price = Integer.parseInt(textInputEditText2.getText().toString());
            boolean available = switch1.isChecked();

            // Edit Item
            if (item != null) {

              DatabaseReference databaseReference =
                  DAOOwner.getFirebaseDatabase()
                      .getReference("categories")
                      .child("Food")
                      .child(subCategoryName);
              databaseReference.child(name).child("name").setValue(name);
              databaseReference.child(name).child("price").setValue(price);
              // If name is changed
              if(!item.getName().equals(name))
              {
                databaseReference.child(name).child("available").setValue(item.isAvailable());
                databaseReference.child(name).child("imageURL").setValue(item.getImageURL());
                //delete old item
                DAOOwner.deleteItem(categoryName, subCategoryName, item.getName());
              }
              if (!imageURL.equals("")) {
                databaseReference.child(name).child("imageURL").setValue(imageURL);
              }
              databaseReference.child(name).child("available").setValue(available);
            }
            // Add Item
            else {
              //FoodItem foodItem = new FoodItem(name, price, "", available);

              FoodItem foodItem = new FoodItem(name, price, imageURL, available);
              DatabaseReference databaseReference =
                  DAOOwner.getFirebaseDatabase()
                      .getReference("categories")
                      .child("Food")
                      .child(subCategoryName);
              DatabaseReference newItemRef = databaseReference.child(name);
              newItemRef.setValue(foodItem);
              newItemRef.child("price").setValue(price);
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
            if (item != null) {
              DAOOwner.deleteItem(categoryName, subCategoryName, item.getName());
              if (!item.getImageURL().equals("")) {
                FirebaseStorage storage = DAOOwner.getFirebaseStorage();
                StorageReference storageReference = storage.getReference();
                StorageReference ref = storageReference.child(item.getImageURL());
                ref.delete();
              }
            }
            dismiss();
          }
        });
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_addfood, container, false);
    view.setBackgroundResource(R.drawable.rounded_backgound);
    return view;
  }

  private Uri filePath;
  private final int PICK_IMAGE_REQUEST = 71;
  private final int RESULT_OK = -1;
  FirebaseStorage storage = DAOOwner.getFirebaseStorage();
  StorageReference storageReference = storage.getReference();
  // StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
  StorageReference ref = storageReference;

  private void openFileChooser() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == PICK_IMAGE_REQUEST
        && resultCode == RESULT_OK
        && data != null
        && data.getData() != null) {
      filePath = data.getData();
      uploadImage();
      //            try {
      //              Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),
      // filePath);
      //              imageView.setImageBitmap(bitmap);
      //            } catch (IOException e) {
      //              e.printStackTrace();
      //            }
    }
  }

  public void uploadImage() {
    if (filePath != null) {

      // Code for showing progressDialog while uploading
      ProgressDialog progressDialog = new ProgressDialog(getContext());
      progressDialog.setTitle("Uploading...");
      progressDialog.show();

      imageURL = UUID.randomUUID().toString();
      ref.child(imageURL + ".jpeg")
          .putFile(filePath)
          .addOnSuccessListener(
              new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                  // Image uploaded successfully, dismiss dialog
                  progressDialog.dismiss();
                  Toast.makeText(getContext(), "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                }
              })
          .addOnFailureListener(
              new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                  // Error, Image not uploaded
                  progressDialog.dismiss();
                  Toast.makeText(getContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT)
                      .show();
                }
              })
          .addOnProgressListener(
              new OnProgressListener<UploadTask.TaskSnapshot>() {
                // Progress Listener for loading percentage on the dialog box
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                  double progress =
                      (100.0
                          * taskSnapshot.getBytesTransferred()
                          / taskSnapshot.getTotalByteCount());
                  progressDialog.setMessage("Uploaded " + (int) progress + "%");
                }
              });
    }
  }
}
