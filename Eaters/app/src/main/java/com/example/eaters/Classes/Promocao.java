package com.example.eaters.Classes;

import java.util.List;

public class Promocao {

    private String id;
    private String name;
    private String back_img_promo;
    private String description;

    public Promocao(String id,String name,String back_img_promo,String description){
        this.id = id;
        this.name = name;
        this.description = description;
        this.back_img_promo = back_img_promo;

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBack_img_promo() {
        return back_img_promo;
    }

    public void setBack_img_promo(String back_img_promo) {
        this.back_img_promo = back_img_promo;
    }
}
