package com.example.stanislau_bushuk.foodhealth.model;


import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constats;
import com.example.stanislau_bushuk.foodhealth.api.IAPI;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.presenters.SearchPresenter;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NetWorkModel {

    @Inject
    IAPI iapi;
    private CallBackSearchPresenter callBackSearchPresenter;

    public NetWorkModel() {
        App.getAppComponent().inject(this);
    }

    public void setCallBack(final SearchPresenter presenter) {
        callBackSearchPresenter = presenter;
        getResponse("chiken", 0);
    }

    public void getResponse(final String recipeName, final int from) {
        final Observable<Recipes> observable = iapi.getJson("chiken", Constats.APP_ID,Constats.APP_KEY,"0","10");
        callBackSearchPresenter.call(observable);
    }
}
