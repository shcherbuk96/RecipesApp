package com.example.stanislau_bushuk.foodhealth.presentantion.cardOwnRecipePresentation;

import com.example.stanislau_bushuk.foodhealth.model.pojo.OwnRecipe;

public interface CallBackCardOwnRecipePresenter {
    public void call(OwnRecipe ownRecipe);

    public void callError(String error);
}
