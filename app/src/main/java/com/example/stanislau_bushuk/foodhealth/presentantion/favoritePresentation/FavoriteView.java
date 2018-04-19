package com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation;

import com.arellomobile.mvp.MvpView;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

import io.realm.RealmResults;

public interface FavoriteView extends MvpView {
    void showList(RealmResults<Recipe> recipes);
}
