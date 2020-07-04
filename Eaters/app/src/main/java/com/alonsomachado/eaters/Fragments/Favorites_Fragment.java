package com.alonsomachado.eaters.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alonsomachado.eaters.Adapters.Favorites_Adapter;
import com.alonsomachado.eaters.Model.Restaurant;
import com.example.eaters.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Favorites_Fragment extends Fragment {


        private View v;
        RecyclerView rv_fav;
        Favorites_Adapter fav_adapter;

        ArrayList<Restaurant> restaurantsg = new ArrayList<Restaurant>();



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            v = inflater.inflate(R.layout.fragment_favorites, container, false);
            rv_fav = v.findViewById(R.id.rv_fav);
            rv_fav.setHasFixedSize(true);
            rv_fav.setLayoutManager(new LinearLayoutManager(getContext()));


            rv_fav.addOnScrollListener(new RecyclerView.OnScrollListener() {
                private static final int PAGE_SIZE = 10;
                boolean isLoading = false;
                boolean isLastPage = false;

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    LinearLayoutManager layoutManager = (LinearLayoutManager) rv_fav.getLayoutManager();
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    if((visibleItemCount + firstVisibleItemPosition) < totalItemCount) {
                        isLastPage = false;
                        Log.d("rv_favSCROLL", "isLastPage FALSE");
                    }
                    if (!isLoading && !isLastPage) {
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                                && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                            //loadMoreItems();
                            String restauranteDummyData = "Restaurants_Data.json";
                            getAssetJsonData(getContext(), restauranteDummyData);
                            Log.d("rv_favSCROLL", "onScrolled: Tentou carregar uma pagina nova");
                            Log.d("rv_favSCROLLTItemCount", "TotalItemCount: "+totalItemCount);
                            isLoading = true;

                            if((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                                isLastPage = true;
                                Log.d("rv_favSCROLL", "isLastPage TRUE");
                                isLoading = false;
                            }

                        }
                    }

                }
            });


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
                String resttipo = restaurant.getString("resttipo");
                String distance = restaurant.getString("distance");
                String stars_review = restaurant.getString("stars_review");

                Restaurant restaurant_rv= new Restaurant(id, name, description,logo_path,back_img_path,time_distance,distance,stars_review,resttipo);

                //restaurants.add(restaurant_rv);
                restaurantsg.add(restaurant_rv);

            }


            fav_adapter = new Favorites_Adapter(context, restaurantsg);
            rv_fav.setAdapter(fav_adapter);


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.i("Dados Favoritos", json);


        return restaurants;
    }
}

