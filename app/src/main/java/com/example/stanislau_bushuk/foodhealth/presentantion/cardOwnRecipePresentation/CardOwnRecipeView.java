package com.example.stanislau_bushuk.foodhealth.presentantion.cardOwnRecipePresentation;

import com.arellomobile.mvp.MvpView;
import com.example.stanislau_bushuk.foodhealth.model.pojo.OwnRecipe;

public interface CardOwnRecipeView extends MvpView {
    public void infoOwnRecipe(OwnRecipe ownRecipe);

    public void error(String error);
}
