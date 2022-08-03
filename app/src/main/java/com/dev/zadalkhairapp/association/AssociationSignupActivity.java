package com.dev.zadalkhairapp.association;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import com.dev.zadalkhairapp.R;
import com.dev.zadalkhairapp.ReusableCodeForAll;
import com.dev.zadalkhairapp.RoleClass;
import com.dev.zadalkhairapp.activity.LoginScreen;
import com.dev.zadalkhairapp.activity.SignupScreen;
import com.dev.zadalkhairapp.consumer.Consumer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.Objects;

public class AssociationSignupActivity extends AppCompatActivity {
    public static final String PATH_ASSOCIATION ="Association";
    TextInputEditText etPhone, etName, etEmail, etAddress, etPassword;
    AppCompatTextView tvSingIn;
    AppCompatButton btnSignUp;
    AppCompatCheckBox checkBoxAgree;
    CountryCodePicker codePicker;
    Intent intent;
    String type;
    String role = "association";
    String name ,email,address,password,phone,intro_number,phoneAll;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;

    boolean isValidateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_association_signup);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        findViewById();

        databaseReference = firebaseDatabase.getInstance().getReference(PATH_ASSOCIATION);
        firebaseAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(v-> signUpAccount());
        tvSingIn.setOnClickListener(view -> {
            startActivity(new Intent(AssociationSignupActivity.this, AssociationLoginActivity.class));
            finish();
        });
    }

    public void findViewById() {
        etName = findViewById(R.id.association_sign_upEtName);
        etPhone = findViewById(R.id.association_sign_upEtPhone);
        etEmail = findViewById(R.id.association_sign_upEtEmail);
        etAddress = findViewById(R.id.association_sign_upEtAddress);
        etPassword = findViewById(R.id.association_sign_upEtPassword);
        checkBoxAgree = findViewById(R.id.association_sign_up_CheckBox);
        btnSignUp = findViewById(R.id.association_sign_upButton);
        tvSingIn = findViewById(R.id.association_sign_up_login);
        codePicker = findViewById(R.id.association_code_picker);
    }

    void signUpAccount(){
        name = Objects.requireNonNull(etName.getText()).toString().trim();
        email = Objects.requireNonNull(etEmail.getText()).toString().trim();
        address = Objects.requireNonNull(etAddress.getText()).toString().trim();
        password = Objects.requireNonNull(etPassword.getText()).toString().trim();
        phone = Objects.requireNonNull(etPhone.getText()).toString().trim();
        intro_number = codePicker.getSelectedCountryCodeWithPlus();
        phoneAll = "" + intro_number + phone;



        isValidateData = validateData(name,phoneAll,email ,address ,password);

        if (isValidateData) {
            final ProgressDialog progressDialog = new ProgressDialog(AssociationSignupActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setTitle(R.string.register);
            progressDialog.setMessage(getResources().getString(R.string.presses_register));
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        databaseReference = FirebaseDatabase.getInstance().getReference("User").child(userid);
                        RoleClass roleClass = new RoleClass(role);
                        databaseReference.setValue(roleClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Association associationObj = new Association(name, email, address, password, phoneAll);

                                firebaseDatabase.getInstance().getReference(PATH_ASSOCIATION).child(FirebaseAuth.getInstance()
                                        .getCurrentUser().getUid()).setValue(associationObj).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressDialog.dismiss();
                                        firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(AssociationSignupActivity.this);
                                                    builder.setMessage(getResources().getString(R.string.checkEmail));
                                                    builder.setTitle(R.string.VerificationMsg);
                                                    builder.setCancelable(false);
                                                    builder.setPositiveButton(R.string.positiv_btn, new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            startActivity(new Intent(AssociationSignupActivity.this, LoginScreen.class));
                                                            Toast.makeText(AssociationSignupActivity.this, getResources().getString(R.string.association), Toast.LENGTH_SHORT).show();
                                                            dialog.dismiss();
                                                        }
                                                    });
                                                    AlertDialog alert = builder.create();
                                                    alert.show();

                                                } else {
                                                    progressDialog.dismiss();
                                                    Toast.makeText(AssociationSignupActivity.this, getResources().getString(R.string.errorSignUp) + task.getException(), Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });
                                    }

                                });
                            }
                        });

                    }}});
        }else{
            Toast.makeText(this, R.string.errorSignUp, Toast.LENGTH_SHORT).show();

        }

    }




    boolean validateData(String name, String phoneAll , String email , String address , String password){
        boolean isValid=false,
                isValid_name=false,
                isValid_phone=false,
                isValid_email=false,
                isValid_address=false,
                isValid_password=false,
                isValid_checkBoxAgree= false;

        if (TextUtils.isEmpty(name)) {
            ReusableCodeForAll.giveMassageError(etName,getResources().getString(R.string.Enter_Your_Name));
        }else {
            isValid_name = true;
        }
        if (TextUtils.isEmpty(phone) || phone.length()> 15) {
            ReusableCodeForAll.giveMassageError(etPhone,getResources().getString(R.string.Enter_your_phone_number));
        }else {
            isValid_phone = true;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || TextUtils.isEmpty(email)) {
            ReusableCodeForAll.giveMassageError(etEmail,getResources().getString(R.string.Enter_valid_email));
        }else {
            isValid_email= true;
        }
        if (TextUtils.isEmpty(address)) {
            ReusableCodeForAll.giveMassageError(etAddress,getResources().getString(R.string.Enter_your_address_Enter_valid));
        }else {
            isValid_address = true;
        }
        if (TextUtils.isEmpty(password)|| password.length()< 7) {
            ReusableCodeForAll.giveMassageError(etPassword,getResources().getString(R.string.Enter_valid_password));
        }else {
            isValid_password = true;
        }

        if (checkBoxAgree.isChecked()) {
            isValid_checkBoxAgree = true;
        }else {
            checkBoxAgree.setError("");

        }
        isValid = (isValid_name && isValid_phone && isValid_email  && isValid_address && isValid_password &&isValid_checkBoxAgree) ? true : false;
        return isValid;
    }
}

