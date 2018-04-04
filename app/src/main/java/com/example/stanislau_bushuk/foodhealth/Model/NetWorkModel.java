package com.example.stanislau_bushuk.foodhealth.Model;

import com.example.stanislau_bushuk.foodhealth.API.IAPI;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Recipes;
import com.example.stanislau_bushuk.foodhealth.Presenters.SearchPresenter;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.example.stanislau_bushuk.foodhealth.Constats.APP_ID;
import static com.example.stanislau_bushuk.foodhealth.Constats.APP_KEY;

public class NetWorkModel {

    @Inject
    IAPI iapi;
    private CallBackSearchPresenter callBackSearchPresenter;

    public NetWorkModel() {
        App.getAppComponent().inject(this);
    }

    public void setCallBack(SearchPresenter presenter) {
        callBackSearchPresenter = presenter;
        getResponse("chiken",0);
    }

    public void getResponse(String recipeName, int from){
        Observable<Recipes> observable = iapi.getJson("search?q="+recipeName+"&app_id="+APP_ID+"&app_key="+APP_KEY+"&from="+from+"&to="+from+10);
        callBackSearchPresenter.call(observable);
    }
}
