package com.alonsomachado.eaters.Adapters;

import android.content.Context;;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alonsomachado.eaters.Model.Food;
import com.example.eaters.R;

import java.util.List;


public class MenuFoodCategory_Adapter extends RecyclerView.Adapter<MenuFoodCategory_Adapter.ViewHolder> {

    private Context mContext;
    private List<Food> mListaMenuFoods;
    //private HashMap<String, Integer> mListaMenuTipos = new HashMap<String, Integer>();

    public MenuFoodCategory_Adapter(Context mContext, List<Food> mListaMenuFoods) {
        this.mContext = mContext;
        this.mListaMenuFoods = mListaMenuFoods;
    }

    @NonNull
    @Override
    public MenuFoodCategory_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View menuTipoView = inflater.inflate(R.layout.menufoodcategoria_item, parent, false);
        MenuFoodCategory_Adapter.ViewHolder viewHolder = new MenuFoodCategory_Adapter.ViewHolder(menuTipoView);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull MenuFoodCategory_Adapter.ViewHolder holder, final int position) {

        CardView itemMenuTipo = holder.itemMenuTipo;
        TextView tipo = holder.tipo;

        //Tentando Resolver Categoria de Comida no MENU
        //mListaMenuTipos.put( mListaMenuFoods.get(position).getTipo(), position );
        //tipo.setText(mListaMenuFoods.get(position).getTipo());

        tipo.setText(mListaMenuFoods.get(position).getTipo());

        itemMenuTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext.getApplicationContext(),"Clicou na Categoria de Comida: "+mListaMenuFoods.get(position).getTipo(),Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mListaMenuFoods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tipo;
        public CardView itemMenuTipo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tipo = (TextView) itemView.findViewById(R.id.menufoodcategoria);
            itemMenuTipo = itemView.findViewById(R.id.itemMenuTipo);


        }
    }
}