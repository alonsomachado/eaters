package com.example.eaters.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eaters.R;


public class Profile_Fragment extends Fragment {

    View v;
    private TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile_, container, false);

        name= v.findViewById(R.id.profile_name);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String person_name = settings.getString("name", "");

        name.setText(person_name);



        return v;
    }

}
