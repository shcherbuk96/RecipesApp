package com.example.stanislau_bushuk.foodhealth.Model.Pojo;

import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Lists.Cautions;
import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Lists.DietLabels;
import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Lists.Digets;
import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Lists.HealhLabels;
import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Lists.IngridientLines;
import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Lists.Ingridients;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Recipe extends RealmObject {

    private String uri;
    private String label;
    private String image;
    private String source;
    private String url;
    private String shareAs;
    private int yield;
    private RealmList<DietLabels> dietLabels;
    private RealmList<HealhLabels> healthLabels;

    private RealmList<Cautions> cautions;
    private RealmList<IngridientLines> ingredientLines;
    private RealmList<Ingridients> ingredients;
    private float calories;
    private float totalWeight;
    private int totalTime;
    //totalNutrients	{…}
    //totalDaily	{…}
    private RealmList<Digets> digest;
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

    public RealmList getDietLabels() {
        return dietLabels;
    }

    public RealmList getHealthLabels() {
        return healthLabels;
    }

    public RealmList getCautions() {
        return cautions;
    }

    public RealmList getIngredientLines() {
        return ingredientLines;
    }

    public RealmList getIngredients() {
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

    public RealmList getDigest() {
        return digest;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public boolean isBought() {
        return bought;
    }
}
