package com.example.stanislau_bushuk.foodhealth.model.pojo;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class OwnRecipe {

    private String recipeName;
    private String recipeInsruction;
    private String recipePhoto;
    private ArrayList<String> recipeIngridients;

    public OwnRecipe(final DataSnapshot ds) {

    }

    public OwnRecipe() {
    }
}
