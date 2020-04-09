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

import com.example.eaters.Classes.Acompanhamento;
import com.example.eaters.Classes.Restaurant;
import com.example.eaters.R;

import java.util.List;

public class Acompanhamento_Adapter extends RecyclerView.Adapter<Acompanhamento_Adapter.ViewHolder> {
    private Context mContext;
    private List<Acompanhamento> mListaAcompanhamento;
    public Acompanhamento_Adapter(Context mContext, List<Acompanhamento> mListaDivisions) {
        this.mContext = mContext;
        this.mListaAcompanhamento = mListaDivisions;
    }

    @NonNull
    @Override
    public Acompanhamento_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View mAcoView = inflater.inflate(R.layout.acompanhamento_item, parent, false);
        Acompanhamento_Adapter.ViewHolder viewHolder = new Acompanhamento_Adapter.ViewHolder(mAcoView);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull Acompanhamento_Adapter.ViewHolder holder, final int position) {
        CardView acompanhamento = holder.acompanhamento;
        TextView name = holder.name;
        TextView quantidade = holder.quantidade;
        ImageView logo = holder.logo;
        ImageView minus = holder.minus;
        ImageView add = holder.add;
        final Integer[] quant = {0}; //mListaAcompanhamento.get(position).getQuantidade();

        name.setText(mListaAcompanhamento.get(position).getName());

        int id_logo = mContext.getResources().getIdentifier(mListaAcompanhamento.get(position).getBack_img_food(), "drawable", mContext.getPackageName());
        Drawable drawable_logo = mContext.getResources().getDrawable(id_logo);
        logo.setImageDrawable(drawable_logo);

        quantidade.setText(String.valueOf(quant[0]));

        minus.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 if( quant[0] > 0 ) { //Not Negative
                     Toast.makeText(mContext.getApplicationContext(),"Retirou um "+mListaAcompanhamento.get(position).getName(),Toast.LENGTH_SHORT).show();
                     quant[0] = quant[0] - 1;
                     quantidade.setText(String.valueOf(quant[0]));

                 }else {
                     Toast.makeText(mContext.getApplicationContext(),"Erro! Tentou retirar acompanhamento "+mListaAcompanhamento.get(position).getName(),Toast.LENGTH_SHORT).show();
                 }
              }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext.getApplicationContext(),"Adicionou um "+mListaAcompanhamento.get(position).getName(),Toast.LENGTH_SHORT).show();

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
        return mListaAcompanhamento.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, quantidade;
        public ImageView logo, minus, add;
        public CardView acompanhamento;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.acompanhamentoname);
            quantidade = (TextView) itemView.findViewById(R.id.acompanhamentoqnt);
            logo = itemView.findViewById(R.id.acompanhamentoicon);
            minus = itemView.findViewById(R.id.acompanhamentominus);
            add = itemView.findViewById(R.id.acompanhamentoadd);

            acompanhamento = itemView.findViewById(R.id.itemAcompanhamento);

        }
    }
}

