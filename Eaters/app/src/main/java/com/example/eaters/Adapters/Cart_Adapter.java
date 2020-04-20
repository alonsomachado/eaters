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

import com.example.eaters.Classes.Adicional;
import com.example.eaters.Classes.Food;
import com.example.eaters.R;

import java.util.List;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.ViewHolder> {
    private Context mContext;
    private List<Food> mListaFoodCart;
    private List<Adicional> mListaAdicionalCart;
    public Cart_Adapter(Context mContext, List<Food> mListaFood, List<Adicional> mListaAdicional ) {
        this.mContext = mContext;
        this.mListaFoodCart = mListaFood;
        this.mListaAdicionalCart = mListaAdicional;
    }

    @NonNull
    @Override
    public Cart_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View mCartView = inflater.inflate(R.layout.cart_item, parent, false);
        Cart_Adapter.ViewHolder viewHolder = new Cart_Adapter.ViewHolder(mCartView);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull Cart_Adapter.ViewHolder holder, final int position) {
        CardView cartitem = holder.cartitem;
        TextView name = holder.name;
        TextView quantidade = holder.quantidade;
        TextView preco = holder.preco;
        ImageView logo = holder.logo;
        ImageView minus = holder.minus;
        ImageView add = holder.add;
        ImageView delete = holder.delete;
        final Integer[] quant = {1}; //mListaFoodCart.get(position).setQuantidade();

        name.setText(mListaFoodCart.get(position).getName());

        int id_logo = mContext.getResources().getIdentifier(mListaFoodCart.get(position).getFood_img(), "drawable", mContext.getPackageName());
        Drawable drawable_logo = mContext.getResources().getDrawable(id_logo);
        logo.setImageDrawable(drawable_logo);

        quantidade.setText(String.valueOf(quant[0]));
        preco.setText(String.valueOf(mListaFoodCart.get(position).getPreco()) );

        minus.setVisibility(View.INVISIBLE);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( quant[0] > 1) { //Not Negative
                    Toast.makeText(mContext.getApplicationContext(),"Retirou um "+ mListaFoodCart.get(position).getName(),Toast.LENGTH_SHORT).show();
                    minus.setVisibility(View.VISIBLE);
                    quantidade.setVisibility(View.VISIBLE);
                    quant[0] = quant[0] - 1;
                    quantidade.setText(String.valueOf(quant[0]));
                    if(quant[0] == 1) {
                        minus.setVisibility(View.INVISIBLE);
                        delete.setVisibility(View.VISIBLE);
                    }

                }else {
                    //Toast.makeText(mContext.getApplicationContext(),"Erro! Tentou retirar acompanhamento "+mListaAcompanhamento.get(position).getName(),Toast.LENGTH_SHORT).show();
                    minus.setVisibility(View.INVISIBLE);
                    quantidade.setVisibility(View.INVISIBLE);
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quant[0] == 1) {
                    delete.setVisibility(View.VISIBLE);
                }
                quant[0] = quant[0] - 1;
                quantidade.setText(String.valueOf(quant[0]));
                if(quant[0] == 0) {
                    minus.setVisibility(View.INVISIBLE);
                    quantidade.setVisibility(View.INVISIBLE);
                    delete.setVisibility(View.INVISIBLE);
                    //add.setVisibility(View.INVISIBLE);
                }
                cartitem.setVisibility(View.INVISIBLE);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext.getApplicationContext(),"Adicionou um "+ mListaFoodCart.get(position).getName(),Toast.LENGTH_SHORT).show();
                quant[0] = quant[0] + 1;
                if(quant[0] == 1){
                    minus.setVisibility(View.INVISIBLE);
                    delete.setVisibility(View.VISIBLE);
                }else {
                    minus.setVisibility(View.VISIBLE);
                }
                quantidade.setText(String.valueOf(quant[0]));
                quantidade.setVisibility(View.VISIBLE);
                delete.setVisibility(View.INVISIBLE);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mListaFoodCart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, quantidade, preco;
        public ImageView logo, minus, add, delete;
        public CardView cartitem;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.cartitem_name);
            quantidade = (TextView) itemView.findViewById(R.id.cartitem_qnt);
            logo = itemView.findViewById(R.id.cartitem_icon);
            minus = itemView.findViewById(R.id.cartitem_minus);
            add = itemView.findViewById(R.id.cartitem_add);
            preco = (TextView) itemView.findViewById(R.id.cartitem_price);
            delete = itemView.findViewById(R.id.cartitem_delete);

            cartitem = itemView.findViewById(R.id.cartitem);

        }
    }
}

