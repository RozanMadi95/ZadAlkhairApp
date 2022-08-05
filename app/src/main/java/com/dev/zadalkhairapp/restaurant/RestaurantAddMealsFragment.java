package com.dev.zadalkhairapp.restaurant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dev.zadalkhairapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class RestaurantAddMealsFragment extends Fragment implements View.OnClickListener{
    FloatingActionButton addbtnImage;
    ImageView showimage;
    RadioGroup radgroup ;

//    addMeals_et_Name
    //addMeals_available_items
    AppCompatEditText mealsname ;
    EditText etavilableitem, etpricemeals,expirtdate,deliveroption;
    Uri imageUri;
    RadioButton radioButton1 ,radioButton2;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseAuth FAuth;
    String RandomUId,namedesh,imagename,pricena,optiondeliver,expirdate,avalibleitem,paymetoption;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String userid;
    AppCompatButton btnUploaddata;
            //addMeals_payment_options_Cash
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RestaurantAddMealsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RestaurantAddMealsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RestaurantAddMealsFragment newInstance(String param1, String param2) {
        RestaurantAddMealsFragment fragment = new RestaurantAddMealsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_add_meals, container, false);
        addbtnImage =view.findViewById(R.id.addMeals_btnImage);
        showimage=view.findViewById(R.id.addMeals_image_upload);
        mealsname=view.findViewById(R.id.addMeals_et_Name);
        etavilableitem=view.findViewById(R.id.addMeals_available_items);
        etpricemeals=view.findViewById(R.id.addMeals_price);
        radioButton1=view.findViewById(R.id.addMeals_payment_options_Cash);
        radioButton2=view.findViewById(R.id.radioFemale);
        expirtdate=view.findViewById(R.id.addMeals_expiry_date);
        deliveroption=view.findViewById(R.id.addMeals_delivery_options);
        btnUploaddata =view.findViewById(R.id.addMeals_btnAdd);
        storage = FirebaseStorage.getInstance();

        radgroup =view.findViewById(R.id.addMeals_radioGroup);
        FAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getInstance().getReference("mealsdetial");
        userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        radioButton1.setOnClickListener(this);
        radioButton2.setOnClickListener(this);



        //    String RandomUId,namedesh,imagename,pricena,optiondeliver,expirdate,avalibleitem,paymetoption;

//etavilableitem, etpricemeals,expirtdate,deliveroption
        addbtnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choessPicter();
            }
        });
        btnUploaddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    uploadImage(imageUri);
            }
        });

        return  view;
    }


    private void choessPicter() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        imageUri = data.getData();
        showimage.setImageURI(imageUri);



//        if (requestCode == 1 && resultCode == ResultOk && data != null && data.getData() != null) {
////            imageUri = data.getData();
////            imageupload.setImageURI(imageUri);
//            //  uploadImage(imageUri);
//        }
    }
    private void uploadImage(Uri imageUri1) {


        if (imageUri1 != null) {


            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            storageReference = storage.getReference().child(RandomUId);
            RandomUId = UUID.randomUUID().toString();
            storageReference.putFile(imageUri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
//        paymetoption

        namedesh=mealsname.getText().toString().trim();
        pricena =etpricemeals.getText().toString().trim();
        optiondeliver =deliveroption.getText().toString().trim();
        avalibleitem =etavilableitem.getText().toString().trim();

        expirdate =expirtdate.getText().toString().trim();
        MealClass info = new MealClass(namedesh, avalibleitem, pricena, String.valueOf(uri), RandomUId, userid,paymetoption, optiondeliver,expirdate,"");
                            databaseReference.child(RandomUId).setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Dish posted successfully", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
//                      /*this.mealname = mealname;
//        this.avilableitem = avilableitem;
//        this.price = price;
//        this.imgurl = imgurl;
//        this.randounid = randounid;
//        this.resturentid = resturentid;
//        this.paymentoption = paymentoption;
//        this.deliveryoption = deliveryoption;
//        this.ecpirydate = ecpirydate;
//        this.userId = userId;*/
                        }
                    });
                }
            });
        }
    }


    @Override
    public void onClick(View v) {
        if( v.getId()==R.id.addMeals_payment_options_Cash){
            paymetoption = radioButton1.getText()+"";


        }
        else{   paymetoption = radioButton2.getText()+"";}
    }
}