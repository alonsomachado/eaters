package com.alonsomachado.eaters.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.alonsomachado.eaters.Database.FoodDatabase;
import com.alonsomachado.eaters.Adapters.MenuFoodCategory_Adapter;
import com.alonsomachado.eaters.Adapters.MenuFood_Adapter;
import com.alonsomachado.eaters.Model.Food;
import com.alonsomachado.eaters.Model.Restaurant;
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
    FoodDatabase foodDB;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_menufood, container, false);
        this.foodDB = FoodDatabase.getDatabase(getContext());
        rv_food = v.findViewById(R.id.rv_food);
        rv_food.setHasFixedSize(true);
        rv_food.setLayoutManager(new LinearLayoutManager(getContext()));
        //RecyclerView.OnScrollListener recyclerViewOnScrollListener;
        rv_food.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private static final int PAGE_SIZE = 10;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = rv_food.getLayoutManager();
                //LinearLayoutManager layoutManager = (new LinearLayoutManager(getContext()) );
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = 1; //layoutManager.findFirstVisibleItemPosition();

                boolean isLoading = false;
                boolean isLastPage = false;
                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                        //loadMoreItems();
                        Log.d("onScrolled", "onScrolled: Tentou carregar uma pagina nova");

                    }
                }
            }
        });

        res_name = (TextView) v.findViewById(R.id.menufood_restaurantlabel);
        res_time_dis = (TextView) v.findViewById(R.id.menufood_rest_time);
        res_stars_review = (TextView) v.findViewById(R.id.menufood_stars_num);
        res_distance = (TextView) v.findViewById(R.id.menufood_rest_distance);
        res_background_img = v.findViewById(R.id.menufood_back_img_rest);
        res_logo = v.findViewById(R.id.menufood_rest_logo);
        res_resttipo = (TextView) v.findViewById(R.id.menufood_resttipo);
        res_restaurant = v.findViewById(R.id.menufood_restaurant);


        Bundle bundlerecebido = this.getArguments();
        if (bundlerecebido != null) {
            String valorRecebido = bundlerecebido.getString("nomeRestaurant");
            mRestaurant = (Restaurant) bundlerecebido.get("Restaurant");
            res_name.setText(valorRecebido);

            int id_back = getContext().getResources().getIdentifier(mRestaurant.getBack_img_rest(), "drawable", getContext().getPackageName());
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
                String food_img = itemmenu.getString("food_img");
                String preco = itemmenu.getString("preco");
                String nota = itemmenu.getString("nota");
                String tipo = itemmenu.getString("tipo");

                Food fooditem = new Food(name,ingredientes,food_img,preco,nota,tipo);

                //Colocar no SQLite atravéz do ROOM e DAO
                //foodDB.getFoodDao().insertFood(fooditem);
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

        Log.i("Menu do Restaurante: ", json);

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
                String food_img = itemmenu.getString("food_img");
                String preco = itemmenu.getString("preco");
                String nota = itemmenu.getString("nota");
                String tipo = itemmenu.getString("tipo");

                Food fooditem = new Food(name,ingredientes,food_img,preco,nota,tipo);

                //Colocar no SQLite atravéz do ROOM e DAO
                foodDB.getFoodDao().insertFood(fooditem);
                Log.e("Adicionou: ", fooditem.toString());
                foodmenu.add(fooditem);

            }

            //Category of Foods for the Category RV
            foodcategory_adapter = new MenuFoodCategory_Adapter(context, foodmenu);
            rv_foodcategory.setAdapter(foodcategory_adapter);


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("Menu do Restaurante: ", json);


        return foodmenu;
    }


}
