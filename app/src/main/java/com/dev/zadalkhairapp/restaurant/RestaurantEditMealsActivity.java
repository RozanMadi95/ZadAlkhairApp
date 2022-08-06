package com.dev.zadalkhairapp.restaurant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dev.zadalkhairapp.R;

public class RestaurantEditMealsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_edit_meals);
        setTitle(R.string.restaurantEditMeals); //  اضافة عنوان للشاشة
        ActionBar actionBar = getSupportActionBar(); //هات الاكشن بار الرئيسي المدعم من اندرويد واعمل منه اوبجكت
        if (actionBar != null) { // اذا مش فاضي
            actionBar.setHomeButtonEnabled(true); // فعل الكشن بار الرئيسي
            actionBar.setDisplayHomeAsUpEnabled(true); // اطبع عليه الايقونات اظهرهم يعني
        }
    }
}