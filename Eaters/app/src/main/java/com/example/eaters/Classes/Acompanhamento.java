package com.example.eaters.Classes;

public class Acompanhamento {


    private String id;
    private String name;
    private String quantidade;
    private String back_img_food;

    public Acompanhamento(String id, String name, String quantidade, String back_img_food) {
        this.id = id;
        this.name = name;
        this.quantidade = quantidade;
        this.back_img_food = back_img_food;
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

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getBack_img_food() {
        return back_img_food;
    }

    public void setBack_img_food(String back_img_food) {
        this.back_img_food = back_img_food;
    }
}
