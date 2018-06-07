package com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.google.firebase.auth.FirebaseUser;

@StateStrategyType(SkipStrategy.class)
public interface LoginView extends MvpView {
    void user(FirebaseUser firebaseUser);

    void error(Exception e);

    void checkPassword();

    void checkEmptyLine();
}
