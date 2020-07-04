package com.alonsomachado.eaters.Model;

public class Adicional {


    private String id;
    private String name;
    private Integer preco;
    private String back_img_food;

    public Adicional(String id, String name, Integer preco, String back_img_food) {
        this.id = id;
        this.name = name;
        this.preco = preco;
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

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
    }

    public String getBack_img_food() {
        return back_img_food;
    }

    public void setBack_img_food(String back_img_food) {
        this.back_img_food = back_img_food;
    }
}
