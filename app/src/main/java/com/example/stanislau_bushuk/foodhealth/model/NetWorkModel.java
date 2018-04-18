package com.example.stanislau_bushuk.foodhealth.model;


import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.api.IAPI;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.presenters.SearchPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;


public class NetWorkModel {

    @Inject
    IAPI iapi;

    private CallBackSearchPresenter callBackSearchPresenter;

    public NetWorkModel() {
        App.getAppComponent().inject(this);
    }

    public void setCallBackSearch(final SearchPresenter presenter) {
        callBackSearchPresenter = presenter;
    }

    public void getResponse(final String recipeName, final int from, final boolean update) {
        final Observable<Recipes> observable = iapi.getRecipeWithName(recipeName, Constants.APP_ID, Constants.APP_KEY, String.valueOf(from), String.valueOf(from + Constants.ITEMS_IN_PAGE));
        callBackSearchPresenter.call(observable, update, from);
    }

    public void getRandomRecipe(final boolean update) {
        final int random = (int) (Math.random() * Constants.RABDON);
        Timber.e("random " + random);
        final Observable<Recipes> observable = iapi.getRandomRecipe(Constants.RANDOM_RECIPE, Constants.APP_ID, Constants.APP_KEY, String.valueOf(random), String.valueOf(random + Constants.ITEMS_IN_PAGE), Constants.CALLORIES);
        callBackSearchPresenter.call(observable, update, random);
    }

    public void getRecipeFromUri() {
        final Observable<Recipe> observable = iapi.getRecipeWithUri(Constants.RANDOM_RECIPE, Constants.APP_ID, Constants.APP_KEY);
    }
}
