package com.dev.zadalkhairapp.consumer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dev.zadalkhairapp.R;
import com.dev.zadalkhairapp.activity.ForgotPasswordScreen;

import java.util.ArrayList;

public class MealDetailsActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        //انفلات للعناصر
        toolbar = findViewById(R.id.meals_details_toolbar);
        //دعتمد التول بار الي صممته انه تول بار الاساسي الي حتتعامل معه
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_meals_details_toolbar,menu);
        return true;
    }

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
