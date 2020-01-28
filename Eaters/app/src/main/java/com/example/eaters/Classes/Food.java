package com.example.eaters.Classes;

public class Food {

    private String id;
    private String name;
    private String ingredientes;
    private String back_img_food;
    private String rest_logo;
    private float preco;
    private float nota;


    public Food(String id, String name, String ingredientes, String back_img_food, String rest_logo, float preco, float nota) {
        this.id = id;
        this.name = name;
        this.ingredientes = ingredientes;
        this.back_img_food = back_img_food;
        this.rest_logo = rest_logo;
        this.preco = preco;
        this.nota = nota;
    }


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

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getNota() { return nota;  }

    public void setNota(float nota) { this.nota = nota;  }
}
