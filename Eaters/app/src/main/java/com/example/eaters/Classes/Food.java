package com.example.eaters.Classes;

public class Food {

    private String id;
    private String name;
    private String description;
    private String ingredients;
    private String back_img_food;
    private String rest_logo;
    private float price;
    private float stars_review;

    public Food(String id, String name, String description, String ingredients, String back_img_food, String rest_logo, float price, float stars_review) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.back_img_food = back_img_food;
        this.rest_logo = rest_logo;
        this.price = price;
        this.stars_review = stars_review;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getStars_review() {
        return stars_review;
    }

    public void setStars_review(float stars_review) {
        this.stars_review = stars_review;
    }
}
