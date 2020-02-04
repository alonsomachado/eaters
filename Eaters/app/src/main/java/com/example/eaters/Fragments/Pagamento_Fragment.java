package com.example.eaters.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eaters.Activities.MainActivity;
import com.example.eaters.Dialogs.Dialog_TrocoPagamento;
import com.example.eaters.R;

public class Pagamento_Fragment extends Fragment implements Dialog_TrocoPagamento.ExampleDialogListener{

    View v;
    private TextView name;
    private TextView textViewUsername;
    private TextView textViewPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_pagamento, container, false);
        CardView pagapp1 = v.findViewById(R.id.pagamentoApp1);
        CardView pagapp2 = v.findViewById(R.id.pagamentoApp2);
        CardView pagcash = v.findViewById(R.id.pagamentoEntrega_cash);
        CardView pagpos = v.findViewById(R.id.pagamentoEntrega_pos);

        textViewUsername = v.findViewById(R.id.pagamentoApplabel);
        textViewPassword = v.findViewById(R.id.pagamentoEntregalabel);

        //name= v.findViewById(R.id.profile_name);

        //SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        //String person_name = settings.getString("name", "");

        //name.setText(person_name);

        pagapp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { changeFragment();  }
        });

        pagapp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { changeFragment();  }
        });

        pagcash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        pagpos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { changeFragment();  }
        });

        return v;
    }

    public void openDialog() {
        Dialog_TrocoPagamento exampleDialog = new Dialog_TrocoPagamento();
        exampleDialog.setTargetFragment(Pagamento_Fragment.this, 1);
        //exampleDialog.show(((MainActivity)getContext()).getSupportFragmentManager(), "example dialog");
        exampleDialog.show(getFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String username, String password) {
        textViewUsername.setText(username);
        textViewPassword.setText(password);
    }

    public void changeFragment() {

        Toast.makeText(getContext(),"Pedido Concluído, aguardando confirmação do estabelecimento! ",Toast.LENGTH_SHORT).show();
        //mContext = getContext();
        PedidoConcluido_Fragment newFragment = new PedidoConcluido_Fragment();
        //FragmentManager manager = ((MainActivity)getContext()).getSupportFragmentManager();
        FragmentManager manager = (getActivity().getSupportFragmentManager());

        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
