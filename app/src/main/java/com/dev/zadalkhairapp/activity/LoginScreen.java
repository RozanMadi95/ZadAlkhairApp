package com.dev.zadalkhairapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.dev.zadalkhairapp.R;
import com.dev.zadalkhairapp.ReusableCodeForAll;
import com.dev.zadalkhairapp.association.AssociationMainActivity;
import com.dev.zadalkhairapp.consumer.MainActivity;
import com.dev.zadalkhairapp.restaurant.RestaurantMainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class LoginScreen extends AppCompatActivity {
    TextInputEditText etEmail, etPassword;
    AppCompatTextView  tvForgotPassword, tvSingUp;
    AppCompatButton btnLogin;
    String email , password;
//    Intent intent;
//    String type;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    boolean isValidateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        findViewById();

        databaseReference = firebaseDatabase.getInstance().getReference("Consumer");
        firebaseAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(v-> LoginAccount());


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

    boolean validateData(String email ,String password){
        boolean isValid=false, isValid_email=false, isValid_password=false;

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || TextUtils.isEmpty(email)) {
            ReusableCodeForAll.giveMassageError(etEmail,getResources().getString(R.string.Enter_valid_email));
        }else {
            isValid_email= true;
        }
        if (TextUtils.isEmpty(password)|| password.length()< 7) {
            ReusableCodeForAll.giveMassageError(etPassword,getResources().getString(R.string.Enter_valid_password));
        }else {
            isValid_password = true;
        }
        isValid = ( isValid_email && isValid_password ) ? true : false;
        return isValid;
    }
    void LoginAccount(){
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        isValidateData = validateData(email,password);

        if (isValidateData) {
            final ProgressDialog progressDialog = new ProgressDialog(LoginScreen.this);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setTitle("Login ");
            progressDialog.setMessage("Login whith email ... ");
            progressDialog.show();
            Log.e("er2", "ee");
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.e("er3", "ee");

                    if (task.isSuccessful()) {
                        if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                            progressDialog.dismiss();
                            Log.e("er4", "ee");
                            try {
                                Toast.makeText(LoginScreen.this, "Login done ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginScreen.this, MainActivity.class));
                            } catch (Exception ee) {
                                Toast.makeText(LoginScreen.this,ee.getMessage() +"try catch err ", Toast.LENGTH_SHORT).show();
                            }
                            Log.e("er5", "ee");
                        } else {
                            Toast.makeText(LoginScreen.this, "error in login " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDialog.dismiss();

                        Toast.makeText(LoginScreen.this, "error in login " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}