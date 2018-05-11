package com.example.stanislau_bushuk.foodhealth;


import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import ru.terrakok.cicerone.Router;
import timber.log.Timber;

public class MainActivityPresenter extends MvpPresenter<MvpView> {

    private Router router;

    public MainActivityPresenter(final Router router) {
        Timber.e(router.toString());
        this.router = router;
    }

    public void goBack(final String screenKey) {
        router.backTo(screenKey);
    }


    public void goTo(final String screenKey) {
        router.navigateTo(screenKey,1);
    }
}
