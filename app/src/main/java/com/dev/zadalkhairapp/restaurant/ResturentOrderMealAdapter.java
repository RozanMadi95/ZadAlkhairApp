package com.dev.zadalkhairapp.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.zadalkhairapp.R;
import com.squareup.picasso.Picasso;


import java.util.List;

public class ResturentOrderMealAdapter extends  RecyclerView.Adapter<ResturentOrderMealAdapter.ViewHolder> {
    private List<MealClass> MealArray;
    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_order_meals,parent,false);


        return new ResturentOrderMealAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final MealClass mealClass = MealArray.get(position);
        holder.dishesname.setText(mealClass.getMealname());
        holder.dishesname.setText(mealClass.getMealname());

        Picasso.get().load(mealClass.getImgurl()).into(holder.imagedish);
    }

    @Override
    public int getItemCount() {
        return MealArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView dishesname;
        TextView username;
        ImageView imagedish;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishesname=itemView.findViewById(R.id.order_meals_name);
            username=itemView.findViewById(R.id.order_meals_user_name);
            imagedish=itemView.findViewById(R.id.order_meals_img);

        }
    }
}
