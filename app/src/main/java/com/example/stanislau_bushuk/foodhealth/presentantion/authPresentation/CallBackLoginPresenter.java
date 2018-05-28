package com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation;

import com.google.firebase.auth.AuthResult;

import rx.Observable;


public interface CallBackLoginPresenter {
    void call(Observable<AuthResult> observable);
}
