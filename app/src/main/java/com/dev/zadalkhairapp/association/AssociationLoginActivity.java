package com.dev.zadalkhairapp.association;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.dev.zadalkhairapp.R;
import com.dev.zadalkhairapp.ReusableCodeForAll;
import com.dev.zadalkhairapp.activity.ForgotPasswordScreen;
import com.dev.zadalkhairapp.activity.LoginScreen;
import com.dev.zadalkhairapp.activity.SignupScreen;
import com.dev.zadalkhairapp.activity.TypeUserScreen;
import com.dev.zadalkhairapp.consumer.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AssociationLoginActivity extends AppCompatActivity {
    TextInputEditText association_etEmail, association_etPassword;
    AppCompatTextView association_tvForgotPassword, association_tvSingUp;
    AppCompatButton association_btnLogin;
    String email , password;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    boolean isValidateData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_association_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        findViewById();

        databaseReference = firebaseDatabase.getInstance().getReference(AssociationSignupActivity.PATH_ASSOCIATION);
        firebaseAuth = FirebaseAuth.getInstance();

        association_btnLogin.setOnClickListener(v-> associationLoginAccount());


        association_tvSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AssociationLoginActivity.this, AssociationSignupActivity.class));
                finish();
            }
        });

        association_tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AssociationLoginActivity.this, ForgotPasswordScreen.class).putExtra( TypeUserScreen.Name_INTENT_TYPE_USER, TypeUserScreen.VALUE_INTENT_TYPE_USER_ASSOCIATION));
                finish();
            }
        });
    }
    public void findViewById() {
        association_etEmail = findViewById(R.id.association_loginEtEmail);
        association_etPassword = findViewById(R.id.association_loginEtPassword);
        association_tvForgotPassword = findViewById(R.id.association_loginTVForgotPassword);
        association_tvSingUp = findViewById(R.id.association_loginTVSingUp);
        association_btnLogin = findViewById(R.id.association_loginButton);
    }

    boolean validateData(String email ,String password){
        boolean isValid=false, isValid_email=false, isValid_password=false;

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || TextUtils.isEmpty(email)) {
            ReusableCodeForAll.giveMassageError(association_etEmail,getResources().getString(R.string.Enter_valid_email));
        }else {
            isValid_email= true;
        }
        if (TextUtils.isEmpty(password)|| password.length()< 7) {
            ReusableCodeForAll.giveMassageError(association_etPassword,getResources().getString(R.string.Enter_valid_password));
        }else {
            isValid_password = true;
        }
        isValid = ( isValid_email && isValid_password ) ? true : false;
        return isValid;
    }
    void associationLoginAccount(){
        email = association_etEmail.getText().toString();
        password = association_etPassword.getText().toString();
        isValidateData = validateData(email,password);

        if (isValidateData) {
            final ProgressDialog progressDialog = new ProgressDialog(AssociationLoginActivity.this);
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
                                Toast.makeText(AssociationLoginActivity.this, getResources().getString(R.string.Login_successes), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AssociationLoginActivity.this, AssociationMainActivity.class));
                                finish();
                            } catch (Exception ee) {
                                Toast.makeText(AssociationLoginActivity.this,ee.getMessage() +"try catch err ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(AssociationLoginActivity.this, getResources().getString(R.string.error_login) + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(AssociationLoginActivity.this, getResources().getString(R.string.error_login) + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}