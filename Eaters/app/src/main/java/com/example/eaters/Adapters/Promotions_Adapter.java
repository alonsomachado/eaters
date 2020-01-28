package com.example.eaters.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eaters.Classes.Promocao;
import com.example.eaters.R;
import java.util.List;


public class Promotions_Adapter extends RecyclerView.Adapter<Promotions_Adapter.ViewHolder> {

    private Context mContext;
    private List<Promocao> mListaPromocao;

    public Promotions_Adapter(Context mContext, List<Promocao> mListaDivisions) {
        this.mContext = mContext;
        this.mListaPromocao = mListaDivisions;
    }

    @NonNull
    @Override
    public Promotions_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View promoView = inflater.inflate(R.layout.promocao_item, parent, false);
        Promotions_Adapter.ViewHolder viewHolder = new Promotions_Adapter.ViewHolder(promoView);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull Promotions_Adapter.ViewHolder holder, final int position) {
        //MaterialCardView itemPromo = holder.itemPromo;
        CardView itemPromo = holder.itemPromo;
        TextView name = holder.name;
        TextView description = holder.description;
        ImageView back_img = holder.background_img;


        int id_back = mContext.getResources().getIdentifier(mListaPromocao.get(position).getBack_img_promo(), "drawable", mContext.getPackageName());
        Drawable drawable_back = mContext.getResources().getDrawable(id_back);

        back_img.setImageDrawable(drawable_back);

        name.setText(mListaPromocao.get(position).getName());
        description.setText(mListaPromocao.get(position).getDescription());

        itemPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                FragmentManager fragmentManager = ((Inicial_Activity) mContext).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                SensorsOfDivision_fragment fragment = new SensorsOfDivision_fragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();*/
                Toast.makeText(mContext.getApplicationContext(),"Clicou na Promocao"+mListaPromocao.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    /*
    @Override
    public void onBindViewHolder(@NonNull Promotions_Adapter.ViewHolder holder, final int position) {

        final TextView name = holder.nameDivision;
        MaterialCardView item = holder.item;
        ImageView delete = holder.delete;

        final Bundle bundle = new Bundle();
        bundle.putString("divisionId", mListaPromocao.get(position).getId());

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = ((Inicial_Activity) mContext).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                SensorsOfDivision_fragment fragment = new SensorsOfDivision_fragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });
        name.setText(mListaPromocao.get(position).getName());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(mContext)
                        .setMessage("Remove division " + mListaPromocao.get(position).getName() + " ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                myRef.child(mListaPromocao.get(position).getId()).removeValue();


                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("Sensors");
                                Query getSensorsByDivisonId = myRef.orderByChild("divisionId").equalTo(mListaPromocao.get(position).getId());


                                getSensorsByDivisonId.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            for (DataSnapshot divisions : dataSnapshot.getChildren()) {
                                                refSensors.child(divisions.getValue(Sensor.class).getIdentifier()).removeValue();
                                                //   list_divisions.add(divisions.getValue(Door.class).getnSensorClass());
                                            }

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                                FragmentManager fragmentManager = ((Inicial_Activity) mContext).getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.addToBackStack(null);
                                Home_Fragment fragment = new Home_Fragment();
                                fragment.setArguments(bundle);
                                fragmentTransaction.replace(R.id.fragment_container, fragment);
                                fragmentTransaction.commit();

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });


    }*/

    @Override
    public int getItemCount() {
        return mListaPromocao.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

     /*   public TextView nameDivision;
     //   public MaterialCardView item;
        public ImageView delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameDivision = (TextView) itemView.findViewById(R.id.item_name);
            item = itemView.findViewById(R.id.division_layout);
            delete = itemView.findViewById(R.id.delete_div);


        }
    }*/

        public TextView name, description;
        public ImageView background_img;
        public CardView itemPromo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.promo_name);
            description = (TextView) itemView.findViewById(R.id.promo_description);
            background_img = itemView.findViewById(R.id.back_img_promo);
            itemPromo = itemView.findViewById(R.id.itemPromo);


        }
    }
}
