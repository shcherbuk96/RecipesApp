package com.example.stanislau_bushuk.foodhealth.Model;

import com.example.stanislau_bushuk.foodhealth.API.IAPI;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Recipes;
import com.example.stanislau_bushuk.foodhealth.Presenters.SearchPresenter;

import javax.inject.Inject;

import io.reactivex.Observable;

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
        Observable<Recipes> observable = iapi.getJson("search?q="+recipeName+"&app_id=8fe07cd3&app_key=d0f2fdfa54e4a68a2f8d96a0e34a7658&from="+from+"&to="+from+10);
        callBackSearchPresenter.call(observable);
    }
}
