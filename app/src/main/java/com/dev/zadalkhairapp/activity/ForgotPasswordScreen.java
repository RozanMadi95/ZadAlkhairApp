package com.dev.zadalkhairapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dev.zadalkhairapp.MainActivity;
import com.dev.zadalkhairapp.R;

public class ForgotPasswordScreen extends AppCompatActivity {
    AppCompatEditText etPhone;
    AppCompatButton btnReset;
    LinearLayoutCompat linerLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_screen);

        findViewById();

        linerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordScreen.this, LoginScreen.class));
                finish();
            }
        });
    }
    public void findViewById() {
        etPhone = findViewById(R.id.forgetEtPhone);
        btnReset = findViewById(R.id.forgetResetButton);
        linerLogin = findViewById(R.id.forgetLinearLayout);
    }
}