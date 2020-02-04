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

import com.example.eaters.Adapters.MenuFood_Adapter;
import com.example.eaters.Adapters.MenuFoodCategory_Adapter;
import com.example.eaters.Classes.Food;
import com.example.eaters.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MenuRestaurant_Fragment extends Fragment {

    private View v;
    RecyclerView rv_food;
    MenuFood_Adapter food_adapter;

    RecyclerView rv_menufood;
    MenuFoodCategory_Adapter menu_adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_menufood, container, false);

        rv_food = v.findViewById(R.id.rv_food);
        rv_food.setHasFixedSize(true);
        rv_food.setLayoutManager(new LinearLayoutManager(getContext()));


        String foodDummyData = "Food_Data.json";
        getAssetJsonData(getContext(), foodDummyData);

        rv_menufood = v.findViewById(R.id.rv_menufood);
        rv_menufood.setHasFixedSize(true);
        rv_menufood.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        getAssetTipoJsonData(getContext(), foodDummyData);

        return v;
    }

    public ArrayList<Food> getAssetJsonData(Context context, String arqData) {
        String json = null;
        //HashSet<String> foodtypemenu = new HashSet<String>();
        ArrayList<Food> foods = new ArrayList<Food>();
        try {
            InputStream is = context.getAssets().open(arqData);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONArray jsonArray= new JSONArray(json);


            for ( int i=0 ; i< jsonArray.length(); i++ ){

                JSONObject itemmenu = jsonArray.getJSONObject(i);

                String id = itemmenu.getString("id");
                String name = itemmenu.getString("name");
                String ingredientes = itemmenu.getString("ingredientes");
                String logo_path = itemmenu.getString("logo_path");
                String back_img_path = itemmenu.getString("back_img_path");
                String preco = itemmenu.getString("preco");
                String nota = itemmenu.getString("nota");
                String tipo = itemmenu.getString("tipo");

                Food fooditem = new Food(id,name,ingredientes,back_img_path,logo_path,preco,nota,tipo);

                //foodtypemenu.add(tipo);
                foods.add(fooditem);

            }
            //Foods in the RV
            food_adapter = new MenuFood_Adapter(context, foods);
            rv_food.setAdapter(food_adapter);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Menu do Restaurante: ", json);

        return foods;
    }

    public ArrayList<Food> getAssetTipoJsonData(Context context, String arqData) {
        String json = null;

        ArrayList<Food> foodmenu = new ArrayList<Food>();
        try {
            InputStream is = context.getAssets().open(arqData);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONArray jsonArray= new JSONArray(json);


            for ( int i=0 ; i< jsonArray.length(); i++ ){

                JSONObject itemmenu = jsonArray.getJSONObject(i);

                String id = itemmenu.getString("id");
                String name = itemmenu.getString("name");
                String ingredientes = itemmenu.getString("ingredientes");
                String logo_path = itemmenu.getString("logo_path");
                String back_img_path = itemmenu.getString("back_img_path");
                String preco = itemmenu.getString("preco");
                String nota = itemmenu.getString("nota");
                String tipo = itemmenu.getString("tipo");

                Food fooditem = new Food(id,name,ingredientes,back_img_path,logo_path,preco,nota,tipo);
                foodmenu.add(fooditem);

            }

            //Types of Foods for the Other RV
            menu_adapter = new MenuFoodCategory_Adapter(context, foodmenu);
            rv_menufood.setAdapter(menu_adapter);


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Menu do Restaurante: ", json);


        return foodmenu;
    }


}
