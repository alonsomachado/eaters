package com.example.eaters.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eaters.Activities.MainActivity;
import com.example.eaters.Classes.Restaurant;
import com.example.eaters.Fragments.MenuRestaurant_Fragment;
import com.example.eaters.R;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;



public class Restaurant_Adapter extends RecyclerView.Adapter<Restaurant_Adapter.ViewHolder> {

    //private OnItemClickListener listener;
    private Context mContext;
    private List<Restaurant> mListaRestaurants;
    public FragmentManager fragmentManager;

    public Restaurant_Adapter(Context mContext, List<Restaurant> mListaDivisions) {
        this.mContext = mContext;
        this.mListaRestaurants = mListaDivisions;
    }

    @NonNull
    @Override
    public Restaurant_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View divisionView = inflater.inflate(R.layout.restaurant_item, parent, false);
        Restaurant_Adapter.ViewHolder viewHolder = new Restaurant_Adapter.ViewHolder(divisionView);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull Restaurant_Adapter.ViewHolder holder, final int position) {
        TextView name = holder.name;
        TextView time = holder.time_dis;
        TextView distance = holder.distance;
        TextView stars = holder.stars_review;
        TextView resttipo = holder.resttipo;
        ImageView back_img = holder.background_img;
        ImageView logo = holder.logo;


        CardView restaurant = holder.restaurant;

        int id_back = mContext.getResources().getIdentifier(mListaRestaurants.get(position).getBack_img_rest(), "drawable", mContext.getPackageName());
        int id_logo = mContext.getResources().getIdentifier(mListaRestaurants.get(position).getLogo_path(), "drawable", mContext.getPackageName());
        Drawable drawable_back = mContext.getResources().getDrawable(id_back);
        Drawable drawable_logo = mContext.getResources().getDrawable(id_logo);


        back_img.setImageDrawable(drawable_back);
        logo.setImageDrawable(drawable_logo);

        name.setText(mListaRestaurants.get(position).getName());
        time.setText(mListaRestaurants.get(position).getTime_distance());
        distance.setText(mListaRestaurants.get(position).getDistance());
        stars.setText(mListaRestaurants.get(position).getStars_review());
        resttipo.setText(mListaRestaurants.get(position).getResttipo());

        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(mContext.getApplicationContext(),"Clicou no Restaurante: "+mListaRestaurants.get(position).getName(),Toast.LENGTH_SHORT).show();

                MenuRestaurant_Fragment newFragment = new MenuRestaurant_Fragment();
                FragmentManager manager = ((MainActivity)mContext).getSupportFragmentManager();

                Bundle args = new Bundle();
                args.putString("nomeRestaurant", mListaRestaurants.get(position).getName() );
                newFragment.setArguments(args);

                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return mListaRestaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, time_dis, stars_review, distance, resttipo;
        public ImageView background_img, logo;
        public CardView restaurant;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.rest_name);
            time_dis = (TextView) itemView.findViewById(R.id.rest_time);
            stars_review = (TextView) itemView.findViewById(R.id.stars_num);
            distance = (TextView) itemView.findViewById(R.id.rest_distance);
            background_img = itemView.findViewById(R.id.back_img_rest);
            logo = itemView.findViewById(R.id.rest_logo);
            resttipo = (TextView) itemView.findViewById(R.id.resttipo);
            restaurant = itemView.findViewById(R.id.restaurant);

            //itemView.setOnClickListener(new View.OnClickListener(){
            /*restaurant.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            int pos = getAdapterPosition();
            //Toast.makeText(mContext.getApplicationContext(), "Clicou no Restaurante: " + mListaRestaurants.get(position).getName(), Toast.LENGTH_SHORT).show();
            if (listener != null && pos != RecyclerView.NO_POSITION) {   listener.onItemClick(pos);  }
            });*/
        }
    }

    /*public interface OnItemClickListener {
        void onItemClick(Integer pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }*/

}
