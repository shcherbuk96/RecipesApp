package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardPresenter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;

public class CardNetWorkModel {
    private Realm realm;
    private CallBackCardPresenter callBackCardPresenter;

    public CardNetWorkModel() {
        realm = Realm.getDefaultInstance();
    }

    public void setCallBackCard(final CardPresenter cardPresenter) {
        callBackCardPresenter = cardPresenter;
    }

    public void getRecipeFromRealmUri(final String uri) {
        final Observable r = realm.where(Recipe.class).equalTo("uri", uri).findFirst().asFlowable().cast(Recipe.class).toObservable();
        if (r != null) {
            callBackCardPresenter.call(r.subscribeOn(AndroidSchedulers.mainThread()));
        }
    }
}
