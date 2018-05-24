package com.example.stanislau_bushuk.foodhealth.presentantion.registratinPresentation;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kelvinapps.rxfirebase.RxFirebaseAuth;

import rx.Observable;


public class RegistrationModel {

    private CallBackRegistrationPresenter callBackRegistrationPresenter;
    private final FirebaseAuth mAuth;

    public RegistrationModel() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void setCallBack(final RegistrationPresenter registrationPresenter) {
        callBackRegistrationPresenter = registrationPresenter;
    }

    public void registrationUser(final String email, final String password) {
        final Observable<AuthResult> observable = RxFirebaseAuth.createUserWithEmailAndPassword(mAuth, email, password)
                .asObservable();
        callBackRegistrationPresenter.call(observable);
    }
}
