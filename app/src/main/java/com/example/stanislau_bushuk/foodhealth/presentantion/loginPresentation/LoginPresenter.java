package com.example.stanislau_bushuk.foodhealth.presentantion.loginPresentation;

import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.auth.AuthResult;

import rx.Observable;


public class LoginPresenter extends MvpPresenter<LoginView> implements CallBackLoginPresenter{


    @Override
    public void call(final Observable<AuthResult> observable) {

    }
}
