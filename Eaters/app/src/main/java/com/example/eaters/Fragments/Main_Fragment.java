package com.example.eaters.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eaters.Adapters.Promotions_Adapter;
import com.example.eaters.Adapters.Restaurant_Adapter;
import com.example.eaters.Classes.Promocao;
import com.example.eaters.Classes.Restaurant;
import com.example.eaters.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Main_Fragment extends Fragment {

    private View v;
   // private Button btn_add_div;
    RecyclerView rv_restaurants;
    Restaurant_Adapter rest_adapter;
    RecyclerView rv_promo;
    Promotions_Adapter promo_adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_main_, container, false);
        rv_restaurants = v.findViewById(R.id.rv_rest);
        rv_restaurants.setHasFixedSize(true);
        rv_restaurants.setLayoutManager(new LinearLayoutManager(getContext()));

        String restauranteDummyData = "Restaurants_Data.json";
        getAssetJsonData(getContext(), restauranteDummyData);


        rv_promo = v.findViewById(R.id.rv_promo);
        rv_promo.setHasFixedSize(true);
        rv_promo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        String promocaoDummyData = "Promocao_Data.json";
        getAssetPromoJsonData(getContext(), promocaoDummyData);

        /*public void chamadamenu(Integer pos){
            FragmentManager fragmentManager =  super.getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            MenuRestaurant_Fragment fragment = new MenuRestaurant_Fragment();
            //fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container, fragment, getString(R.string.menurestaurante) );
            fragmentTransaction.commit();
        }*/

        return v;
    }

    public ArrayList<Restaurant> getAssetJsonData(Context context, String arqData) {
        String json = null;

        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        try {
            InputStream is = context.getAssets().open(arqData);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONArray jsonArray= new JSONArray(json);


            for ( int i=0 ; i< jsonArray.length(); i++ ){

                JSONObject restaurant = jsonArray.getJSONObject(i);

                String id = restaurant.getString("id");
                String name = restaurant.getString("name");
                String description = restaurant.getString("description");
                String logo_path = restaurant.getString("logo_path");
                String back_img_path = restaurant.getString("back_img_path");
                String time_distance = restaurant.getString("time_distance");
                String resttipo = restaurant.getString("resttipo");
                String distance = restaurant.getString("distance");
                String stars_review = restaurant.getString("stars_review");

                Restaurant restaurant_rv= new Restaurant(id, name, description,logo_path,back_img_path,time_distance,distance,stars_review,resttipo);

                restaurants.add(restaurant_rv);

            }


            rest_adapter = new Restaurant_Adapter(context, restaurants);
            rv_restaurants.setAdapter(rest_adapter);


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.e("Dados Restaurantes", json);


        return restaurants;
    }

    public ArrayList<Promocao> getAssetPromoJsonData(Context context, String arqData) {
        String json = null;

        ArrayList<Promocao> promocoes = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open(arqData);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONArray jsonArray= new JSONArray(json);


            for ( int i=0 ; i< jsonArray.length(); i++ ){

                JSONObject promocao = jsonArray.getJSONObject(i);

                String id = promocao.getString("id");
                String name = promocao.getString("name");
                String description = promocao.getString("description");
                String back_img_path = promocao.getString("back_img_path");


                Promocao promocaoItem = new Promocao(id, name, back_img_path,description);

                promocoes.add(promocaoItem);

            }


            promo_adapter = new Promotions_Adapter(context, promocoes);
            rv_promo.setAdapter(promo_adapter);


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.e("Dados Promocoes", json);


        return promocoes;
    }

    public static String getJsonData(Context context, String arqData) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(arqData);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Log.e("data", json);
        return json;

    }

    /*public void chamadamenu(Fragment fragment, String tag){
        FragmentManager fragmentManager =  super.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        MenuRestaurant_Fragment fragment = new MenuRestaurant_Fragment();
        //fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, fragment, getString(R.string.menurestaurante) );
        fragmentTransaction.commit();
    }*/

    /*rest_adapter.setOnItemClickListener(new Restaurant_Adapter.OnItemClickListener() {
        @Override
        public void onItemClick(int post) {

            Intent intent = new Intent(MainActivity.this, AddEditNoteActivity.class);
            intent.putExtra(AddEditNoteActivity.EXTRA_ID, note.getId());
            intent.putExtra(AddEditNoteActivity.EXTRA_TITLE, note.getTitle());
            intent.putExtra(AddEditNoteActivity.EXTRA_DESCRIPTION, note.getDescription());
            intent.putExtra(AddEditNoteActivity.EXTRA_PRIORITY, note.getPriority());
            startActivityForResult(intent, EDIT_NOTE_REQUEST);
        }
    });*/


}
