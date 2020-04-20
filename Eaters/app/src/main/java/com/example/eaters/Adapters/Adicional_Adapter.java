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
import com.example.eaters.R;

import java.util.List;

public class Adicional_Adapter extends RecyclerView.Adapter<Adicional_Adapter.ViewHolder> {
    private Context mContext;
    private List<Adicional> mListaAdicional;
    public Adicional_Adapter(Context mContext, List<Adicional> mListaDivisions) {
        this.mContext = mContext;
        this.mListaAdicional = mListaDivisions;
    }

    @NonNull
    @Override
    public Adicional_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View mAcoView = inflater.inflate(R.layout.acompanhamento_item, parent, false);
        Adicional_Adapter.ViewHolder viewHolder = new Adicional_Adapter.ViewHolder(mAcoView);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull Adicional_Adapter.ViewHolder holder, final int position) {
        CardView acompanhamento = holder.adicional;
        TextView name = holder.name;
        TextView quantidade = holder.quantidade;
        TextView preco = holder.preco;
        ImageView logo = holder.logo;
        ImageView minus = holder.minus;
        ImageView add = holder.add;
        final Integer[] quant = {0}; //mListaAcompanhamento.get(position).getQuantidade();

        name.setText(mListaAdicional.get(position).getName());

        int id_logo = mContext.getResources().getIdentifier(mListaAdicional.get(position).getBack_img_food(), "drawable", mContext.getPackageName());
        Drawable drawable_logo = mContext.getResources().getDrawable(id_logo);
        logo.setImageDrawable(drawable_logo);

        quantidade.setText(String.valueOf(quant[0]));
        preco.setText(String.valueOf(mListaAdicional.get(position).getPreco()) );

        minus.setVisibility(View.INVISIBLE);
        quantidade.setVisibility(View.INVISIBLE);

        minus.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 if( quant[0] >= 1) { //Not Negative
                     Toast.makeText(mContext.getApplicationContext(),"Retirou um "+ mListaAdicional.get(position).getName(),Toast.LENGTH_SHORT).show();
                     minus.setVisibility(View.VISIBLE);
                     quantidade.setVisibility(View.VISIBLE);
                     quant[0] = quant[0] - 1;
                     quantidade.setText(String.valueOf(quant[0]));
                     if(quant[0] == 0) {
                         minus.setVisibility(View.INVISIBLE);
                         quantidade.setVisibility(View.INVISIBLE);
                     }

                 }else {
                     //Toast.makeText(mContext.getApplicationContext(),"Erro! Tentou retirar acompanhamento "+mListaAcompanhamento.get(position).getName(),Toast.LENGTH_SHORT).show();
                     minus.setVisibility(View.INVISIBLE);
                     quantidade.setVisibility(View.INVISIBLE);
                 }
              }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext.getApplicationContext(),"Adicionou um "+ mListaAdicional.get(position).getName(),Toast.LENGTH_SHORT).show();
                minus.setVisibility(View.VISIBLE);
                quantidade.setVisibility(View.VISIBLE);
                quant[0] = quant[0] + 1;
                quantidade.setText(String.valueOf(quant[0]));

            }
        });


        /*acompanhamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext.getApplicationContext(),"Clicou no Acompanhamento: "+mListaAcompanhamento.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return mListaAdicional.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, quantidade, preco;
        public ImageView logo, minus, add;
        public CardView adicional;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.adicionalname);
            quantidade = (TextView) itemView.findViewById(R.id.adicionalqnt);
            logo = itemView.findViewById(R.id.adicionalicon);
            minus = itemView.findViewById(R.id.adicionalminus);
            add = itemView.findViewById(R.id.adicionaladd);
            preco = (TextView) itemView.findViewById(R.id.adicionalpreco);

            adicional = itemView.findViewById(R.id.itemAdicional);

        }
    }
}

