package com.example.eaters.Classes;

import java.util.List;

public class Restaurant {

    private String id;
    private String name;
    private String description;
    private String logo_path;
    private String back_img_rest;
    private String time_distance;
    private String distance;
    private List<Food> food_list;
    private String stars_review;

    public Restaurant(String id, String name, String description, String logo_path, String back_img_rest, String time_distance, String distance, String stars_review) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logo_path = logo_path;
        this.back_img_rest = back_img_rest;
        this.time_distance = time_distance;
        this.distance = distance;
        this.stars_review = stars_review;
    }

    public Restaurant(String id, String name, String description, String logo_path, String back_img_rest, String time_distance, String distance, List<Food> food_list, String stars_review) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logo_path = logo_path;
        this.back_img_rest = back_img_rest;
        this.time_distance = time_distance;
        this.distance = distance;
        this.food_list = food_list;
        this.stars_review = stars_review;
    }

    public String getBack_img_rest() {
        return back_img_rest;
    }

    public void setBack_img_rest(String back_img_rest) {
        this.back_img_rest = back_img_rest;
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

    public String getLogo_path() {
        return logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public String getTime_distance() {
        return time_distance;
    }

    public void setTime_distance(String time_distance) {
        this.time_distance = time_distance;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<Food> getFood_list() {
        return food_list;
    }

    public void setFood_list(List<Food> food_list) {
        this.food_list = food_list;
    }

    public String getStars_review() {
        return stars_review;
    }

    public void setStars_review(String stars_review) {
        this.stars_review = stars_review;
    }
}
