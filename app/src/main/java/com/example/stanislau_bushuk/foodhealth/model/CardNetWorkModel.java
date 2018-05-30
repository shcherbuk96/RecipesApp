package com.example.stanislau_bushuk.foodhealth.model;

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
import timber.log.Timber;

public class CardNetWorkModel {

    @Inject
    IAPI iapi;

    private final Realm realm;

    private CallBackCardPresenter callBackCardPresenter;

    public CardNetWorkModel() {
        realm = Realm.getDefaultInstance();
    }

    public void setCallBackCard(final CardPresenter cardPresenter) {
        callBackCardPresenter = cardPresenter;
    }

    public void getRecipeFromRealmUri(final String uri) {
        final Recipe recipe = realm.where(Recipe.class).equalTo("uri", uri).findFirst();

        if (recipe != null) {

            if (recipe.getIngredientLines().size() == 0) {
                final Observable<List<Recipe>> r = iapi.getRecipeWithUri(recipe.getUri(),
                        Constants.APP_ID, Constants.APP_KEY);
                Timber.e("OBSERVABLE ");
                callBackCardPresenter.callList(r.subscribeOn(Schedulers.io()));
            } else {
                final Observable<Recipe> r = realm.where(Recipe.class).equalTo("uri", uri).findFirst()
                        .asFlowable().cast(Recipe.class).toObservable();

                if (r != null) {
                    callBackCardPresenter.call(r.subscribeOn(AndroidSchedulers.mainThread()));
                }
            }

        }

    }
}
