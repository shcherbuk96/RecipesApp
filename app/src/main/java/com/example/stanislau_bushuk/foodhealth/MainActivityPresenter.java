package com.example.stanislau_bushuk.foodhealth;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;

import javax.inject.Inject;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MvpView> {

    @Inject
    OwnRouter router;

    public MainActivityPresenter() {
        App.getAppComponent().inject(this);
    }

    public void goBack(final String screenKey,final int data) {
        router.backToOwn(screenKey,data);
    }

    public void goTo(final String screenKey) {
        router.navigateTo(screenKey,1);
    }
}
