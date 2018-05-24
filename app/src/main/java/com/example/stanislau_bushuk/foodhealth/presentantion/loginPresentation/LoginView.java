package com.example.stanislau_bushuk.foodhealth.presentantion.loginPresentation;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.google.firebase.auth.FirebaseUser;

public interface LoginView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void getUser(FirebaseUser firebaseUser);

    void error();
}
