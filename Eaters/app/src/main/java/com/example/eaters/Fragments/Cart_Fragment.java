package com.example.eaters.Fragments;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eaters.Adapters.Cart_Adapter;
import com.example.eaters.Classes.Adicional;
import com.example.eaters.Classes.Food;
import com.example.eaters.R;

import java.util.ArrayList;

public class Cart_Fragment extends Fragment {

   private View v;
    RecyclerView rv_cart;
    Cart_Adapter cart_adapter;
    private Button btn_action;
    private TextView address, tvsubprice, tvdiscount, tvdelivertax, tvtotal;
    Food mFood;
    ArrayList<Food> foods = new ArrayList<Food>();
    ArrayList<Adicional> adicionals = new ArrayList<Adicional>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String person_adress = settings.getString("address", " ");

        v = inflater.inflate(R.layout.fragment_cart, container, false);
        rv_cart = v.findViewById(R.id.rv_cart);
        rv_cart.setHasFixedSize(true);
        rv_cart.setLayoutManager(new LinearLayoutManager(getContext()));
        btn_action = v.findViewById(R.id.btn_action);
        address = v.findViewById(R.id.cart_address);
        tvsubprice = v.findViewById(R.id.cart_pay_sub);
        tvdiscount = v.findViewById(R.id.cart_pay_desc);
        tvdelivertax = v.findViewById(R.id.cart_pay_delivery);
        tvtotal = v.findViewById(R.id.cart_pay_total);

        Double price = 0.0;
        Double subprice = 0.0;
        Double discount = 0.0; //Desconto
        Double delivertax = 0.0; //Delivery Tax
        Double total = 0.0; //Total

        address.setText(person_adress);

        //Pedido mPedido = new Pedido(0,mFood,1, mAcompanhamentos,1);
        //R.id.navigation

        Bundle bundlerecebido = this.getArguments();
        if( bundlerecebido != null ) {
            String valorRecebido = bundlerecebido.getString("nomeFood");
            mFood = (Food) bundlerecebido.get("Food");
            price = Double.parseDouble(mFood.getPreco()); //String.valueOf();
            subprice = subprice + price ;
            foods.add(mFood);

        }
        cart_adapter = new Cart_Adapter(getContext(), foods, adicionals);
        rv_cart.setAdapter(cart_adapter);
        total = subprice - discount;
        tvdiscount.setText(String.valueOf(discount));
        tvdelivertax.setText(String.valueOf(delivertax));
        tvsubprice.setText(String.valueOf(subprice));
        tvtotal.setText(String.valueOf(total));

        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Selecione o Meio de Pagamento! ",Toast.LENGTH_SHORT).show();
                //mContext = getContext();
                Pagamento_Fragment newFragment = new Pagamento_Fragment();
                //FragmentManager manager = ((MainActivity)mContext).getSupportFragmentManager();
                FragmentManager manager = (getActivity().getSupportFragmentManager());

                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });



        return v;
    }
}
