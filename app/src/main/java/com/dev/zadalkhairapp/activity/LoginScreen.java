package com.dev.zadalkhairapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.dev.zadalkhairapp.R;
import com.dev.zadalkhairapp.association.AssociationMainActivity;
import com.dev.zadalkhairapp.consumer.MainActivity;
import com.dev.zadalkhairapp.restaurant.RestaurantMainActivity;
import com.google.android.material.textfield.TextInputEditText;


public class LoginScreen extends AppCompatActivity {
    TextInputEditText etEmail, etPassword;
    AppCompatTextView  tvForgotPassword, tvSingUp;
    AppCompatButton btnLogin;

    Intent intent;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        findViewById();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = getIntent();
                type = intent.getStringExtra(TypeUserScreen.Name_INTENT_TYPE_USER).trim();

                switch (type) {
                    case TypeUserScreen.VALUE_INTENT_TYPE_USER_ASSOCIATION:
                        startActivity(new Intent(LoginScreen.this, AssociationMainActivity.class).putExtra(TypeUserScreen.Name_INTENT_TYPE_USER, TypeUserScreen.VALUE_INTENT_TYPE_USER_ASSOCIATION));
                        Toast.makeText(LoginScreen.this, getResources().getString(R.string.association), Toast.LENGTH_SHORT).show();
                        break;
                    case TypeUserScreen.VALUE_INTENT_TYPE_USER_CONSUMER:
                        startActivity(new Intent(LoginScreen.this, MainActivity.class).putExtra(TypeUserScreen.Name_INTENT_TYPE_USER, TypeUserScreen.VALUE_INTENT_TYPE_USER_CONSUMER));
                        Toast.makeText(LoginScreen.this, getResources().getString(R.string.consumer), Toast.LENGTH_SHORT).show();
                        break;
                    case TypeUserScreen.VALUE_INTENT_TYPE_USER_RESTAURANT:
                        startActivity(new Intent(LoginScreen.this, RestaurantMainActivity.class).putExtra(TypeUserScreen.Name_INTENT_TYPE_USER, TypeUserScreen.VALUE_INTENT_TYPE_USER_RESTAURANT));
                        Toast.makeText(LoginScreen.this, getResources().getString(R.string.restaurant), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        
                }
                finish();
            }
        });

        tvSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this,SignupScreen.class));
                finish();
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this,ForgotPasswordScreen.class));
                finish();
            }
        });
    }
    public void findViewById() {
        etEmail = findViewById(R.id.loginEtEmail);
        etPassword = findViewById(R.id.loginEtPassword);
        tvForgotPassword = findViewById(R.id.loginTVForgotPassword);
        tvSingUp = findViewById(R.id.loginTVSingUp);
        btnLogin = findViewById(R.id.loginButton);

    }
}