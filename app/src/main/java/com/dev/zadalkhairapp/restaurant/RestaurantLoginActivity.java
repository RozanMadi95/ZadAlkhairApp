package com.dev.zadalkhairapp.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.dev.zadalkhairapp.R;
import com.dev.zadalkhairapp.ReusableCodeForAll;
import com.dev.zadalkhairapp.activity.ForgotPasswordScreen;
import com.dev.zadalkhairapp.association.AssociationLoginActivity;
import com.dev.zadalkhairapp.association.AssociationMainActivity;
import com.dev.zadalkhairapp.association.AssociationSignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RestaurantLoginActivity extends AppCompatActivity {
    TextInputEditText restaurant_etEmail, restaurant_etPassword;
    AppCompatTextView restaurant_tvForgotPassword, restaurant_tvSingUp;
    AppCompatButton restaurant_btnLogin;
    String email , password;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    boolean isValidateData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        findViewById();

        databaseReference = firebaseDatabase.getInstance().getReference(RestaurantSignupActivity.PATH_RESTAURANT);
        firebaseAuth = FirebaseAuth.getInstance();

        restaurant_btnLogin.setOnClickListener(v-> restaurantLoginAccount());


        restaurant_tvSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RestaurantLoginActivity.this, RestaurantSignupActivity.class));
                finish();
            }
        });

        restaurant_etPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RestaurantLoginActivity.this, ForgotPasswordScreen.class));
                finish();
            }
        });
    }
    public void findViewById() {
        restaurant_etEmail = findViewById(R.id.restaurant_loginEtEmail);
        restaurant_etPassword = findViewById(R.id.restaurant_loginEtPassword);
        restaurant_tvForgotPassword = findViewById(R.id.restaurant_loginTVForgotPassword);
        restaurant_tvSingUp = findViewById(R.id.restaurant_loginTVSingUp);
        restaurant_btnLogin = findViewById(R.id.restaurant_loginButton);
    }

    boolean validateData(String email ,String password){
        boolean isValid=false, isValid_email=false, isValid_password=false;

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || TextUtils.isEmpty(email)) {
            ReusableCodeForAll.giveMassageError(restaurant_etEmail,getResources().getString(R.string.Enter_valid_email));
        }else {
            isValid_email= true;
        }
        if (TextUtils.isEmpty(password)|| password.length()< 7) {
            ReusableCodeForAll.giveMassageError(restaurant_etPassword,getResources().getString(R.string.Enter_valid_password));
        }else {
            isValid_password = true;
        }
        isValid = ( isValid_email && isValid_password ) ? true : false;
        return isValid;
    }
    void restaurantLoginAccount(){
        email = restaurant_etEmail.getText().toString();
        password = restaurant_etPassword.getText().toString();
        isValidateData = validateData(email,password);

        if (isValidateData) {
            final ProgressDialog progressDialog = new ProgressDialog(RestaurantLoginActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setTitle(R.string.login_here);
            progressDialog.setMessage(getResources().getString(R.string.logInWithEmail));
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                            progressDialog.dismiss();
                            try {
                                Toast.makeText(RestaurantLoginActivity.this, getResources().getString(R.string.Login_successes), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RestaurantLoginActivity.this, RestaurantMainActivity.class));
                                finish();
                            } catch (Exception ee) {
                                Toast.makeText(RestaurantLoginActivity.this,ee.getMessage() +"try catch err ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RestaurantLoginActivity.this, getResources().getString(R.string.error_login) + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(RestaurantLoginActivity.this, getResources().getString(R.string.error_login) + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}