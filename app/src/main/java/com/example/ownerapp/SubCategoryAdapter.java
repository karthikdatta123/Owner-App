package com.example.ownerapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {
    private List<SubCategory> subCategoryList;
    private Context context;
    private static int currentPosition = 0;

    RecyclerView recyclerView1;
    RowAdapter rowAdapter;

    public SubCategoryAdapter(List<SubCategory> subCategoryList, Context context) {
        this.subCategoryList = subCategoryList;
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
        holder.textView3.setText(subCategory.getName());

        boolean isExpanded= subCategory.getExpanded();
        holder.expandableLayout.setVisibility(isExpanded? View.VISIBLE : View.GONE);

        //populate recyclerView with row_item.xml UI
        rowAdapter = new RowAdapter(subCategory.getItems(), context.getApplicationContext());
        holder.recyclerView1.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
        holder.recyclerView1.setHasFixedSize(true);
        holder.recyclerView1.setAdapter(rowAdapter);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView3=itemView.findViewById(R.id.textView3);
            expandableLayout=itemView.findViewById(R.id.expandableLayout);
            recyclerView1=itemView.findViewById(R.id.recyclerView1);
            expandable_icon=itemView.findViewById(R.id.expandable_icon);
            expandable_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SubCategory subCategory = subCategoryList.get(getAdapterPosition());
                    boolean isExpanded= subCategory.getExpanded();
                    if(isExpanded)
                        expandable_icon.setImageResource(R.drawable.chevron_up_arrow);
                    else  expandable_icon.setImageResource(R.drawable.chevron_down_arrow);
                    subCategory.setExpanded(!isExpanded);
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

}
