package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.api.IAPI;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardPresenter;

import javax.inject.Inject;

public class CardNetWorkModel {
    @Inject
    IAPI iapi;

    private CallBackCardPresenter callBackCardPresenter;

    public CardNetWorkModel() {
        App.getAppComponent().inject(this);
    }

    public void setCallBackCard(final CardPresenter cardPresenter) {
        callBackCardPresenter = cardPresenter;
    }

    public void getRecipeFromUri(final String uri) {
        callBackCardPresenter.call(iapi.getRecipeWithUri(uri, Constants.APP_ID, Constants.APP_KEY));
    }
}
