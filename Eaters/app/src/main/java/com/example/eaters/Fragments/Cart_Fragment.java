package com.example.eaters.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.eaters.Activities.MainActivity;
import com.example.eaters.Adapters.Cart_Adapter;
import com.example.eaters.Adapters.Favorites_Adapter;
import com.example.eaters.R;

public class Cart_Fragment extends Fragment {

   private View v;
    RecyclerView rv_cart;
    Cart_Adapter fav_adapter;
    private Button btn_action;
    private String mensagem = "";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Bundle bundle = getArguments();
        if(bundle != null){ //Bundle tem alguma coisa
            mensagem = bundle.getString();
        }*/

        v = inflater.inflate(R.layout.fragment_cart_, container, false);
        rv_cart = v.findViewById(R.id.rv_cart);
        rv_cart.setHasFixedSize(true);
        rv_cart.setLayoutManager(new LinearLayoutManager(getContext()));
        btn_action = v.findViewById(R.id.btn_action);

        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Selecione o Meio de Pagamento! ",Toast.LENGTH_SHORT).show();
                //mContext = getContext();
                Cart_Fragment newFragment = new Cart_Fragment();
                FragmentManager manager = ((MainActivity)getContext()).getSupportFragmentManager();

                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });



        return v;
    }
}
