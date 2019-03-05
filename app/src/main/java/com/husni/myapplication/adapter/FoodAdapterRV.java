package com.husni.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.husni.myapplication.R;
import com.husni.myapplication.model.Food;

import java.util.List;

public class FoodAdapterRV extends RecyclerView.Adapter<FoodAdapterRV.MyHolder>  {

    private List<Food> foodList;
    private OnItemClickImg clickImg;

    public FoodAdapterRV(List<Food> foodList, OnItemClickImg clickImg) {
        this.foodList = foodList;
        this.clickImg = clickImg;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {

        myHolder.textName.setText(foodList.get(i).getFood_name());
        myHolder.textDesc.setText(foodList.get(i).getFood_desc());
        myHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImg.Item(foodList.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size() ;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView textName, textDesc;
        ImageView imgDelete;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgDelete = itemView.findViewById(R.id.itemDelete);
            textName = itemView.findViewById(R.id.itemName);
            textDesc = itemView.findViewById(R.id.itemDesc);
        }
    }

    public interface OnItemClickImg {
        public void Item(Food food);
    }
}
