package com.example.eaters.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eaters.Adapters.Adicional_Adapter;
import com.example.eaters.Classes.Adicional;
import com.example.eaters.Classes.Food;
import com.example.eaters.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ItemPedido_Fragment extends Fragment {

    private View v;
    RecyclerView rv_adicional;
    Adicional_Adapter acom_adapter;
    private Button btn_add;
    TextView pedido_name;
    TextView pedido_preco;
    TextView pedido_ingredientes;
    TextView pedido_nota;
    ImageView pedido_food_img;
    CardView pedido_itemfood;
    Food mFood;
    ArrayList<Adicional> mAdicionals;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_itempedido, container, false);
        rv_adicional = v.findViewById(R.id.rv_adicional);
        rv_adicional.setHasFixedSize(true);
        rv_adicional.setLayoutManager(new LinearLayoutManager(getContext()));
        btn_add = v.findViewById(R.id.btn_add);

        pedido_name = (TextView) v.findViewById(R.id.pedido_food_name);
        pedido_preco = (TextView) v.findViewById(R.id.pedido_food_preco);
        pedido_nota = (TextView) v.findViewById(R.id.pedido_food_nota);
        pedido_ingredientes = (TextView) v.findViewById(R.id.pedido_food_ingredients);
        pedido_food_img = (ImageView) v.findViewById(R.id.pedido_food_img);

        pedido_itemfood = v.findViewById(R.id.pedido_itemfood);

        Bundle bundlerecebido = this.getArguments();
        if( bundlerecebido != null ) {
            String valorRecebido = bundlerecebido.getString("nomePizza");
            mFood = (Food) bundlerecebido.get("Food");
            pedido_name.setText(mFood.getName());
            pedido_preco.setText(mFood.getPreco());
            pedido_ingredientes.setText(mFood.getIngredientes());
            pedido_nota.setText(mFood.getNota());

            int id_back = getContext().getResources().getIdentifier(mFood.getFood_img(), "drawable", getContext().getPackageName());

             Drawable drawable_back = getContext().getResources().getDrawable(id_back);
            if (drawable_back != null) {
                pedido_food_img.setImageDrawable(drawable_back);
            }
        }


        String acomDummyData = "Adicional.json";
        mAdicionals = getAssetJsonData(getContext(), acomDummyData);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Adicionou "+ mFood.getName() +" ao Carrinho!",Toast.LENGTH_SHORT).show();
                Cart_Fragment newFragment = new Cart_Fragment();
                //FragmentManager manager = ((MainActivity)getContext()).getSupportFragmentManager();
                FragmentManager manager = (getActivity().getSupportFragmentManager());

                //Pedido mPedido = new Pedido(0,mFood,1, mAcompanhamentos,1);
                //R.id.navigation

                Bundle args = new Bundle();
                args.putParcelable("Food", mFood);
                newFragment.setArguments(args);

                FragmentActivity mActivity = getActivity();
                //mActivity.getAssets().


                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });


        return v;
    }

    public ArrayList<Adicional> getAssetJsonData(Context context, String arqData) {
        String json = null;

        ArrayList<Adicional> adicionals = new ArrayList<Adicional>();
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
                Integer preco = novoacomp.getInt("preco");
                String logo = novoacomp.getString("logo_path");


                Adicional acomp = new Adicional(id, name, preco, logo);

                adicionals.add(acomp);

            }


            acom_adapter = new Adicional_Adapter(context, adicionals);
            rv_adicional.setAdapter(acom_adapter);


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.e("Dados Favoritos", json);


        return adicionals;
    }
}

