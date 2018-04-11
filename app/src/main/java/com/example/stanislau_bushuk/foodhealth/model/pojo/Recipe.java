package com.example.stanislau_bushuk.foodhealth.model.pojo;

import com.example.stanislau_bushuk.foodhealth.model.pojo.lists.Digets;

import java.util.ArrayList;

public class Recipe {

    private String uri;
    private String label;
    private String image;
    private String source;
    private String url;
    private String shareAs;
    private int yield;
    private ArrayList dietLabels;
    private ArrayList healthLabels;

    private ArrayList cautions;
    private ArrayList ingredientLines;
    private ArrayList ingredients;
    private float calories;
    private float totalWeight;
    private int totalTime;
    //totalNutrients	{…}
    //totalDaily	{…}
    private ArrayList<Digets> digest;
    private boolean bookmarked;
    private boolean bought;

    public String getUri() {
        return uri;
    }

    public String getLabel() {
        return label;
    }

    public String getImage() {
        return image;
    }

    public String getSource() {
        return source;
    }

    public String getUrl() {
        return url;
    }

    public String getShareAs() {
        return shareAs;
    }

    public int getYield() {
        return yield;
    }

    public ArrayList getDietLabels() {
        return dietLabels;
    }

    public ArrayList getHealthLabels() {
        return healthLabels;
    }

    public ArrayList getCautions() {
        return cautions;
    }

    public ArrayList getIngredientLines() {
        return ingredientLines;
    }

    public ArrayList getIngredients() {
        return ingredients;
    }

    public float getCalories() {
        return calories;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public ArrayList getDigest() {
        return digest;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public boolean isBought() {
        return bought;
    }
}
