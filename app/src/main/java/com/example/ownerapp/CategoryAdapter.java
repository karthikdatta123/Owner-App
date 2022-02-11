package com.example.ownerapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> categoryList;
    private static Context context;
    SubCategoryAdapter subCategoryAdapter;

    public CategoryAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }
    public static Context getContext()
    {return context;}

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.categories_list, parent, false);
        CategoryAdapter.ViewHolder viewHolder=new CategoryAdapter.ViewHolder(view);
        return viewHolder;//manages the rows, keeps track of individual rows-all img views,text views etc
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Category category = categoryList.get(position);

        holder.textView.setText(category.getCategoryName());
        //populate recyclerView with list_layout_items.xml UI
        subCategoryAdapter = new SubCategoryAdapter(category.getCategoryList(),category.getCategoryName(), context.getApplicationContext());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
//       holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setAdapter(subCategoryAdapter);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {
        RecyclerView recyclerView;
        TextView textView;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
            button=itemView.findViewById(R.id.button);
            recyclerView=itemView.findViewById(R.id.recyclerView);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddSubCategory addSubCategory = new AddSubCategory();
                    Bundle bundle = new Bundle();
                    bundle.putString("categoryName", categoryList.get(getAdapterPosition()).getCategoryName());
                    addSubCategory.setArguments(bundle);
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    FragmentManager manager = activity.getSupportFragmentManager();
                    addSubCategory.show(manager, addSubCategory.getTag());
                }
            });
        }
    }
}
