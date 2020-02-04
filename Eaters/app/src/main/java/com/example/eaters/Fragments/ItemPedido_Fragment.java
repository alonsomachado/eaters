package com.example.eaters.Fragments;

import android.content.Context;
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
import com.example.eaters.Adapters.Acompanhamento_Adapter;
import com.example.eaters.Classes.Acompanhamento;
import com.example.eaters.Classes.Restaurant;
import com.example.eaters.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ItemPedido_Fragment extends Fragment {

    private View v;
    RecyclerView rv_acompanhamento;
    Acompanhamento_Adapter acom_adapter;
    private Button btn_adicionar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_itempedido, container, false);
        rv_acompanhamento = v.findViewById(R.id.rv_acompanhamento);
        rv_acompanhamento.setHasFixedSize(true);
        rv_acompanhamento.setLayoutManager(new LinearLayoutManager(getContext()));
        btn_adicionar= v.findViewById(R.id.btn_adicionar);


        String acomDummyData = "Acompanhamento.json";
        getAssetJsonData(getContext(), acomDummyData);

        btn_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Clicou em Adicionar! ",Toast.LENGTH_SHORT).show();
                //mContext = getContext();
                Cart_Fragment newFragment = new Cart_Fragment();
                //FragmentManager manager = ((MainActivity)getContext()).getSupportFragmentManager();
                FragmentManager manager = (getActivity().getSupportFragmentManager());

                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });


        return v;
    }

    public ArrayList<Acompanhamento> getAssetJsonData(Context context, String arqData) {
        String json = null;

        ArrayList<Acompanhamento> acompanhamentos = new ArrayList<Acompanhamento>();
        try {
            InputStream is = context.getAssets().open(arqData);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONArray jsonArray= new JSONArray(json);


            for ( int i=0 ; i< jsonArray.length(); i++ ){

                JSONObject novoacomp = jsonArray.getJSONObject(i);

                String id = novoacomp.getString("id");
                String name = novoacomp.getString("name");
                String quantidade = novoacomp.getString("quantidade");
                String logo = novoacomp.getString("logo_path");


                Acompanhamento acomp = new Acompanhamento(id, name, quantidade, logo);

                acompanhamentos.add(acomp);

            }


            acom_adapter = new Acompanhamento_Adapter(context, acompanhamentos);
            rv_acompanhamento.setAdapter(acom_adapter);


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.e("Dados Favoritos", json);


        return acompanhamentos;
    }
}

