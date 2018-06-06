package com.example.stanislau_bushuk.foodhealth.model.pojo;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class OwnRecipe {

    private String recipeName;
    private String recipeInsruction;
    private String recipePhoto;
    private ArrayList<String> recipeIngridients;

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(final String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeInsruction() {
        return recipeInsruction;
    }

    public void setRecipeInsruction(final String recipeInsruction) {
        this.recipeInsruction = recipeInsruction;
    }

    public String getRecipePhoto() {
        return recipePhoto;
    }

    public void setRecipePhoto(final String recipePhoto) {
        this.recipePhoto = recipePhoto;
    }

    public ArrayList<String> getRecipeIngridients() {
        return recipeIngridients;
    }

    public void setRecipeIngridients(final ArrayList<String> recipeIngridients) {
        this.recipeIngridients = recipeIngridients;
    }

    public OwnRecipe(final DataSnapshot ds) {
        this.recipeName = ds.child("recipeName").getValue(String.class);
        this.recipePhoto = ds.child("recipePhoto").getValue(String.class);
        this.recipeIngridients = ds.child("recipeIngredients").getValue(ArrayList.class);
        this.recipeInsruction = ds.child("recipeInstruction").getValue(String.class);
    }

    public OwnRecipe(final String recipeName, final String recipeInsruction, final String recipePhoto, final ArrayList<String> recipeIngridients) {
        this.recipeName = recipeName;
        this.recipeInsruction = recipeInsruction;
        this.recipePhoto = recipePhoto;
        this.recipeIngridients = recipeIngridients;
    }

    public OwnRecipe() {
    }
}
