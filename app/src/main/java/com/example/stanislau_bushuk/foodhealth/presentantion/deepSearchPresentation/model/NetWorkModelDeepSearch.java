package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.model;

import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.api.IAPI;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.presenters.DeepSearchPresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NetWorkModelDeepSearch {

    @Inject
    IAPI iapi;

    private DeepSearchPresenter presenter;

    public NetWorkModelDeepSearch() {
        App.getAppComponent().inject(this);
    }

    public void setCallBack(final DeepSearchPresenter presenter) {
        this.presenter = presenter;
    }

    public void getRecipeFilter(final String calories, final int from, final Map<String, String> query) {
        final Observable<Recipes> observable = iapi.getRecipeFilter(Constants.RANDOM_RECIPE, Constants.APP_ID, Constants.APP_KEY,
                String.valueOf(from), String.valueOf(from + Constants.ITEMS_IN_PAGE), calories, query);
        presenter.callBack(observable);
    }

    public void getRecipeFilterCountIngredients(final String calories, final int from, final Map<String, String> query, final String countIngredients) {
        final Observable<Recipes> observable = iapi.getRecipeFilterCountIngredients(Constants.RANDOM_RECIPE, Constants.APP_ID, Constants.APP_KEY,
                String.valueOf(from), String.valueOf(from + Constants.ITEMS_IN_PAGE), calories, countIngredients, query);
        presenter.callBack(observable);
    }

}
