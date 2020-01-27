/*package com.example.eaters.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eaters.R;


public class Promotions_Adapter extends RecyclerView.Adapter<Promotions_Adapter.ViewHolder> {

    private Context mContext;
    private List<Division> mListaDivisions;
    public Promotions_Adapter(Context mContext, List<Division> mListaDivisions) {
        this.mContext = mContext;
        this.mListaDivisions = mListaDivisions;
    }

    @NonNull
    @Override
    public Promotions_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View divisionView = inflater.inflate(R.layout.item_division, parent, false);
        Promotions_Adapter.ViewHolder viewHolder = new Promotions_Adapter.ViewHolder(divisionView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Promotions_Adapter.ViewHolder holder, final int position) {
        final TextView name = holder.nameDivision;
        MaterialCardView item = holder.item;
        ImageView delete = holder.delete;

        final Bundle bundle = new Bundle();
        bundle.putString("divisionId", mListaDivisions.get(position).getId());

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
        name.setText(mListaDivisions.get(position).getName());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(mContext)
                        .setMessage("Remove division " + mListaDivisions.get(position).getName() + " ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                myRef.child(mListaDivisions.get(position).getId()).removeValue();


                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("Sensors");
                                Query getSensorsByDivisonId = myRef.orderByChild("divisionId").equalTo(mListaDivisions.get(position).getId());


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


    }

    @Override
    public int getItemCount() {
        return mListaDivisions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameDivision;
     //   public MaterialCardView item;
        public ImageView delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameDivision = (TextView) itemView.findViewById(R.id.item_name);
            item = itemView.findViewById(R.id.division_layout);
            delete = itemView.findViewById(R.id.delete_div);


        }
    }
}
*/