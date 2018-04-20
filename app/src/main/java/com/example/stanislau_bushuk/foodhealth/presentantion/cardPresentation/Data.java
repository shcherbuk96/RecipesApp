package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import com.example.stanislau_bushuk.foodhealth.model.pojo.ItemTotal;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.TotalDaily;
import com.example.stanislau_bushuk.foodhealth.model.pojo.TotalNutrients;

import java.util.ArrayList;

public class Data {
    private ItemTotal fat;
    private ItemTotal prot;
    private ItemTotal chocdf;
    private ItemTotal ENERC_KCAL;
    private float yield;
    private float calories;
    private String label;
    private String image;
    private ArrayList<String> ingredientLines;

    public static Builder newBuilder() {
        return new Data().new Builder();
    }

    public ArrayList<String> getIngredientLines() {
        return ingredientLines;
    }

    public String getImage() {
        return image;
    }

    public String getLabel() {
        return label;
    }

    public ItemTotal getENERC_KCAL() {
        return ENERC_KCAL;
    }

    public float getYield() {
        return yield;
    }

    public float getCalories() {
        return calories;
    }

    public ItemTotal getFat() {
        return fat;
    }

    public ItemTotal getProt() {
        return prot;
    }

    public ItemTotal getChocdf() {
        return chocdf;
    }

    public class Builder {

        private Builder() {

        }

        public Builder setENERC_KCAL(final TotalDaily totalDaily) {
            ENERC_KCAL = totalDaily.getENERC_KCAL() == null ? new ItemTotal() : totalDaily.getENERC_KCAL();

            return this;
        }

        public Builder setLabel(final Recipe recipe) {
            label = recipe.getLabel() == null ? "empty" : recipe.getLabel();

            return this;
        }

        public Builder setIngridients(final Recipe recipe) {
            ingredientLines = recipe.getIngredientLines();

            return this;
        }
        public Builder setImage(final Recipe recipe) {
            image = recipe.getImage();

            return this;
        }

        public Builder setCalories(final Recipe recipe) {
            calories = recipe.getCalories();

            return this;
        }

        public Builder setYield(final Recipe recipe) {
            yield = recipe.getYield();

            return this;
        }

        public Builder setFat(final TotalNutrients totalNutrients) {
            fat = totalNutrients.getFAT() == null ? new ItemTotal() : totalNutrients.getFAT();

            return this;
        }

        public Builder setProt(final TotalNutrients totalNutrients) {
            prot = totalNutrients.getPROCNT() == null ? new ItemTotal() : totalNutrients.getPROCNT();

            return this;
        }

        public Builder setChocdf(final TotalNutrients totalNutrients) {
            chocdf = totalNutrients.getCHOCDF() == null ? new ItemTotal() : totalNutrients.getCHOCDF();

            return this;
        }

        public Data build() {
            return Data.this;
        }
    }
}
