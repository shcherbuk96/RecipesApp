package com.example.stanislau_bushuk.foodhealth.model.pojo;

import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<String> ingredientLines;
    private List<Ingridients> ingredients;
    private float calories;
    private float totalWeight;
    private float totalTime;
    private TotalNutrients totalNutrients;
    private TotalDaily totalDaily;
    private ArrayList digest;
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

    public ArrayList<String> getIngredientLines() {
        return ingredientLines;
    }

    public List getIngredients() {
        return ingredients;
    }

    public float getCalories() {
        return calories;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public float getTotalTime() {
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

    public TotalNutrients getTotalNutrients() {
        return totalNutrients;
    }

    public void setTotalNutrients(final TotalNutrients totalNutrients) {
        this.totalNutrients = totalNutrients;
    }

    public TotalDaily getTotalDaily() {
        return totalDaily;
    }

    public void setTotalDaily(final TotalDaily totalDaily) {
        this.totalDaily = totalDaily;
    }

}
