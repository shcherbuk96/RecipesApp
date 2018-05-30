package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.CallBackRealmData;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoritePresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmModel {

    private final Realm realm;
    private CallBackRealmData callBackRealmData;

    public RealmModel() {
        realm = Realm.getDefaultInstance();
    }

    public void setCallBack(final FavoritePresenter favoritePresenter) {
        callBackRealmData = favoritePresenter;
    }

    public void addToFavorite(final Recipe recipe) {
        realm.beginTransaction();
        recipe.setChecked(true);
        realm.insertOrUpdate(recipe);
        realm.commitTransaction();
    }

    public void addToRealm(final Recipe recipe) {
        realm.beginTransaction();
        realm.insertOrUpdate(recipe);
        realm.commitTransaction();
    }

    public void deleteFromFavorite(final Recipe recipe) {
        realm.beginTransaction();
        recipe.setChecked(false);
        realm.insertOrUpdate(recipe);
        realm.commitTransaction();
    }


    public void getData() {
        final RealmResults<Recipe> recipes = realm.where(Recipe.class).equalTo("isChecked", true).findAll();

        if (recipes != null) {
            final Observable<RealmResults<Recipe>> observable = recipes.asFlowable().toObservable();
            callBackRealmData.getDataRealm(observable.subscribeOn(AndroidSchedulers.mainThread()));
        }
    }

    public List<Recipe> getRecipesRealm(){
        final List<Recipe> recipes = realm.where(Recipe.class).equalTo("isChecked",true).findAll();
        return recipes;
    }


    public boolean getIsChecked(final Recipe recipe) {
        final Recipe r = realm.where(Recipe.class).equalTo("uri", recipe.getUri()).findFirst();

        return r != null && r.isChecked();
    }

}
