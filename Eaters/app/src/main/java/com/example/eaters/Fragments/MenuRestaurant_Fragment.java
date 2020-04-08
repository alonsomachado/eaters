package com.example.eaters.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eaters.Adapters.MenuFood_Adapter;
import com.example.eaters.Adapters.MenuFoodCategory_Adapter;
import com.example.eaters.Classes.Food;
import com.example.eaters.Classes.Restaurant;
import com.example.eaters.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MenuRestaurant_Fragment extends Fragment {

    private View v;

    TextView nome;
    RecyclerView rv_food;
    MenuFood_Adapter food_adapter;

    RecyclerView rv_foodcategory;
    MenuFoodCategory_Adapter foodcategory_adapter;

    TextView res_name;
    TextView res_time_dis;
    TextView res_distance;
    TextView res_stars_review;
    TextView res_resttipo;
    ImageView res_background_img;
    ImageView res_logo;

    CardView res_restaurant;
    Restaurant mRestaurant;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_menufood, container, false);

        rv_food = v.findViewById(R.id.rv_food);
        rv_food.setHasFixedSize(true);
        rv_food.setLayoutManager(new LinearLayoutManager(getContext()));

        res_name = (TextView) v.findViewById(R.id.menufood_restaurantlabel);
        res_time_dis = (TextView) v.findViewById(R.id.menufood_rest_time);
        res_stars_review = (TextView) v.findViewById(R.id.menufood_stars_num);
        res_distance = (TextView) v.findViewById(R.id.menufood_rest_distance);
        res_background_img = v.findViewById(R.id.menufood_back_img_rest);
        res_logo = v.findViewById(R.id.menufood_rest_logo);
        res_resttipo = (TextView) v.findViewById(R.id.menufood_resttipo);
        res_restaurant = v.findViewById(R.id.menufood_restaurant);



        Bundle bundlerecebido = this.getArguments();
        if( bundlerecebido != null ) {
            String valorRecebido = bundlerecebido.getString("nomeRestaurant");
            mRestaurant = (Restaurant) bundlerecebido.get("Restaurant");
            res_name.setText(valorRecebido);

            int id_back =getContext().getResources().getIdentifier(mRestaurant.getBack_img_rest(), "drawable", getContext().getPackageName());
            int id_logo = getContext().getResources().getIdentifier(mRestaurant.getLogo_path(), "drawable", getContext().getPackageName());
            Drawable drawable_back = getContext().getResources().getDrawable(id_back);
            Drawable drawable_logo = getContext().getResources().getDrawable(id_logo);


            res_background_img.setImageDrawable(drawable_back);
            res_logo.setImageDrawable(drawable_logo);

            res_name.setText(mRestaurant.getName());
            res_time_dis.setText(mRestaurant.getTime_distance());
            res_distance.setText(mRestaurant.getDistance());
            res_stars_review.setText(mRestaurant.getStars_review());
            res_resttipo.setText(mRestaurant.getResttipo());
            //Toast.makeText(getContext(), "Recebeu:  " + valorRecebido, Toast.LENGTH_SHORT).show();
        }

        String foodDummyData = "Food_Data.json";
        getAssetJsonData(getContext(), foodDummyData);

        rv_foodcategory = v.findViewById(R.id.rv_menucategoryfood);
        rv_foodcategory.setHasFixedSize(true);
        rv_foodcategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

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
            foodcategory_adapter = new MenuFoodCategory_Adapter(context, foodmenu);
            rv_foodcategory.setAdapter(foodcategory_adapter);


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
