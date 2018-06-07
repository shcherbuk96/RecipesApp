package com.example.stanislau_bushuk.foodhealth.model.pojo;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.List;

public class OwnRecipe {

    private String recipeName;
    private String recipeInstruction;
    private String recipePhoto;
    private ArrayList<String> recipeIngridients;

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(final String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeInstruction() {
        return recipeInstruction;
    }

    public void setRecipeInstruction(final String recipeInstruction) {
        this.recipeInstruction = recipeInstruction;
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

        this.recipeIngridients = new ArrayList<>();


        recipeIngridients.addAll(ds.child("recipeIngridients").getValue(ArrayList.class));
        this.recipeInstruction = ds.child("recipeInstruction").getValue(String.class);
    }

    public OwnRecipe(final String recipeName, final String recipeInstruction, final String recipePhoto, final ArrayList<String> recipeIngridients) {
        this.recipeName = recipeName;
        this.recipeInstruction = recipeInstruction;
        this.recipePhoto = recipePhoto;
        this.recipeIngridients = recipeIngridients;
    }

    public OwnRecipe() {
    }

    GenericTypeIndicator<ArrayList<OwnRecipe>> t = new GenericTypeIndicator<ArrayList<OwnRecipe>>() {};

}


