package com.alonsomachado.eaters.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.example.eaters.R;
//import com.bumptech.glide.request.RequestOptions;


public class Search_Fragment extends Fragment {

    private View v;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_search, container, false);

        imageView = (ImageView) v.findViewById(R.id.vinhos_verdes_card);
        //final Key antiCollision = new StringSignature("*");
        /*public static RequestOptions signatureOf(@NonNull Key signature) {
            return new RequestOptions().signature(signature);
        }*/
        String url = "https://picsum.photos/200" ;
        try{
            Glide.with(this.getContext()).load(url).fitCenter().placeholder(R.drawable.eaters_logo).error(R.drawable.circle).fallback(R.drawable.eaters_logo)
                    //.apply( new RequestOptions().signature(new ObjectKey(Calendar.getInstance().getTime()))
                    .diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView); //.signature(antiCollision)
            Log.d("GlideImg","Tentando carregar IMG pelo GLIDE!");
        }catch (Exception e){
            Log.e("GlideImg",e.toString());
        }

        return v;

    }

}
