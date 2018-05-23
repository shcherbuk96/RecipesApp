package com.example.stanislau_bushuk.foodhealth.presentantion.loginPresentation;

import com.arellomobile.mvp.MvpView;
import com.google.firebase.auth.FirebaseUser;

public interface LoginView extends MvpView {
    void getUser(FirebaseUser firebaseUser);

    void error();
}
