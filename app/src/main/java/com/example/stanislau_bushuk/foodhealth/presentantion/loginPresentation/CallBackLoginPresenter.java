package com.example.stanislau_bushuk.foodhealth.presentantion.loginPresentation;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import rx.Observable;


public interface CallBackLoginPresenter {
    void call(Observable<AuthResult> observable);
}
