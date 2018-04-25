package com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation;

import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

import io.reactivex.Observable;
import io.realm.RealmResults;

public interface CallBackRealmData {
    void getDataRealm(final Observable<RealmResults<Recipe>> observable);
}
