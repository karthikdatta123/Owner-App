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

public class AddSubCategory extends BottomSheetDialogFragment {
    private String categoryName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            categoryName=this.getArguments().getString("categoryName");
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
        ImageView delete = view.findViewById(R.id.imageView1);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Changes Applied",Toast.LENGTH_SHORT).show();
                String name=textInputEditText1.getText().toString();

                SubCategory subCategory1=new SubCategory(name,null);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragement_addsubcategory, container, false);
        view.setBackgroundResource(R.drawable.rounded_backgound);
        return view;
    }

}
