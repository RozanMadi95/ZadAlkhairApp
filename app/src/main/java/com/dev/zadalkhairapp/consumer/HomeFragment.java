package com.dev.zadalkhairapp.consumer;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.zadalkhairapp.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

     RecyclerView rvNamesRestaurants, rvNamesMeals;
     View view;
     AppCompatTextView tvUserName;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        view=  inflater.inflate(R.layout.fragment_home, container, false);
        tvUserName = view.findViewById(R.id.consumerHome_userName);
        rvNamesRestaurants = view.findViewById(R.id.consumerHome_rv_names_restaurants);
        rvNamesMeals = view.findViewById(R.id.consumerHome_rv_names_meals);

        return view;
    }


}