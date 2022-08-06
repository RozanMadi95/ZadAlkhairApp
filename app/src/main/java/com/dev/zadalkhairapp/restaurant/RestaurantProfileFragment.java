package com.dev.zadalkhairapp.restaurant;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dev.zadalkhairapp.R;
import com.dev.zadalkhairapp.activity.AboutAppActivity;
import com.dev.zadalkhairapp.activity.TermsnAndConditionsActivity;
import com.dev.zadalkhairapp.consumer.EditProfileActivity;

public class RestaurantProfileFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView listViewAccount, listViewOthers;
    SearchView searchView;
    ArrayAdapter<String> adapterAccount, adapterOthers;
    String[] itemsAccount = {"تعديل الملف الشخصي","تغير كلمة المرور"};
    String[] itemsOthers = {"عن التطبيق","الشروط والقواعد","تسجيل خروج"};
//    String[] itemsAccount = {"Edit Profile","Change Password"};
//    String[] itemsOthers = {"Privacy Policy","Terms & Conditions","Logout"};
    private String mParam1;
    private String mParam2;

    public RestaurantProfileFragment() {
        // Required empty public constructor
    }

    public static RestaurantProfileFragment newInstance(String param1, String param2) {
        RestaurantProfileFragment fragment = new RestaurantProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_restaurant_profile, container, false);
        listViewAccount =(ListView) view.findViewById(R.id.account_profile_listview);
        listViewOthers =(ListView) view.findViewById(R.id.others_profile_listview);

        adapterAccount = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, itemsAccount);
        listViewAccount.setAdapter(adapterAccount);
        listViewAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(getContext(), i+""+ view.toString(), Toast.LENGTH_SHORT).show();
                switch (i) {
                    case 0:
                        startActivity(new Intent(getActivity(), RestaurantEditProfileActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
                        break;
                }
            }
        });
        adapterOthers = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, itemsOthers);
        listViewOthers.setAdapter(adapterOthers);
        listViewOthers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(getContext(), i+"", Toast.LENGTH_SHORT).show();
                switch (i){
                    case 0:
                        startActivity(new Intent(getActivity(), AboutAppActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), TermsnAndConditionsActivity.class));
                        break;
                    case 2:
                        new AlertDialog.Builder(getContext())
                                .setMessage(getResources().getString(R.string.Logout))
                                .setCancelable(false)
                                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                })
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        getActivity().finish();
                                    }
                                })
                                .show();
                        break;

                }

            }
        });        return view;
    }
}