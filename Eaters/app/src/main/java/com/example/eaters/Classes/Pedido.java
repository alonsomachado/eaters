package com.example.eaters.Classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Pedido implements Parcelable {

    private Integer id;
    private List<Food> mPedidoFood;
    private List<Integer> mPedidoFoodQnt;
    private List<Adicional> mPedidoAdicional;
    private List<Integer> mPedidoAdicionalQnt;

    public Pedido(Integer id, List<Food> mPedidoFood, List<Integer> mPedidoFoodQnt, List<Adicional> mPedidoAdicional, List<Integer> mPedidoAcompanhamentoQnt) {
        this.id = id;
        this.mPedidoFood = mPedidoFood;
        this.mPedidoFoodQnt = mPedidoFoodQnt;
        this.mPedidoAdicional = mPedidoAdicional;
        this.mPedidoAdicionalQnt = mPedidoAcompanhamentoQnt;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) {    this.id = id;  }

    public List<Food> getmPedidoFood() {   return mPedidoFood;  }

    public void setmPedidoFood(List<Food> mPedidoFood) {   this.mPedidoFood = mPedidoFood;  }

    public List<Integer> getmPedidoFoodQnt() { return mPedidoFoodQnt;  }

    public void setmPedidoFoodQnt(List<Integer> mPedidoFoodQnt) {   this.mPedidoFoodQnt = mPedidoFoodQnt;  }

    public List<Adicional> getmPedidoAdicional() { return mPedidoAdicional;  }

    public void setmPedidoAdicional(List<Adicional> mPedidoAdicional) {   this.mPedidoAdicional = mPedidoAdicional;  }

    public List<Integer> getmPedidoAdicionalQnt() {  return mPedidoAdicionalQnt;  }

    public void setmPedidoAdicionalQnt(List<Integer> mPedidoAdicionalQnt) {  this.mPedidoAdicionalQnt = mPedidoAdicionalQnt;  }

    protected Pedido(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        mPedidoFood = in.createTypedArrayList(Food.CREATOR);
    }

    public static final Creator<Pedido> CREATOR = new Creator<Pedido>() {
        @Override
        public Pedido createFromParcel(Parcel in) {
            return new Pedido(in);
        }

        @Override
        public Pedido[] newArray(int size) {
            return new Pedido[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeTypedList(mPedidoFood);
    }
}
