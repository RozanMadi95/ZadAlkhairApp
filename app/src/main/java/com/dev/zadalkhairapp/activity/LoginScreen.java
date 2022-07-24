package com.dev.zadalkhairapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.dev.zadalkhairapp.R;
import com.dev.zadalkhairapp.consumer.MainActivity;
import com.google.android.material.textfield.TextInputEditText;


public class LoginScreen extends AppCompatActivity {
    TextInputEditText etPhone, etPassword;
    AppCompatTextView  tvForgotPassword, tvSingUp;
    AppCompatButton btnLogin;
    AppCompatImageButton butImgLoginPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        findViewById();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this, MainActivity.class));
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
        etPhone = findViewById(R.id.loginEtPhone);
        etPassword = findViewById(R.id.loginEtPassword);
        tvForgotPassword = findViewById(R.id.loginTVForgotPassword);
        tvSingUp = findViewById(R.id.loginTVSingUp);
        btnLogin = findViewById(R.id.loginButton);
        butImgLoginPhone = findViewById(R.id.loginButtonPhone);
    }
}