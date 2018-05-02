package com.example.stanislau_bushuk.foodhealth.model.pojo;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Recipe extends RealmObject {

    @PrimaryKey
    private String uri;
    private String label;
    private String image;
    private String source;
    private String url;
    private String shareAs;
    private float yield;
    private RealmList<String> dietLabels;
    private RealmList<String> healthLabels;

    private RealmList<String> cautions;
    private RealmList<String> ingredientLines;
    private RealmList<Ingridients> ingredients;
    private float calories;
    private float totalWeight;
    private float totalTime;
    private TotalNutrients totalNutrients;
    private TotalDaily totalDaily;
    private boolean bookmarked;
    private boolean bought;

    private boolean isChecked;

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

    public float getYield() {
        return yield;
    }

    public RealmList<String> getDietLabels() {
        return dietLabels;
    }

    public RealmList<String> getHealthLabels() {
        return healthLabels;
    }

    public RealmList<String> getCautions() {
        return cautions;
    }

    public RealmList<String> getIngredientLines() {
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(final boolean checked) {
        isChecked = checked;
    }
}
