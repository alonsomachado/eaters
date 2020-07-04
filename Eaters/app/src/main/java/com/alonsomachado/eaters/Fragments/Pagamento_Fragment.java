package com.alonsomachado.eaters.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alonsomachado.eaters.Activities.ProgressBarActivity;
import com.alonsomachado.eaters.Dialogs.Dialog_TrocoPagamento;
import com.example.eaters.R;

public class Pagamento_Fragment extends Fragment implements Dialog_TrocoPagamento.ExampleDialogListener{

    View v;
    private TextView name;
    private TextView textViewValor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_pagamento, container, false);
        CardView pagapp1 = v.findViewById(R.id.pagamentoApp1);
        CardView pagapp2 = v.findViewById(R.id.pagamentoApp2);
        CardView pagcash = v.findViewById(R.id.pagamentoEntrega_cash);
        CardView pagpos = v.findViewById(R.id.pagamentoEntrega_pos);

        textViewValor = v.findViewById(R.id.pagamentoEntrega_cash_valor);

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
        if (textViewValor.getVisibility() == View.VISIBLE) {
            //changeFragment();

            Intent intent = new Intent(getActivity(), ProgressBarActivity.class);;
            int i = 19;
            getActivity().startActivityFromFragment(this,intent,i);
            //chamadaProgress();
        } else { //View.GONE (Valor de pagamento em espécie não está aparecendo)
            Dialog_TrocoPagamento myDialog = new Dialog_TrocoPagamento();

            myDialog.setTargetFragment(Pagamento_Fragment.this, 1);
            //exampleDialog.show(((MainActivity)getContext()).getSupportFragmentManager(), "example dialog");
            myDialog.show(getFragmentManager(), "Meu dialog");
        }

    }

    @Override
    public void applyTexts(String valor) {
        textViewValor.setText(valor);
        textViewValor.setVisibility(View.VISIBLE);
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
