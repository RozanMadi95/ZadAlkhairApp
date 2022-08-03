package com.dev.zadalkhairapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.dev.zadalkhairapp.MainActivity;
import com.dev.zadalkhairapp.R;
import com.dev.zadalkhairapp.ReusableCodeForAll;

public class ForgotPasswordScreen extends AppCompatActivity {
    AppCompatEditText etPhone;
    AppCompatButton btnReset;
    LinearLayoutCompat linerLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_screen);
        setTitle(R.string.ForgetPassword); //  اضافة عنوان للشاشة
        ActionBar actionBar = getSupportActionBar(); //هات الاكشن بار الرئيسي المدعم من اندرويد واعمل منه اوبجكت
        if(actionBar != null){ // اذا مش فاضي
            actionBar.setHomeButtonEnabled(true); // فعل الكشن بار الرئيسي
            actionBar.setDisplayHomeAsUpEnabled(true); // اطبع عليه الايقونات اظهرهم يعني
        }

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}