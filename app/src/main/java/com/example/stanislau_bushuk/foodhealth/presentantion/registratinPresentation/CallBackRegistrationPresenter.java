package com.example.stanislau_bushuk.foodhealth.presentantion.registratinPresentation;

import com.google.firebase.auth.AuthResult;

import rx.Observable;

public interface CallBackRegistrationPresenter {
    void call(Observable<AuthResult> observable);
}
