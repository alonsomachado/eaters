package com.example.eaters.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eaters.Classes.Restaurant;
import com.example.eaters.R;

import java.util.List;

public class Favorites_Adapter extends RecyclerView.Adapter<Favorites_Adapter.ViewHolder> {

    private Context mContext;
    private List<Restaurant> mListaRestaurants;
    public Favorites_Adapter(Context mContext, List<Restaurant> mListaDivisions) {
        this.mContext = mContext;
        this.mListaRestaurants = mListaDivisions;
    }

    @NonNull
    @Override
    public Favorites_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View divisionView = inflater.inflate(R.layout.favorites_item, parent, false);
        Favorites_Adapter.ViewHolder viewHolder = new Favorites_Adapter.ViewHolder(divisionView);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull Favorites_Adapter.ViewHolder holder, final int position) {
        CardView favorit = holder.favorit;
        TextView name = holder.name;
        TextView nota = holder.nota;
        TextView resttipo = holder.resttipo;
        ImageView logo = holder.logo;

        int id_logo = mContext.getResources().getIdentifier(mListaRestaurants.get(position).getLogo_path(), "drawable", mContext.getPackageName());
        Drawable drawable_logo = mContext.getResources().getDrawable(id_logo);
        logo.setImageDrawable(drawable_logo);

        name.setText(mListaRestaurants.get(position).getName());
        resttipo.setText(mListaRestaurants.get(position).getResttipo());
        nota.setText(mListaRestaurants.get(position).getStars_review());

        favorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext.getApplicationContext(),"Clicou no Restaurante: "+mListaRestaurants.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }




    @Override
    public int getItemCount() {
        return mListaRestaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, resttipo, nota, distance;
        public ImageView logo;
        public CardView favorit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.fav_name);
            nota = (TextView) itemView.findViewById(R.id.fav_nota);
            resttipo = (TextView) itemView.findViewById(R.id.fav_tipo);
            logo = itemView.findViewById(R.id.logo_img_restfav);

            favorit = itemView.findViewById(R.id.favorit);



        }
    }
}

