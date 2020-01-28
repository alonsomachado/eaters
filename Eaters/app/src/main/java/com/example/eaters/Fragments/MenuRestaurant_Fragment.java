package com.example.eaters.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


public class MenuRestaurant_Fragment extends Fragment {

    private View v;
   // private Button btn_add_div;
    RecyclerView rv_restaurants;
    Restaurant_Adapter rest_adapter;


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
                String distance = restaurant.getString("distance");
                String stars_review = restaurant.getString("stars_review");

                Restaurant restaurant_rv= new Restaurant(id, name, description,logo_path,back_img_path,time_distance,distance,stars_review);

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

        Log.e("Dados Menu do Restaurante", json);


        return restaurants;
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


}
