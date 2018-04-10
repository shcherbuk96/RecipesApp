package com.example.stanislau_bushuk.foodhealth.model;


import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.api.IAPI;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.presenters.SearchPresenter;

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

    public void setCallBack(final SearchPresenter presenter) {
        callBackSearchPresenter = presenter;
    }

    public void getResponse(final String recipeName, final int from) {
        final Observable<Recipes> observable = iapi.getRecipeWithName(recipeName, Constants.APP_ID, Constants.APP_KEY, String.valueOf(from), String.valueOf(from+10));
        callBackSearchPresenter.call(observable);
    }

    public void getRandomRecipe(){
        int random = (int) (Math.random() * 90);
        Timber.e("random "+random);
        final Observable<Recipes> observable = iapi.getRandomRecipe(" ", Constants.APP_ID, Constants.APP_KEY, String.valueOf(random), String.valueOf(random+10),"0-30000");
        callBackSearchPresenter.call(observable);
    }

    public void getRecipeFromUri(){
        final Observable<Recipe> observable = iapi.getRecipeWithUri(" ", Constants.APP_ID, Constants.APP_KEY);
    }
}
