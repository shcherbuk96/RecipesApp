package com.example.stanislau_bushuk.foodhealth.presentantion.registratinPresentation;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.google.firebase.auth.FirebaseUser;

public interface RegistrationView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void user(FirebaseUser firebaseUser);

    void error(Throwable e);

    void checkPassword();
}
