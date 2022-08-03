package com.dev.zadalkhairapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dev.zadalkhairapp.R;

public class TypeUserScreen extends AppCompatActivity {
    public static final String Name_INTENT_TYPE_USER = "TypeUser";
    public static final String VALUE_INTENT_TYPE_USER_ASSOCIATION = "Association";
    public static final String VALUE_INTENT_TYPE_USER_CONSUMER = "Consumer";
    public static final String VALUE_INTENT_TYPE_USER_RESTAURANT = "Restaurant";
    CardView cvAssociation, cvConsumer, cvRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_user_screen);
        findViewById();

        cvAssociation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TypeUserScreen.this, SignupScreen.class).putExtra(Name_INTENT_TYPE_USER,VALUE_INTENT_TYPE_USER_ASSOCIATION));
                finish();
            }
        });

        cvConsumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TypeUserScreen.this, SignupScreen.class).putExtra(Name_INTENT_TYPE_USER,VALUE_INTENT_TYPE_USER_CONSUMER));
                finish();
            }
        });

        cvRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TypeUserScreen.this, SignupScreen.class).putExtra(Name_INTENT_TYPE_USER,VALUE_INTENT_TYPE_USER_RESTAURANT));
                finish();
            }
        });
    }

    public void findViewById() {
        cvAssociation = findViewById(R.id.associationCardView);
        cvConsumer = findViewById(R.id.consumerCardView);
        cvRestaurant = findViewById(R.id.restaurantCardView);
    }
}