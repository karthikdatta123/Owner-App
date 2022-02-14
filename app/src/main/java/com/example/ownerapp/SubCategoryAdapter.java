package com.example.ownerapp;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {
    private List<SubCategory> subCategoryList;
    private String categoryName;

    private Context context;
    private static int currentPosition = 0;

    RecyclerView recyclerView1;
    RowAdapter rowAdapter;

    public SubCategoryAdapter(List<SubCategory> subCategoryList,String categoryName, Context context) {
        this.subCategoryList = subCategoryList;
        this.categoryName=categoryName;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.sub_categories_list, parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubCategory subCategory = subCategoryList.get(position);

        //textView3 will be (sub)subCategory name -
        holder.textView3.setText(subCategory.getSubCategoryName());

        boolean isExpanded= subCategory.isExpanded();
        holder.expandableLayout.setVisibility(isExpanded? View.VISIBLE : View.GONE);

        //populate recyclerView with row_item.xml UI
        rowAdapter = new RowAdapter(categoryName, subCategory, context.getApplicationContext());
        holder.recyclerView1.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
        holder.recyclerView1.setHasFixedSize(true);
        holder.recyclerView1.setAdapter(rowAdapter);

        if (isExpanded) {
            holder.expandable_icon.setImageResource(R.drawable.chevron_up_arrow);
            holder.divider.setVisibility(View.GONE);

        } else{
            holder.expandable_icon.setImageResource(R.drawable.chevron_down_arrow);
            holder.divider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView3;
        ConstraintLayout expandableLayout;
        RecyclerView recyclerView1;
        ImageView expandable_icon;
        Button button;
        View divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView3=itemView.findViewById(R.id.textView3);
            expandableLayout=itemView.findViewById(R.id.expandableLayout);
            recyclerView1=itemView.findViewById(R.id.recyclerView1);
            expandable_icon=itemView.findViewById(R.id.expandable_icon);
            button=itemView.findViewById(R.id.button);
            divider=itemView.findViewById(R.id.divider2);

      expandable_icon.setOnClickListener(
          new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              SubCategory subCategory = subCategoryList.get(getAdapterPosition());
              boolean isExpanded = subCategory.isExpanded();
              if (isExpanded) {
                expandable_icon.setImageResource(R.drawable.chevron_up_arrow);
                divider.setVisibility(View.GONE);

              } else{
                  expandable_icon.setImageResource(R.drawable.chevron_down_arrow);
                  divider.setVisibility(View.VISIBLE);
              }
              subCategory.setExpanded(!isExpanded);
              notifyItemChanged(getAdapterPosition());
            }
          });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SubCategory subCategory = subCategoryList.get(getAdapterPosition());

                    if(categoryName.equals("Food")) {
                        AddItemFood addItemFood = new AddItemFood();
                        Bundle bundle = new Bundle();
                        bundle.putString("subCategoryName", subCategory.getSubCategoryName());
                        addItemFood.setArguments(bundle);
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        FragmentManager manager = activity.getSupportFragmentManager();
                        addItemFood.show(manager, addItemFood.getTag());
                    }
                    else if(categoryName.equals("Laundry"))
                    {
                        AddItemLaundry addItemLaundry=new AddItemLaundry();
                        Bundle bundle = new Bundle();
                        bundle.putString("subCategoryName", subCategory.getSubCategoryName());
                        addItemLaundry.setArguments(bundle);
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        FragmentManager manager = activity.getSupportFragmentManager();
                        addItemLaundry.show(manager,addItemLaundry.getTag());
                    }
                    else if(subCategory.getSubCategoryName().equals("Rentals"))
                    {
                        AddItemRental addItemRental=new AddItemRental();
                        Bundle bundle = new Bundle();
                        bundle.putString("subCategoryName", subCategory.getSubCategoryName());
                        addItemRental.setArguments(bundle);
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        FragmentManager manager = activity.getSupportFragmentManager();
                        addItemRental.show(manager,addItemRental.getTag());
                    }
                    else if(subCategory.getSubCategoryName().equals("Tourist Guide"))
                    {
                        AddItemLocalGuide addItemRental=new AddItemLocalGuide();
                        Bundle bundle = new Bundle();
                        bundle.putString("subCategoryName", subCategory.getSubCategoryName());
                        addItemRental.setArguments(bundle);
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        FragmentManager manager = activity.getSupportFragmentManager();
                        addItemRental.show(manager,addItemRental.getTag());
                    }
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

}
