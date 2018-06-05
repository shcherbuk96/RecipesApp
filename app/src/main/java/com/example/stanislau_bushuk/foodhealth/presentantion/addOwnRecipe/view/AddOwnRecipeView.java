package com.example.stanislau_bushuk.foodhealth.presentantion.addOwnRecipe.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface AddOwnRecipeView extends MvpView {
    void showImage(String imageUrl);

    @StateStrategyType(SkipStrategy.class)
    void fixView(final int viewFail);
}
