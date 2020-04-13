package com.example.eaters.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eaters.Activities.LoginActivity;
import com.example.eaters.R;


public class Profile_Fragment extends Fragment {

    View v;
    private TextView name;
    private Button btn_logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false);

        name= v.findViewById(R.id.profile_name);
        btn_logout = v.findViewById(R.id.btn_logout);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String person_name = settings.getString("name", "");

        name.setText(person_name);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("logado", false);
                editor.commit();
                Toast.makeText(getContext(),"Deslogou da aplicação!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }
        });



        return v;
    }

}
