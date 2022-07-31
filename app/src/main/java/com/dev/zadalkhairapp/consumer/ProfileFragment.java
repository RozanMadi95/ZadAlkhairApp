package com.dev.zadalkhairapp.consumer;

import android.os.Bundle;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView listViewAccount, listViewOthers;
    SearchView searchView;
    ArrayAdapter<String> adapterAccount, adapterOthers;
    String[] itemsAccount = {"Edit Profile","Change Password","Change Language"};
    String[] itemsOthers = {"Privacy Policy","Terms & Conditions","Logout"};

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
         View view = inflater.inflate(R.layout.fragment_profile, container, false);
         listViewAccount =(ListView) view.findViewById(R.id.account_profile_listview);
         listViewOthers =(ListView) view.findViewById(R.id.others_profile_listview);

        adapterAccount = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, itemsAccount);
        listViewAccount.setAdapter(adapterAccount);
        listViewAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), i+""+ view.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        adapterOthers = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, itemsOthers);
        listViewOthers.setAdapter(adapterOthers);
        listViewOthers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), i+"", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}