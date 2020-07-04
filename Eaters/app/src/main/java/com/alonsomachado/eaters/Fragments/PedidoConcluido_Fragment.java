package com.alonsomachado.eaters.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eaters.R;

public class PedidoConcluido_Fragment extends Fragment {

    private View v;
    private TextView address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_pedidoconcluido, container, false);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String person_adress = settings.getString("address", "");

        address = v.findViewById(R.id.pedidoconcluidoadress_address);

        address.setText(person_adress);

        return v;
    }
}
