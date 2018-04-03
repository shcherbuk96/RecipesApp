package com.example.stanislau_bushuk.foodhealth.Presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.API.IAPI;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Modul.Api;
import com.example.stanislau_bushuk.foodhealth.View.ViewSearch;

import javax.inject.Inject;

@InjectViewState
public class SearchPresenter extends MvpPresenter<ViewSearch> {

    @Inject
    IAPI api;

    public SearchPresenter(){
        App.getAppComponent().inject(this);
    }
}
