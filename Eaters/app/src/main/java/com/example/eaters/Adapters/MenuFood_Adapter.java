package com.example.eaters.Adapters;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.eaters.Activities.MainActivity;
import com.example.eaters.Classes.Food;
import com.example.eaters.Fragments.ItemPedido_Fragment;
import com.example.eaters.R;

import java.util.List;


public class MenuFood_Adapter extends RecyclerView.Adapter<MenuFood_Adapter.ViewHolder> {

    private Context mContext;
    private List<Food> mListaFood;
    public MenuFood_Adapter(Context mContext, List<Food> mListaFood) {
        this.mContext = mContext;
        this.mListaFood = mListaFood;
    }

    @NonNull
    @Override
    public MenuFood_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View fooditemView = inflater.inflate(R.layout.food_item, parent, false);
        MenuFood_Adapter.ViewHolder viewHolder = new MenuFood_Adapter.ViewHolder(fooditemView);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull MenuFood_Adapter.ViewHolder holder, final int position) {
        TextView name = holder.name;
        TextView preco = holder.preco;
        TextView ingredientes = holder.ingredientes;
        TextView nota = holder.nota;
        //ImageView back_img = holder.background_img;
        CardView itemfood = holder.itemfood;

        /*int id_back = mContext.getResources().getIdentifier(mListaFood.get(position).getBack_img_food(), "drawable", mContext.getPackageName());

        Drawable drawable_back = mContext.getResources().getDrawable(id_back);
        if (drawable_back != null) {
            back_img.setImageDrawable(drawable_back);
        }*/

        name.setText(mListaFood.get(position).getName());
        preco.setText(mListaFood.get(position).getPreco()); //mListaFood.get(position).getPreco()
        ingredientes.setText(mListaFood.get(position).getIngredientes());
        nota.setText(mListaFood.get(position).getNota());

        itemfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext.getApplicationContext(),"Clicou na Comida: "+mListaFood.get(position).getName(),Toast.LENGTH_SHORT).show();

                ItemPedido_Fragment newFragment = new ItemPedido_Fragment();
                Food mFood = mListaFood.get(position);

                Bundle args = new Bundle();
                args.putString("nomePizza", mListaFood.get(position).getName());
                args.putParcelable("Food", mFood);
                newFragment.setArguments(args);

                FragmentManager manager = ((MainActivity)mContext).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

    }


    @Override
    public int getItemCount() {
        return mListaFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, preco, nota, ingredientes;
        //public ImageView background_img;
        public CardView itemfood;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.food_name);
            preco = (TextView) itemView.findViewById(R.id.food_preco);
            nota = (TextView) itemView.findViewById(R.id.food_nota);
            ingredientes = (TextView) itemView.findViewById(R.id.food_ingredients);
           //background_img = itemView.findViewById(R.id.back_img_food);

            itemfood = itemView.findViewById(R.id.itemfood);


        }
    }
}
