package com.example.stanislau_bushuk.foodhealth.presentantion.splashPresentatiom;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;

import javax.inject.Inject;

@InjectViewState
public class SplashActivityPresenter extends MvpPresenter<MvpView> {

    @Inject
    OwnRouter ownRouter;

    public SplashActivityPresenter(){
        App.getAppComponent().inject(this);
    }

    public void goTo(final String screenKey){
       // ownRouter.exit();
        ownRouter.newRootScreen(screenKey);

    }
}
