package com.dev.zadalkhairapp.consumer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dev.zadalkhairapp.R;



public class MealDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        //انفلات للعناصر
        Toolbar toolbar = findViewById(R.id.meals_details_toolbar);
        //دعتمد التول بار الي صممته انه تول بار الاساسي الي حتتعامل معه
        setSupportActionBar(toolbar);


        AppCompatButton btnAddCart = findViewById(R.id.details_meals_Button_Add_to_cart);
        btnAddCart.setOnClickListener(view -> {
            //calling this method to show our android custom alert dialog
            showCustomDialog();

        });

    }
    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_cart, viewGroup, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);


                //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();

        alertDialog.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_meals_details_toolbar,menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_cart:
               // startActivity(new Intent(this, CartFragment.class));
                return true;
            case R.id.menu_phone:
                startActivity(new Intent(this, MainActivity.class));
                return true;
        }
        return false;
    }

}
