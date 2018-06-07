package com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.view;

import com.arellomobile.mvp.MvpView;
import com.example.stanislau_bushuk.foodhealth.model.pojo.OwnRecipe;

import java.util.List;

public interface OwnRecipesView extends MvpView {
    void showRecipes(List<OwnRecipe> ownRecipes);

    void addRecipe(OwnRecipe ownRecipe);
}
