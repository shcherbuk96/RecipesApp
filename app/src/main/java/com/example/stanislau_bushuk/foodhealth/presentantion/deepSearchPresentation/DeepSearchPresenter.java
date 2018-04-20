package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.model.NetWorkModel;

import javax.inject.Inject;

@InjectViewState
public class DeepSearchPresenter extends MvpPresenter<DeepSearchView>{

    @Inject
    DeepSearchModel deepSearchModel;

    @Inject
    NetWorkModel netWorkModel;

    public DeepSearchPresenter(){
        App.getAppComponent().inject(this);
    }
}
