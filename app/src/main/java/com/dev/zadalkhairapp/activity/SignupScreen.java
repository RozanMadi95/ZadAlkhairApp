package com.dev.zadalkhairapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import com.dev.zadalkhairapp.R;
import com.dev.zadalkhairapp.ReusableCodeForAll;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;


public class SignupScreen extends AppCompatActivity {
    TextInputEditText etPhone, etName, etEmail, etAddress, etPassword;
    AppCompatTextView tvSingIn;
    AppCompatButton btnSignUp;
    AppCompatCheckBox checkBoxAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        findViewById();
        //checkBoxAgree.setOnClickListener(this);

        if (checkBoxAgree.isChecked()) {
            Toast.makeText(this, getResources().getString(R.string.aggree), Toast.LENGTH_SHORT).show();
        }

        btnSignUp.setOnClickListener(v-> signUpAccount());
        tvSingIn.setOnClickListener(view -> {
            startActivity(new Intent(SignupScreen.this,LoginScreen.class));
            finish();
        });
    }

    public void findViewById() {
        etName = findViewById(R.id.sign_upEtName);
        etPhone = findViewById(R.id.sign_upEtPhone);
        etEmail = findViewById(R.id.sign_upEtEmail);
        etAddress = findViewById(R.id.sign_upEtAddress);
        etPassword = findViewById(R.id.sign_upEtPassword);
        checkBoxAgree = findViewById(R.id.sign_up_CheckBox);
        btnSignUp = findViewById(R.id.sign_upButton);
        tvSingIn = findViewById(R.id.sign_up_login);
    }

    void signUpAccount(){
         String name = Objects.requireNonNull(etName.getText()).toString().trim();
         String phone = Objects.requireNonNull(etPhone.getText()).toString().trim();
         String email = Objects.requireNonNull(etEmail.getText()).toString().trim();
         String address = Objects.requireNonNull(etAddress.getText()).toString().trim();
         String password = Objects.requireNonNull(etPassword.getText()).toString().trim();

        validateData(name,phone,email ,address ,password);


    }




    boolean validateData(String name, String phone , String email , String address , String password){
        if (TextUtils.isEmpty(name)) {
            ReusableCodeForAll.giveMassageError(etName,getResources().getString(R.string.Enter_Your_Name));
        }
        if (TextUtils.isEmpty(phone) || password.length()> 10) {
            ReusableCodeForAll.giveMassageError(etPhone,getResources().getString(R.string.Enter_your_phone_number));
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || TextUtils.isEmpty(email)) {
            ReusableCodeForAll.giveMassageError(etEmail,getResources().getString(R.string.Enter_valid_email));

        }
        if (TextUtils.isEmpty(address)) {
            ReusableCodeForAll.giveMassageError(etAddress,getResources().getString(R.string.Enter_your_address_Enter_valid));
        }
        if (TextUtils.isEmpty(password)|| password.length()< 7) {
            ReusableCodeForAll.giveMassageError(etPassword,getResources().getString(R.string.Enter_valid_password));
        }
        return true;
    }
}

