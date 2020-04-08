package com.example.eaters.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {

    private String id;
    private String name;
    private String ingredientes;
    private String back_img_food;
    private String rest_logo;
    private String preco;
    private String nota;
    private String tipo;


    public Food(String id, String name, String ingredientes, String back_img_food, String rest_logo, String preco, String nota, String tipo) {
        this.id = id;
        this.name = name;
        this.ingredientes = ingredientes;
        this.back_img_food = back_img_food;
        this.rest_logo = rest_logo;
        this.preco = preco;
        this.nota = nota;
        this.tipo = tipo;
    }


    protected Food(Parcel in) {
        id = in.readString();
        name = in.readString();
        ingredientes = in.readString();
        back_img_food = in.readString();
        rest_logo = in.readString();
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

    public String getBack_img_food() {
        return back_img_food;
    }

    public void setBack_img_food(String back_img_food) {
        this.back_img_food = back_img_food;
    }

    public String getRest_logo() {
        return rest_logo;
    }

    public void setRest_logo(String rest_logo) {
        this.rest_logo = rest_logo;
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

    public String getNota() { return nota;  }

    public void setNota(String nota) { this.nota = nota;  }

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
        dest.writeString(back_img_food);
        dest.writeString(rest_logo);
        dest.writeString(preco);
        dest.writeString(nota);
        dest.writeString(tipo);
    }
}
