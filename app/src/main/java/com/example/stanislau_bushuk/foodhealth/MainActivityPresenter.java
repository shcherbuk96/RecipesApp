package com.example.stanislau_bushuk.foodhealth;


import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class MainActivityPresenter extends MvpPresenter<MvpView> {

    @Inject
    Router router;

    public MainActivityPresenter() {
        App.getAppComponent().inject(this);
    }

    public void goBack(final String screenKey) {
        router.backTo(screenKey);
    }

    public void goTo(final String screenKey) {
        router.navigateTo(screenKey, 1);
    }
}
