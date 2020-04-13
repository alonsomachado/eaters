package com.example.eaters.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {

    private String id;
    private String name;
    private String ingredientes;
    private String food_img;
    private String preco;
    private String nota;
    private String tipo;

    public Food(String id, String name, String ingredientes, String food_img, String preco, String nota, String tipo) {
        this.id = id;
        this.name = name;
        this.ingredientes = ingredientes;
        this.food_img = food_img;
        this.preco = preco;
        this.nota = nota;
        this.tipo = tipo;
    }


    protected Food(Parcel in) {
        id = in.readString();
        name = in.readString();
        ingredientes = in.readString();
        food_img = in.readString();
        preco = in.readString();
        nota = in.readString();
        tipo = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public String getFood_img() {
        return food_img;
    }

    public void setFood_img(String food_img) {
        this.food_img = food_img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredients) {
        this.ingredientes = ingredients;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(ingredientes);
        dest.writeString(food_img);
        dest.writeString(preco);
        dest.writeString(nota);
        dest.writeString(tipo);
    }
}

