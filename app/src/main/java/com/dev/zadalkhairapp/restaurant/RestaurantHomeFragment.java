package com.dev.zadalkhairapp.restaurant;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.zadalkhairapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class RestaurantHomeFragment extends Fragment {

    RecyclerView rvnamesmeals, rvNamesMeals;
    View view;
    Uri imageUri;
//    FirebaseStorage storage;
//    StorageReference storageReference;

    String RandomUId, Mealname, avilibleitem, Price, resturentid, price, imgurl, paymentoption, deliveryoption, ecpiryDate;

    private ArrayList<MealClass> mealarray = new ArrayList<>();
    private ResturentAdapterMeals adapter;
    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;
    String userid;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RestaurantHomeFragment() {
        // Required empty public constructor
    }

    public static RestaurantHomeFragment newInstance(String param1, String param2) {
        RestaurantHomeFragment fragment = new RestaurantHomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_restaurant_home, container, false);
        rvnamesmeals = view.findViewById(R.id.restaurantHome_rv_names_meals);
        rvnamesmeals.setHasFixedSize(true);
        rvnamesmeals.setLayoutManager(new LinearLayoutManager(getContext()));
        userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("MealDetils");
        return view;

//        private String Mealname;
//        private String AvailableItem;
//        private String Price;
//        private String ImageURL;
//        private String RandomUID;
//        private String ResturentID;
//        private String PaymentOptions;
//        private String DelivaryOption;
//        private String EcpiryDate;
//        return  view;
    }


}