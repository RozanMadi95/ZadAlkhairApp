package com.dev.zadalkhairapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dev.zadalkhairapp.R;
import com.dev.zadalkhairapp.ReusableCodeForAll;
import com.dev.zadalkhairapp.association.AssociationLoginActivity;
import com.dev.zadalkhairapp.consumer.MainActivity;
import com.dev.zadalkhairapp.restaurant.RestaurantLoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ForgotPasswordScreen extends AppCompatActivity {
    TextInputEditText etEmail;
    AppCompatButton btnReset;
    LinearLayoutCompat linerLogin;
    String email;
    FirebaseAuth firebaseAuth;
    boolean isValidateData;
    String type;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_screen);
        setTitle(R.string.ForgetPassword); //  اضافة عنوان للشاشة
        ActionBar actionBar = getSupportActionBar(); //هات الاكشن بار الرئيسي المدعم من اندرويد واعمل منه اوبجكت
        if (actionBar != null) { // اذا مش فاضي
            actionBar.setHomeButtonEnabled(true); // فعل الكشن بار الرئيسي
            actionBar.setDisplayHomeAsUpEnabled(true); // اطبع عليه الايقونات اظهرهم يعني
        }

        findViewById();
        firebaseAuth = FirebaseAuth.getInstance();
        linerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(ForgotPasswordScreen.this, LoginScreen.class));
                intent = getIntent();
                type = intent.getStringExtra(TypeUserScreen.Name_INTENT_TYPE_USER).trim();

                switch (type) {
                    case TypeUserScreen.VALUE_INTENT_TYPE_USER_ASSOCIATION:
                        startActivity(new Intent(ForgotPasswordScreen.this, AssociationLoginActivity.class).putExtra(TypeUserScreen.Name_INTENT_TYPE_USER, TypeUserScreen.VALUE_INTENT_TYPE_USER_ASSOCIATION));
                        Toast.makeText(ForgotPasswordScreen.this, getResources().getString(R.string.association), Toast.LENGTH_SHORT).show();
                        break;
                    case TypeUserScreen.VALUE_INTENT_TYPE_USER_CONSUMER:
                        startActivity(new Intent(ForgotPasswordScreen.this, LoginScreen.class).putExtra(TypeUserScreen.Name_INTENT_TYPE_USER, TypeUserScreen.VALUE_INTENT_TYPE_USER_CONSUMER));
                        Toast.makeText(ForgotPasswordScreen.this, getResources().getString(R.string.consumer), Toast.LENGTH_SHORT).show();
                        break;
                    case TypeUserScreen.VALUE_INTENT_TYPE_USER_RESTAURANT:
                        startActivity(new Intent(ForgotPasswordScreen.this, RestaurantLoginActivity.class).putExtra(TypeUserScreen.Name_INTENT_TYPE_USER, TypeUserScreen.VALUE_INTENT_TYPE_USER_RESTAURANT));
                        Toast.makeText(ForgotPasswordScreen.this, getResources().getString(R.string.restaurant), Toast.LENGTH_SHORT).show();
                        break;
                }
                finish();
            }
        });
        btnReset.setOnClickListener(v-> resetPasswordAccount());

    }
    public void findViewById() {
        etEmail = findViewById(R.id.forgetEtEmail);
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

    boolean validateData(String email){
        boolean isValid=false, isValid_email=false;

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || TextUtils.isEmpty(email)) {
            ReusableCodeForAll.giveMassageError(etEmail,getResources().getString(R.string.Enter_valid_email));
        }else {
            isValid_email= true;
        }
        isValid = ( isValid_email) ? true : false;
        return isValid;
    }
    void resetPasswordAccount(){
        email = etEmail.getText().toString();
        isValidateData = validateData(email);

        if (isValidateData) {
            final ProgressDialog loadingBar=new ProgressDialog(ForgotPasswordScreen.this);
            loadingBar.setMessage("Sending Email....");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    loadingBar.dismiss();
                    if(task.isSuccessful())
                    {
                        // if isSuccessful then done message will be shown
                        // and you can change the password
                        Toast.makeText(ForgotPasswordScreen.this,"Done sent",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(ForgotPasswordScreen.this,"Error Occurred",Toast.LENGTH_LONG).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    loadingBar.dismiss();
                    Toast.makeText(ForgotPasswordScreen.this,"Error Failed",Toast.LENGTH_LONG).show();
                }
            });
        }
        }
}
