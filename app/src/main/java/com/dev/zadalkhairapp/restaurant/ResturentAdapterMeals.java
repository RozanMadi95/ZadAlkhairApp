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

public class ResturentAdapterMeals extends RecyclerView.Adapter<ResturentAdapterMeals.ViewHolder> {

    private List<MealClass> MealArray;
    Context context;

    public ResturentAdapterMeals(List<MealClass> mealArray, Context context) {
        MealArray = mealArray;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_meals_restaurant,parent,false);



        return new ResturentAdapterMeals.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MealClass mealClass = MealArray.get(position);
        holder.dishesname.setText(mealClass.getMealname());


        Picasso.get().load(mealClass.getImgurl()).into(holder.imagedish);
    }

    @Override
    public int getItemCount() {
        return MealArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dishesname;
        ImageView imagedish;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
       dishesname=itemView.findViewById(R
               .id.restaurant_image);
        imagedish=itemView.findViewById(R.id.restaurant_name);

        }
    }

}
