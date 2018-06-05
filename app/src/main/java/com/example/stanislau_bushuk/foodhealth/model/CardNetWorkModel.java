package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.api.IAPI;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

public class CardNetWorkModel {

    private final Realm realm;
    @Inject
    IAPI iapi;
    private CallBackCardPresenter callBackCardPresenter;

    public CardNetWorkModel() {
        App.getAppComponent().inject(this);
        realm = Realm.getDefaultInstance();
    }

    public void setCallBackCard(final CardPresenter cardPresenter) {
        callBackCardPresenter = cardPresenter;
    }

    public void getRecipeFromRealmUri(final String uri) {
        final Recipe recipe = realm.where(Recipe.class).equalTo("uri", uri).findFirst();

        if (recipe != null) {

            if (recipe.getIngredientLines().size() == 0) {
                final Observable<List<Recipe>> r = iapi.getRecipeWithUri(uri,
                        Constants.APP_ID, Constants.APP_KEY);
                callBackCardPresenter.callList(r.subscribeOn(Schedulers.io()));
            } else {
                final Observable<Recipe> observable = realm.where(Recipe.class).equalTo("uri", uri).findFirst()
                        .asFlowable().cast(Recipe.class).toObservable();

                if (observable != null) {
                    callBackCardPresenter.call(observable.subscribeOn(AndroidSchedulers.mainThread()));
                }
            }

        }

    }
}
