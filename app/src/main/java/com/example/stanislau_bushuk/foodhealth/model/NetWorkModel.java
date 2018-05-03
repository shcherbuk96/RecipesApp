package com.example.stanislau_bushuk.foodhealth.model;


import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.api.IAPI;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.presenters.SearchPresenter;

import java.util.ArrayList;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;
import io.realm.RealmResults;


public class NetWorkModel {

    @Inject
    IAPI iapi;

    private Realm realm;

    private CallBackSearchPresenter callBackSearchPresenter;

    public NetWorkModel() {
        App.getAppComponent().inject(this);
        realm = Realm.getDefaultInstance();
    }

    public void setCallBackSearch(final SearchPresenter presenter) {
        callBackSearchPresenter = presenter;
    }

    public void getResponse(final String recipeName, final int from, final boolean update) {
        final Observable<Recipes> observable = iapi.getRecipeWithName(recipeName, Constants.APP_ID, Constants.APP_KEY, String.valueOf(from), String.valueOf(from + Constants.ITEMS_IN_PAGE));
        callBackSearchPresenter.call(observable, update, false);
    }

    public void getRandomRecipe(final boolean update) {
        final int random = (int) (Math.random() * Constants.RABDON);
        final Observable<Recipes> observable = iapi.getRandomRecipe(Constants.RANDOM_RECIPE, Constants.APP_ID, Constants.APP_KEY, String.valueOf(random), String.valueOf(random + Constants.ITEMS_IN_PAGE), Constants.CALLORIES);
        callBackSearchPresenter.call(observable, update, false);
    }

    public boolean checkRealmIsEmpty() {
        final RealmResults<Recipe> recipes = realm.where(Recipe.class).findAll();
        return recipes.isEmpty();
    }

    public void getRandomData(final boolean update) {
        final RealmResults<Recipe> recipes = realm.where(Recipe.class).findAll();
        final ArrayList<Hits> list = new ArrayList<>();

        if (recipes != null) {
            for (final Recipe recipe : recipes) {
                list.add(new Hits(recipe));
            }

            if (list.size() > Constants.SEARCH_RANDOM_COUNT) {
                final int random = new Random().nextInt(list.size() - Constants.SEARCH_RANDOM_COUNT);
                callBackSearchPresenter.call(Observable.just(new Recipes(new ArrayList<>(list.subList(random, random + Constants.SEARCH_RANDOM_COUNT)), 1)).subscribeOn(AndroidSchedulers.mainThread()), update, true);
            } else {
                callBackSearchPresenter.call(Observable.just(new Recipes(list, 1)).subscribeOn(AndroidSchedulers.mainThread()), update, true);
            }

        }
    }

}
